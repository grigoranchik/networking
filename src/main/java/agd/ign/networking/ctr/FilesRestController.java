package agd.ign.networking.ctr;


import agd.ign.networking.dto.files.*;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.CacheControl;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static org.springframework.web.bind.annotation.RequestMethod.POST;

/**
 * curl -F file=@"./README.md" http://localhost:8090/networking/rest/files/upload
 */
@RestController
@RequestMapping("/rest")
public class FilesRestController {

    private final Logger logger = LoggerFactory.getLogger(FilesRestController.class);

    //Save the uploaded file to this folder
    private static String UPLOADED_FOLDER = "down" + File.separator + "files";

    // http://localhost:8090/networking/rest/files/delete//download.jpg
    @RequestMapping(value = "/files/download/{fileName:.+}", method = RequestMethod.GET)
    public void downloadFile(@PathVariable(name = "fileName") String fileName,
                             HttpServletResponse response) throws IOException, InterruptedException {


        Path filePath = new File(UPLOADED_FOLDER + File.separator + fileName).toPath().toAbsolutePath();
        String type = Files.probeContentType(filePath);

        System.out.println("Transferring: " + filePath);

        File songFragment = filePath.toFile();
        InputStream in = new FileInputStream(songFragment);

        response.setContentType(type);

        response.addHeader("Cache-Control", CacheControl.maxAge(1, TimeUnit.HOURS).cachePublic().getHeaderValue());

        //Thread.sleep(500);

        IOUtils.copy(in, response.getOutputStream());

        in.close();
    }

    // http://localhost:8090/networking/rest/files/upload
    @PostMapping(value = "/files/upload", produces = "application/json")
    @ResponseBody
    public OkResponseDto uploadFile(@RequestParam("file") MultipartFile uploadfile) {

        logger.debug("Single file upload!");

        if (uploadfile.isEmpty()) {
            return new OkResponseDto();
        }

        try {
            saveUploadedFiles(Arrays.asList(uploadfile));
        } catch (IOException e) {
            return new OkResponseDto();
        }

        return new OkResponseDto();
    }

    // http://localhost:8090/networking/rest/files/list
    @RequestMapping(value = "/files/list", method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    public GetAvailFilesDto listFiles() throws IOException {

        GetAvailFilesDto rv = new GetAvailFilesDto();

        Path filePath = new File(UPLOADED_FOLDER).toPath().toAbsolutePath();

        File folder = filePath.toFile();
        File[] listOfFiles = folder.listFiles();

        for (File file : listOfFiles) {

            if (file.isDirectory()) {
                continue;
            }

            if (StringUtils.contains(file.getName(), ".gitkeep")) {
                continue;
            }

            BasicFileAttributes attr = Files.readAttributes(file.toPath(), BasicFileAttributes.class);

            System.out.println("creationTime: " + attr.creationTime());
            //System.out.println("lastAccessTime: " + attr.lastAccessTime());
            //System.out.println("lastModifiedTime: " + attr.lastModifiedTime());


            AvailFileDto fileDto = new AvailFileDto();
            fileDto.setAvailFileName(file.getName());
            fileDto.setAvailFileDateAddedMilliseconds(attr.creationTime().toMillis());

            rv.getAvailableFilesList().add(fileDto);

        }

        return rv;
    }

    @RequestMapping(path = "/files/delete", method = POST, produces = "application/json")
    @ResponseBody
    public OkResponseDto deleteFile(@RequestBody RemoveFileDto removeFileDto) throws IOException {

        String fileName = removeFileDto.getRemoveNaHuyFileName();
        Path filePath = new File(UPLOADED_FOLDER + File.separator + fileName).toPath().toAbsolutePath();
        Files.delete(filePath);
        return new OkResponseDto();
    }

    @RequestMapping(path = "/files/rename", method = POST, produces = "application/json")
    @ResponseBody
    public OkResponseDto deleteFile(@RequestBody RenameFileDto renameFileDto) throws IOException {

        String fileName = renameFileDto.getRemoveFileFrom();
        String newFileName = renameFileDto.getRemoveFileTo();

        Path filePath = new File(UPLOADED_FOLDER + File.separator + fileName).toPath().toAbsolutePath();

        //

        return new OkResponseDto();
    }

    //save file
    private void saveUploadedFiles(List<MultipartFile> files) throws IOException {

        for (MultipartFile file : files) {

            if (file.isEmpty()) {
                continue; //next pls
            }

            byte[] bytes = file.getBytes();
            Path path = Paths.get(UPLOADED_FOLDER + File.separator + file.getOriginalFilename());
            Files.write(path, bytes);

        }
    }
}