package agd.ign.ignition.dto.msg;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author aillusions
 */
@Data
@AllArgsConstructor
public class AvailMessageDto {
    private String existingMessageText;
    private String existingMessageFrom;
}
