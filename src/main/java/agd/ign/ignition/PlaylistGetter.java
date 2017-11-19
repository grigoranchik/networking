package agd.ign.ignition;

import org.apache.commons.lang3.StringUtils;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

/**
 * @author aillusions
 */
public class PlaylistGetter {

    private static final String STORAGE_PATH = "down";
    private static final String PLAYLIST_FILE_NAME = "playlist.m3u8";

    private static final String PL_FILE_NAME_PREFIX = "https://cf-hls-media.sndcdn.com/playlist/";
    private static final String PL_FILE_NAME_POSTFIX = "/playlist.m3u8?Policy";

    /**
     * https://cf-hls-media.sndcdn.com/playlist/7BSlpZTiK3pe.128.mp3/playlist.m3u8?Policy=eyJTdGF0ZW1lbnQiOlt7IlJlc291cmNlIjoiKjovL2NmLWhscy1tZWRpYS5zbmRjZG4uY29tL3BsYXlsaXN0LzdCU2xwWlRpSzNwZS4xMjgubXAzL3BsYXlsaXN0Lm0zdTgiLCJDb25kaXRpb24iOnsiRGF0ZUxlc3NUaGFuIjp7IkFXUzpFcG9jaFRpbWUiOjE1MTExMDQ1MTN9fX1dfQ__&Signature=PgbJpqIyCK2G08qV4aWH7PBl3fpnnh6RjL6h4FE6LbOFxbNQANJisYhr5KnLiWRx20CoyonMiTGrNDCtE~sCASixafs~MoqeEhM60rfHNOCKw86NR0hyXBwqZUr5eVolxZny8SYgebnR-~QMQ8uxWtDkG-2LprDW8EwZwHKevOIqHHel~8oEiEBukmTSYGqH6cZLbmXztTJ82wej7bz6m5K1ntMReMJHfnoMSZbas5K3u1vx32e3x3fgN~xN3a4GPzeWlO76w4dLvQxc5vAa65c2Uour1Nbnu3y93~oyWjm-1R~KOCl53A9Ykt-N8BBx3RXwSl4YVA~Q2FKRaDHsGw__&Key-Pair-Id=APKAJAGZ7VMH2PFPW6UQ
     */
    public static Path playList(String url) throws IOException {

        String songId = getFileName(url);

        String dir = getSongStorePath(songId);

        new File(dir).mkdir();

        Path playlistFilePath = getSongPlaylistPath(dir);
        String playListFilePathStr = playlistFilePath.toAbsolutePath().toString();

        System.out.println("Saving playlist: " + playListFilePathStr);

        try (InputStream in = SoundcloudAccessor.getInputStream(url)) {
            Files.copy(in, playlistFilePath, StandardCopyOption.REPLACE_EXISTING);
        }

        return playlistFilePath;
    }

    protected static String getFileName(String url) {
        return StringUtils.substringBetween(url, PL_FILE_NAME_PREFIX, PL_FILE_NAME_POSTFIX);
    }

    private static String getSongStorePath(String songId) {
        return new File(STORAGE_PATH + File.separator + songId).toPath().toAbsolutePath().toString();
    }

    private static Path getSongPlaylistPath(String directory) {
        return new File(directory + File.separator + PLAYLIST_FILE_NAME).toPath();
    }
}



