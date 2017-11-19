package agd.ign.ignition;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.zip.GZIPInputStream;

/**
 * @author aillusions
 */
public class PlaylistGetter {

    public static String playList(String url) throws IOException {

        URLConnection connection = new URL(url).openConnection();

        connection.addRequestProperty("Referer", "https://soundcloud.com/");
        connection.addRequestProperty("Origin", "https://soundcloud.com");
        connection.addRequestProperty("Host", "cf-hls-media.sndcdn.com");
        connection.addRequestProperty("Accept", "*/*");
        connection.addRequestProperty("Connection", "keep-alive");
        connection.addRequestProperty("Accept-Language", "en-us");
        connection.addRequestProperty("Accept-Encoding", "br, gzip, deflate");
        connection.addRequestProperty("User-Agent", "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_13) AppleWebKit/604.1.38 (KHTML, like Gecko) Version/11.0 Safari/604.1.38");

        // connection.addRequestProperty("", "");

        //connection.setDoOutput(true); // Triggers POST.
        // connection.setRequestProperty("Accept-Charset", "UTF-8");
        // connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded;charset=" + "UTF-8");

        //FileUtils.copyURLToFile(connection, File);


        Path targetPath = new File("down" + File.separator + "play.m3u8").toPath();

        System.out.println("Saving playlist: " + targetPath.toAbsolutePath().toString());


        InputStream is;
        if ("gzip".equals(connection.getContentEncoding())) {
            is = new GZIPInputStream(connection.getInputStream());
        } else {
            is = connection.getInputStream();
        }

        try (InputStream in = is) {
            Files.copy(in, targetPath, StandardCopyOption.REPLACE_EXISTING);
        }

        return "";
    }
}



