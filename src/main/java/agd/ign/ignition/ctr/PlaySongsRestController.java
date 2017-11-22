package agd.ign.ignition.ctr;

import agd.ign.ignition.app.PlaylistGetter;
import agd.ign.ignition.dto.get.AvailSongDto;
import agd.ign.ignition.dto.get.GetAvailSongsDto;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * @author aillusions
 */
@RestController()
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/rest")
public class PlaySongsRestController {

    // https://localhost/ignition/rest/play/23cS6M2r9CA7.128.mp3
    @RequestMapping(value = "/play/{songId:.+}", method = RequestMethod.GET)
    public void getSongFragment(@PathVariable(name = "songId") String songId, HttpServletResponse response) throws IOException {

        boolean isOpus = StringUtils.endsWithIgnoreCase(songId, ".opus");

        File songFragment = PlaylistGetter.getSongFragmentPath(songId, isOpus ? "4.opus" : "4.mp3").toFile();
        InputStream in = new FileInputStream(songFragment);

        if (isOpus) {
            response.setContentType("audio/ogg");
        } else {
            response.setContentType("audio/mp3");
        }

        IOUtils.copy(in, response.getOutputStream());

        in.close();
    }

    // https://localhost/ignition/rest/list
    @RequestMapping(value = "/list", method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    public GetAvailSongsDto listSongs() {

        GetAvailSongsDto rv = new GetAvailSongsDto();

        File folder = PlaylistGetter.getAbsoluteStoragePath().toFile();
        File[] listOfFiles = folder.listFiles();

        for (File file : listOfFiles) {

            String songId = file.getName();
            if (file.isDirectory()) {
                StringUtils.containsIgnoreCase(songId, ".mp3");
                System.out.println("Found recording: " + songId);

                AvailSongDto songDto = new AvailSongDto();
                songDto.setAvailSongId(songId);
                rv.getAvailableSongsList().add(songDto);
            }
        }

        return rv;
    }
}
