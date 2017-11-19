package agd.ign.ignition;

import junit.framework.TestCase;

/**
 * @author aillusions
 */
public class PlaylistGetterTest extends TestCase {

    public void testName() {
        String url = "https://cf-hls-media.sndcdn.com/playlist/7BSlpZTiK3pe.128.mp3/playlist.m3u8?Policy=eyJTdGF0ZW1lbnQiOlt7IlJlc291cmNlIjoiKjovL2NmLWhscy1tZWRpYS5zbmRjZG4uY29tL3BsYXlsaXN0LzdCU2xwWlRpSzNwZS4xMjgubXAzL3BsYXlsaXN0Lm0zdTgiLCJDb25kaXRpb24iOnsiRGF0ZUxlc3NUaGFuIjp7IkFXUzpFcG9jaFRpbWUiOjE1MTExMDQ1MTN9fX1dfQ__&Signature=PgbJpqIyCK2G08qV4aWH7PBl3fpnnh6RjL6h4FE6LbOFxbNQANJisYhr5KnLiWRx20CoyonMiTGrNDCtE~sCASixafs~MoqeEhM60rfHNOCKw86NR0hyXBwqZUr5eVolxZny8SYgebnR-~QMQ8uxWtDkG-2LprDW8EwZwHKevOIqHHel~8oEiEBukmTSYGqH6cZLbmXztTJ82wej7bz6m5K1ntMReMJHfnoMSZbas5K3u1vx32e3x3fgN~xN3a4GPzeWlO76w4dLvQxc5vAa65c2Uour1Nbnu3y93~oyWjm-1R~KOCl53A9Ykt-N8BBx3RXwSl4YVA~Q2FKRaDHsGw__&Key-Pair-Id=APKAJAGZ7VMH2PFPW6UQ";
        assertEquals("7BSlpZTiK3pe.128.mp3", PlaylistGetter.getFileName(url));
    }
}
