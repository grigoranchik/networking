package agd.ign.ignition;

import agd.ign.ignition.app.PlaylistGetter;
import agd.ign.ignition.app.PlaylistReader;
import agd.ign.ignition.dto.put.NewSongDto;
import org.apache.commons.lang3.StringUtils;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Service;

import java.nio.file.Path;
import java.util.List;

/**
 * @author aillusions
 */
@EnableAsync
@Service
public class AsyncService {

    @Async()
    public void asyncDownloadFragments(NewSongDto dto) {

        String url = dto.getScCjsSongPlayListUrl();
        try {

            String songId = PlaylistGetter.getSongId(url);
            boolean isOpus = StringUtils.endsWithIgnoreCase(songId, ".opus");

            Path playlistFilePath = PlaylistGetter.getSongPlaylistPath(songId);
            if (playlistFilePath.toFile().exists()) {
                throw new RuntimeException("Already indexed: " + songId);
            }
            PlaylistGetter.downloadPlayList(url, playlistFilePath);

            List<String> partUrls = PlaylistReader.getPartUrls(playlistFilePath);

            int i = 0;
            for (String partUrl : partUrls) {

                Path fragmentPath;
                if (isOpus) {
                    fragmentPath = PlaylistGetter.getSongOpusPath(i, songId);
                } else {
                    fragmentPath = PlaylistGetter.getSongMp3Path(i, songId);
                }

                String playListFilePathStr = fragmentPath.toAbsolutePath().toString();
                System.out.println("Saving fragment: " + playListFilePathStr + " (" + (i + 1) + " of " + partUrls.size() + ")");

                PlaylistGetter.downloadFragment(fragmentPath, partUrl);

                i++;
            }

            Path metaPath = PlaylistGetter.getSongMetadataPath(songId);
            PlaylistGetter.saveMetadata(metaPath, dto);

            System.out.println("Done: " + dto.getScCjsSongTitle());

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
