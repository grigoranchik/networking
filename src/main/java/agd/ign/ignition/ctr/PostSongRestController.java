package agd.ign.ignition.ctr;

import agd.ign.ignition.app.PlaylistGetter;
import agd.ign.ignition.app.PlaylistReader;
import agd.ign.ignition.dto.AboutIgnitionDto;
import agd.ign.ignition.dto.put.NewSongDto;
import agd.ign.ignition.dto.put.NewSongResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;

/**
 * @author aillusions
 */
@RestController()
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/rest")
public class PostSongRestController {

    // http://localhost:8090/ignition/rest/about
    // https://localhost/ignition/rest/about
    // https://192.168.0.103/ignition/rest/about
    // https://zalizniak.com/ignition/rest/about
    @RequestMapping(value = "/about", method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    public AboutIgnitionDto about() {
        return new AboutIgnitionDto();
    }

    @RequestMapping(value = "/song/new", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
    @ResponseBody
    public NewSongResponseDto sendSong(@RequestBody NewSongDto dto) throws IOException {

        System.out.println(dto.getScCjsSongTitle());

        String url = dto.getScCjsSongPlayListUrl();
        try {

            String songId = PlaylistGetter.getSongId(url);

            Path playlistFilePath = PlaylistGetter.getSongPlaylistPath(songId);
            if (playlistFilePath.toFile().exists()) {
                throw new RuntimeException("Already indexed: " + songId);
            }
            PlaylistGetter.downloadPlayList(url, playlistFilePath);

            List<String> partUrls = PlaylistReader.getPartUrls(playlistFilePath);

            int i = 0;
            for (String partUrl : partUrls) {
                Path mp3Path = PlaylistGetter.getSongMp3Path(i, songId);
                String playListFilePathStr = mp3Path.toAbsolutePath().toString();
                System.out.println("Saving mp3: " + playListFilePathStr + " (" + (i + 1) + " of " + partUrls.size() + ")");

                PlaylistGetter.downloadMp3(mp3Path, partUrl);
                i++;
            }

            Path metaPath = PlaylistGetter.getSongMetadataPath(songId);
            PlaylistGetter.saveMetadata(metaPath, dto);

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        System.out.println("");
        return new NewSongResponseDto();
    }

    @RequestMapping(value = "/**", method = RequestMethod.OPTIONS)
    public ResponseEntity handle() {
        return new ResponseEntity(HttpStatus.OK);
    }
}
