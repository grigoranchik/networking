package agd.ign.networking.dto.files;

import lombok.Data;

@Data()
public class RenameFileDto {
    private String removeFileFrom;
    private String removeFileTo;
}
