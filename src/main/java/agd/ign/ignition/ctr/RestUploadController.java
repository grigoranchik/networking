package agd.ign.ignition.ctr;


import agd.ign.ignition.dto.msg.NewMessageResponseDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

/**
 * curl -F file=@"C:\\env\\ignition\\README.md" http://localhost:8090/ignition/rest/files/upload
 * curl -F file=@"C:\\env\\ignition\\README.md" http://176.36.229.152/ignition/rest/files/upload
 */
@RestController
@RequestMapping("/rest")
public class RestUploadController {

    private final Logger logger = LoggerFactory.getLogger(RestUploadController.class);

    //Save the uploaded file to this folder
    private static String UPLOADED_FOLDER = "down/";//PlaylistGetter.STORAGE_PATH;

    // http://176.36.229.152/ignition/rest/files/upload
    @PostMapping("/files/upload")
    @ResponseBody
    public NewMessageResponseDto uploadFile(@RequestParam("file") MultipartFile uploadfile) {

        logger.debug("Single file upload!");

        if (uploadfile.isEmpty()) {
            return new NewMessageResponseDto();
        }

        try {
            saveUploadedFiles(Arrays.asList(uploadfile));
        } catch (IOException e) {
            return new NewMessageResponseDto();
        }

        return new NewMessageResponseDto();
    }


    //save file
    private void saveUploadedFiles(List<MultipartFile> files) throws IOException {

        for (MultipartFile file : files) {

            if (file.isEmpty()) {
                continue; //next pls
            }

            byte[] bytes = file.getBytes();
            Path path = Paths.get(UPLOADED_FOLDER + file.getOriginalFilename());
            Files.write(path, bytes);

        }
    }
}