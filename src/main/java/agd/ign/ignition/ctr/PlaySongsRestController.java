package agd.ign.ignition.ctr;

import agd.ign.ignition.app.PlaylistGetter;
import agd.ign.ignition.dto.get.AvailSongDto;
import agd.ign.ignition.dto.get.GetAvailSongsDto;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.io.File;

/**
 * @author aillusions
 */
@RestController()
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/rest")
public class PlaySongsRestController {


    // https://localhost/ignition/rest/list
    @RequestMapping(value = "/list", method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    public GetAvailSongsDto play() {

        GetAvailSongsDto rv = new GetAvailSongsDto();

        File folder = PlaylistGetter.getAbsoluteStoragePath().toFile();
        File[] listOfFiles = folder.listFiles();

        for (File file : listOfFiles) {

            String songId = file.getName();
            if (file.isDirectory()) {
                StringUtils.containsIgnoreCase(songId, ".mp3");
                System.out.println("Found recording: " + songId);


                AvailSongDto songDto = new AvailSongDto();
                songDto.setAvailSongFragmentUri("/ignition/rest/play/" + songId + "/1.mp3");
                rv.getAvailableSongsList().add(songDto);
            }
        }

        return rv;
    }
}
