package agd.ign.ignition.dto.msg;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author aillusions
 */
@Data
@AllArgsConstructor
public class NewMessageDto {
    private String newMessageText;
    private String newMessageFrom;
}
