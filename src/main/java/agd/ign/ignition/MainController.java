package agd.ign.ignition;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;

/**
 * http://localhost/ignition/rest/about
 * <p>
 * https://localhost/ignition/rest/about
 * https://192.168.0.103/ignition/rest/about
 *
 * @author aillusions
 */
@RestController()
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/ignition")
public class MainController {

    @RequestMapping(value = "/rest/about", method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    public AboutIgnitionDto about() {
        return new AboutIgnitionDto();
    }

    @RequestMapping(value = "/rest/song/new", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
    @ResponseBody
    public NewSongResponseDto sendSong(@RequestBody NewSongDto dto) throws IOException {

        String url = dto.getScCjsSongPlayListUrl();
        try {

            String songId = PlaylistGetter.getSongId(url);

            Path playlistFilePath = PlaylistGetter.getSongPlaylistPath(songId);
            PlaylistGetter.downloadPlayList(url, playlistFilePath);

            List<String> partUrls = PlaylistReader.getPartUrls(playlistFilePath);

            int i = 0;
            for (String partUrl : partUrls) {
                Path mp3Path = PlaylistGetter.getSongMp3Path(i, songId);
                PlaylistGetter.downloadMp3(mp3Path, partUrl);
                i++;
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return new NewSongResponseDto();
    }

    @RequestMapping(value = "/**", method = RequestMethod.OPTIONS)
    public ResponseEntity handle() {
        return new ResponseEntity(HttpStatus.OK);
    }
}
