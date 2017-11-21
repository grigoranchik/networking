package agd.ign.ignition.dto.get;

import lombok.Data;

import java.util.LinkedList;
import java.util.List;

/**
 * @author aillusions
 */
@Data
public class GetAvailSongsDto {

    private List<AvailSongDto> availableSongsList = new LinkedList<>();
}
