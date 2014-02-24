import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ideax.fm360.pojo.SongXiami;
import com.mpatric.mp3agic.ID3v1;
import com.mpatric.mp3agic.ID3v2;
import com.mpatric.mp3agic.InvalidDataException;
import com.mpatric.mp3agic.Mp3File;
import com.mpatric.mp3agic.UnsupportedTagException;

public class GetSongInfoFromXiami {

    public static ObjectMapper mapper = new ObjectMapper();
    static {
        mapper.configure(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT, false);
    }

    public static void main(String[] args) throws Exception {

        File text = new File("D:\\BaiduMusicAIO\\result.txt");
        PrintWriter pw = new PrintWriter(text, "UTF-8");

        File dir = new File("D:\\BaiduMusicAIO\\Songs");
        File[] files = dir.listFiles();
        for (File mp3 : files) {
            SongXiami song = new SongXiami();
            getMp3Info(mp3.getAbsolutePath(), song);
            catchDetailInfoFromXiami(song);
            String s = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(song);
            pw.println(s);
        }
        pw.flush();
        pw.close();

    }

    public static void catchDetailInfoFromXiami(SongXiami song) {
        String searchkey = song.getName().replace(" ", "+") + "+" + song.getArtist().replace(" ", "+");
        Document doc;
        try {
            doc = Jsoup.connect("http://www.xiami.com/search?key=" + searchkey).get();
        } catch (IOException e1) {
            e1.printStackTrace();
            return;
        }
        System.out.println(searchkey);
        Elements newsHeadlines = doc.select(".track_list tbody");
        if (newsHeadlines.size() <= 0) {
            System.out.println("no search result:" + searchkey);
            return;
        }
        Element firstrow = newsHeadlines.get(0);

        Elements els = firstrow.select(".song_name a");
        Element songA = els.get(els.size() - 1);
        String songName = songA.attr("title");
        String songLink = songA.attr("href");

        Element artistA = firstrow.select(".song_artist a").get(0);
        String artistName = artistA.attr("title");
        String artistLink = artistA.attr("href");

        Element albumA = firstrow.select(".song_album a").get(0);
        String albumName = albumA.attr("title");
        String albumLink = albumA.attr("href");

        song.setXiamiSongName(songName);
        song.setXiamiSongLink(songLink);
        song.setXiamiArtist(artistName);
        song.setXiamiArtistLink(artistLink);
        song.setXiamiAlbum(albumName);
        song.setXiamiAlbumLink(albumLink);

        // 专辑图片
        Document albumDoc;
        try {
            albumDoc = Jsoup.connect(albumLink).get();
            Elements coverels = albumDoc.select(".cdCDcover185");
            if (coverels.size() > 0) {
                Element img = coverels.get(0);
                String src = img.attr("src");
                if (StringUtils.isNotBlank(src))
                    song.setCoverImg(src);
            }
            Elements albuminfo = albumDoc.select("#albums_info");
            albuminfo.select("div");
            
            
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void getMp3Info(String path, SongXiami song) {

        try {
            Mp3File mp3file = new Mp3File(path);

            song.setBitrate(mp3file.getBitrate());
            song.setIsvbr(mp3file.isVbr() ? (byte) 1 : (byte) 0);
            song.setDuration((int) (mp3file.getLengthInSeconds()));

            if (mp3file.hasId3v1Tag()) {
                ID3v1 id3v1Tag = mp3file.getId3v1Tag();
                // System.out.println("Track: " + id3v1Tag.getTrack());
                song.setArtist(id3v1Tag.getArtist());
                song.setName(id3v1Tag.getTitle());
                song.setAlbum(id3v1Tag.getAlbum());
                song.setYear(id3v1Tag.getYear());
                // System.out.println("Genre: " + id3v1Tag.getGenre() + " ("
                // + id3v1Tag.getGenreDescription() + ")");
                // System.out.println("Comment: " + id3v1Tag.getComment());
            }

            if (mp3file.hasId3v2Tag()) {
                ID3v2 id3v2Tag = mp3file.getId3v2Tag();
                // id3v2Tag.getTrack();
                song.setArtist(id3v2Tag.getArtist());
                song.setName(id3v2Tag.getTitle());
                song.setAlbum(id3v2Tag.getAlbum());
                song.setYear(id3v2Tag.getYear());
                // System.out.println("Genre: " + id3v2Tag.getGenre() + " ("
                // + id3v2Tag.getGenreDescription() + ")");
                // System.out.println("Comment: " + id3v2Tag.getComment());
                song.setComposer(id3v2Tag.getComposer());
                // System.out.println("Publisher: " +
                // id3v2Tag.getPublisher());
                // System.out.println("Original artist: " +
                // id3v2Tag.getOriginalArtist());
                song.setAlbumArtist(id3v2Tag.getAlbumArtist());
                // System.out.println("Copyright: " +
                // id3v2Tag.getCopyright());
                // System.out.println("URL: " + id3v2Tag.getUrl());
                // System.out.println("Encoder: " + id3v2Tag.getEncoder());
                byte[] albumImageData = id3v2Tag.getAlbumImage();
                if (albumImageData != null) {
                    // Write image to file - can determine appropriate
                    // file extension from the mime type
                    String artname = song.getName().toLowerCase().replace(" ", "_") + ".jpg";
                    IOUtils.copy(new ByteArrayInputStream(albumImageData), new FileOutputStream(new File(
                            "D:\\BaiduMusicAIO\\Artwork\\" + artname)));
                    song.setCoverImg(artname);
                }
            }
        } catch (UnsupportedTagException e) {
            e.printStackTrace();
        } catch (InvalidDataException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
