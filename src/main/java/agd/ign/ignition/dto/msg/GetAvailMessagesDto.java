package agd.ign.ignition.dto.msg;

import lombok.Data;

import java.util.LinkedList;
import java.util.List;

/**
 * @author aillusions
 */
@Data
public class GetAvailMessagesDto {

    private List<String> availableMessagesList = new LinkedList<>();
}
