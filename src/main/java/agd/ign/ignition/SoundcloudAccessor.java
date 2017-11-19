package agd.ign.ignition;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.zip.GZIPInputStream;

/**
 * @author aillusions
 */
public class SoundcloudAccessor {

    public static InputStream getInputStream(String url) throws IOException {
        URLConnection connection = getConnection(url);

        InputStream is;
        if ("gzip".equals(connection.getContentEncoding())) {
            is = new GZIPInputStream(connection.getInputStream());
        } else {
            is = connection.getInputStream();
        }

        return is;
    }

    public static URLConnection getConnection(String url) throws IOException {

        URLConnection connection = new URL(url).openConnection();

        connection.addRequestProperty("Referer", "https://soundcloud.com/");
        connection.addRequestProperty("Origin", "https://soundcloud.com");
        connection.addRequestProperty("Host", "cf-hls-media.sndcdn.com");
        connection.addRequestProperty("Accept", "*/*");
        connection.addRequestProperty("Connection", "keep-alive");
        connection.addRequestProperty("Accept-Language", "en-us");
        connection.addRequestProperty("Accept-Encoding", "br, gzip, deflate");
        connection.addRequestProperty("User-Agent", "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_13) AppleWebKit/604.1.38 (KHTML, like Gecko) Version/11.0 Safari/604.1.38");

        return connection;
    }
}
