package agd.ign.ignition;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 *
 * http://localhost/ignition/rest/about
 *
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
    public NewSongResponseDto sendSong(@RequestBody NewSongDto dto) {
        dto.getScCjsSongTitle();
        System.out.println(dto);
        return new NewSongResponseDto();
    }

    @RequestMapping(value = "/**", method = RequestMethod.OPTIONS)
    public ResponseEntity handle() {
        return new ResponseEntity(HttpStatus.OK);
    }
}
