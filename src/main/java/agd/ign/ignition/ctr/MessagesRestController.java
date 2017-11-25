package agd.ign.ignition.ctr;

import agd.ign.ignition.dto.msg.AvailMessageDto;
import agd.ign.ignition.dto.msg.GetAvailMessagesDto;
import agd.ign.ignition.dto.msg.NewMessageDto;
import agd.ign.ignition.dto.msg.NewMessageResponseDto;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author aillusions
 */
@RestController()
@RequestMapping("/rest")
public class MessagesRestController {

    private static List<NewMessageDto> MESSAGES = Collections.synchronizedList(new LinkedList<>());

    static {
        MESSAGES.add(new NewMessageDto("Гриша лох, ггг", "Alex"));
    }

    @RequestMapping(value = "/messages/send", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
    @ResponseBody
    public NewMessageResponseDto sendMessage(@RequestBody NewMessageDto dto) throws IOException {
        if (StringUtils.isNotBlank(dto.getNewMessageFrom())
                && StringUtils.isNotBlank(dto.getNewMessageText())
                && !MESSAGES.contains(dto.getNewMessageText())) {
            MESSAGES.add(dto);
        }

        System.out.println("Added message: " + dto.getNewMessageText());
        return new NewMessageResponseDto();
    }

    // https://localhost/ignition/rest/messages/list
    @RequestMapping(value = "/messages/list", method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    public GetAvailMessagesDto getAvailMessages() {
        GetAvailMessagesDto rv = new GetAvailMessagesDto();

        rv.getAvailableMessagesList().addAll(MESSAGES.stream().map(m -> new AvailMessageDto(m.getNewMessageText(), m.getNewMessageFrom())).collect(Collectors.toList()));

        System.out.println("Returned messages: " + MESSAGES.size());

        return rv;
    }
}
