package agd.ign.ignition;

import org.apache.commons.lang3.StringUtils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author aillusions
 */
public class PlaylistReader {

    public static List<String> getPartUrls(Path playlistFilePath) throws IOException {
        final List<String> rv = new LinkedList<>();
        Files.lines(playlistFilePath).forEach(s -> rv.add(s));
        return rv.stream().filter(s -> StringUtils.startsWithIgnoreCase(s, "https://")).collect(Collectors.toList());
    }
}



