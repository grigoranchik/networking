package agd.ign.networking.ctr;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * http://localhost:8090/networking/
 * @author aillusions
 */
@Controller
public class WelcomeController {

    // http://localhost:8090/ignition
    // https://localhost/ignition
    // https://192.168.0.103/ignition
    // https://zalizniak.com/ignition
    @RequestMapping("/")
    public String home() {
        //return "html/index.html";
        return "html/work_with_files.html";
    }
}
