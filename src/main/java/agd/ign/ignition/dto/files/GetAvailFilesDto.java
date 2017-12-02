package agd.ign.ignition.dto.files;

import lombok.Data;

import java.util.LinkedList;
import java.util.List;

/**
 * @author aillusions
 */
@Data
public class GetAvailFilesDto {

    private List<AvailFileDto> availableFilesList = new LinkedList<>();
}
