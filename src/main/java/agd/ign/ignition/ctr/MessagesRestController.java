package agd.ign.ignition.ctr;

import agd.ign.ignition.dto.msg.GetAvailMessagesDto;
import agd.ign.ignition.dto.msg.NewMessageDto;
import agd.ign.ignition.dto.msg.NewMessageResponseDto;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * @author aillusions
 */
@RestController()
@RequestMapping("/rest")
public class MessagesRestController {

    private static List<String> MESSAGES = Collections.synchronizedList(new LinkedList<>());

    static {
        MESSAGES.add("Привет");
        MESSAGES.add("Гриша лох...");
    }

    @RequestMapping(value = "/messages/send", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
    @ResponseBody
    public NewMessageResponseDto sendMessage(@RequestBody NewMessageDto dto) throws IOException {
        MESSAGES.add(dto.getNewMessageText());
        System.out.println("Added message: " + dto.getNewMessageText());
        return new NewMessageResponseDto();
    }

    // https://localhost/ignition/rest/messages/list
    @RequestMapping(value = "/messages/list", method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    public GetAvailMessagesDto getAvailMessages() {
        GetAvailMessagesDto rv = new GetAvailMessagesDto();

        rv.getAvailableMessagesList().addAll(MESSAGES);

        System.out.println("Returned messages: " + MESSAGES.size());

        return rv;
    }
}
