package com.ideax.fm360.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.CharUtils;
import org.apache.commons.lang3.StringUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.baidu.inf.iis.bcs.BaiduBCS;
import com.baidu.inf.iis.bcs.auth.BCSCredentials;
import com.baidu.inf.iis.bcs.model.ObjectMetadata;
import com.baidu.inf.iis.bcs.request.PutObjectRequest;
import com.baidu.inf.iis.bcs.response.BaiduBCSResponse;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ideax.common.exception.IllegalException;
import com.ideax.fm360.dao.SongDAO;
import com.ideax.fm360.dao.SongXiamiDAO;
import com.ideax.fm360.pojo.Song;
import com.ideax.fm360.pojo.SongXiami;
import com.mpatric.mp3agic.ID3v1;
import com.mpatric.mp3agic.ID3v2;
import com.mpatric.mp3agic.InvalidDataException;
import com.mpatric.mp3agic.Mp3File;
import com.mpatric.mp3agic.UnsupportedTagException;

@Controller
@RequestMapping("admin")
public class AdminC {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    public static ObjectMapper mapper = new ObjectMapper();
    static {
        mapper.configure(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT, false);
    }

    static String host = "bcs.duapp.com";
    static String accessKey = "XcMrzzpIgrVlXUOtAEzL3PFz";
    static String secretKey = "BOFBT0GSnoh0cnancoAAhhSTWFVonf8d";
    static String bucket = "fmstore";

    // static Set<String> parsered = new HashSet<String>();
    // static {
    // parsered.add("1900");
    // parsered.add("2004");
    // parsered.add("2005");
    // parsered.add("2006");
    // parsered.add("best of A1");
    // parsered.add("best of avril");
    // parsered.add("best of blue");
    // parsered.add("best of eminem");
    // parsered.add("best of jet");
    // parsered.add("best of linkin park");
    // parsered.add("best of westlife");
    // parsered.add("best 周杰伦");
    //
    // }

    // ----------------------------------------

    @Value("${mp3.upload.path}")
    String uploadPath;

    @Autowired
    SongXiamiDAO songXiamiDao;

    @Autowired
    SongDAO songDao;

    BaiduBCS baiduBCS = null;

    public AdminC() {
        BCSCredentials credentials = new BCSCredentials(accessKey, secretKey);
        baiduBCS = new BaiduBCS(credentials, host);
        // baiduBCS.setDefaultEncoding("GBK");
        baiduBCS.setDefaultEncoding("UTF-8"); // Default UTF-8

    }

    @RequestMapping("search")
    public String searchXiami(@RequestParam(defaultValue = "") String songName,
            @RequestParam(defaultValue = "") String artist, Model model) throws Exception {
        if (songName != null && !songName.isEmpty()) {
            String searchUrl = "http://www.xiami.com/search?key=" + songName.replace(" ", "+") + "+"
                    + artist.replace(" ", "+");
            Document doc;
            try {
                doc = Jsoup.connect(searchUrl).get();
            } catch (IOException e1) {
                e1.printStackTrace();
                model.addAttribute("searchResult", "no search result : <a href='" + searchUrl + "'>" + searchUrl
                        + "</a>");
                return "admin/searchlist";

            }
            Element searchTable = doc.select(".track_list").get(0);
            model.addAttribute("songName", songName);
            model.addAttribute("artist", artist);
            model.addAttribute("searchResult", searchTable.toString());
        }
        return "admin/searchlist";
    }

    @RequestMapping("catch")
    public String searchXiami(HttpServletRequest req, Model model) throws Exception {
        String songLink = null;
        String mp3Path = null;
        String fileName = null;
        if (ServletFileUpload.isMultipartContent(req)) {
            try {
                int length = 0;
                DiskFileItemFactory factory = new DiskFileItemFactory(1024 * 1024, null);
                ServletFileUpload upload = new ServletFileUpload(factory);
                upload.setHeaderEncoding("UTF-8");
                upload.setSizeMax(1024 * 1024 * 10);
                List<FileItem> fileItems = null;
                try {
                    fileItems = upload.parseRequest(req);
                } catch (FileUploadException e) {
                    logger.error("", e);
                    throw new IllegalException(1001, "FileUploadException when parse request!");
                }
                if (fileItems == null) {
                    throw new IllegalException(1001, "no post args!");
                }
                Iterator<FileItem> iter = fileItems.iterator();
                while (iter.hasNext()) {
                    FileItem item = (FileItem) iter.next();
                    if (item.isFormField()) {
                        if (logger.isDebugEnabled()) {
                            logger.debug("form data : [" + item.getFieldName() + ":" + item.getString("UTF-8") + "]");
                        }
                        if (item.getFieldName().equals("link")) {
                            songLink = item.getString("UTF-8");
                        }
                    } else {
                        fileName = item.getName();
                        if (fileName != null) {
                            length = 0;
                            InputStream ips = null;
                            FileOutputStream imageOs = null;
                            String imgtype = fileName.substring(fileName.lastIndexOf('.'));
                            try {
                                if (!imgtype.equals(".mp3") && !imgtype.equals(".m4a")) {
                                    throw new IllegalException(1001, "必须是mp3 或者 m4a 文件");
                                }
                                mp3Path = uploadPath + fileName;
                                // length = (int) item.getSize();
                                imageOs = new FileOutputStream(mp3Path); // init
                                ips = item.getInputStream();
                                length = IOUtils.copy(ips, imageOs);
                                logger.info("upload file length " + length);
                            } catch (IOException e) {
                                logger.error("", e);
                                throw new IllegalException(1001, "IOException when getting file!");
                            } finally {
                                IOUtils.closeQuietly(ips);
                                IOUtils.closeQuietly(imageOs);
                            }
                        }
                    }
                }
                if (length == 0)
                    throw new IllegalException(1001, "size is 0 ");
            } catch (IllegalException e) {
                logger.error("errorcode exception !", e);
                model.addAttribute("error", e.getMessage());
            } catch (Exception e) {
                logger.error("", e);
                model.addAttribute("error", e.getMessage());
            } finally {
            }
        }

        if (mp3Path != null || songLink != null) {
            SongXiami song = new SongXiami();
            song.setFilePath(fileName);
            song.setXiamiSongLink(songLink);
            // --------------------------------------------------------------------
            // --------------------------------
            // 抓虾米-------------------------------
            // --------------------------------------------------------------------
            if (StringUtils.isNotBlank(songLink)) {
                Document albumDoc;
                try {
                    albumDoc = Jsoup.connect(songLink).get();
                    // 标题
                    Elements titles = albumDoc.select("#title");
                    if (titles.size() > 0) {
                        song.setXiamiSongName(titles.get(0).text());
                    }
                    // 专辑图片
                    Elements coverels = albumDoc.select(".cdCDcover185");
                    if (coverels.size() > 0) {
                        Element img = coverels.get(0);
                        String src = img.attr("src");
                        if (StringUtils.isNotBlank(src))
                            song.setXiamiCoverImg(src);
                        else
                            System.out.println("!!!!!!!!!!!!!!!!!! block");
                    }
                    // 专辑信息
                    Elements albuminfo = albumDoc.select("#albums_info");
                    Elements albumels = albuminfo.select(".item");
                    if (albumels.size() > 0) {
                        for (Element item : albumels) {
                            String key = item.text();
                            String value = null;
                            String link = null;
                            Element valueEl = item.nextElementSibling();
                            if (valueEl != null) {
                                try {
                                    Element div = valueEl.child(0);
                                    if (div.children().size() > 0) {
                                        Element a = div.child(0);
                                        value = a.text();
                                        link = a.attr("href");
                                    } else {
                                        value = div.text();
                                    }
                                } catch (IndexOutOfBoundsException e2) {
                                }
                            }
                            if (StringUtils.isNotBlank(key) && StringUtils.isNotBlank(value)) {
                                if (key.contains("专辑")) {
                                    song.setXiamiAlbum(value);
                                    song.setXiamiAlbumLink(link);
                                } else if (key.contains("演唱")) {
                                    song.setXiamiArtist(value);
                                    song.setXiamiArtistLink(link);
                                } else if (key.contains("词")) {
                                    song.setXiamiLyricist(value);
                                } else if (key.contains("作曲")) {
                                    song.setXiamiComposer(value);
                                }
                            }
                        }
                    }
                    // 歌词
                    Elements lrcs = albumDoc.select(".lrc_main");
                    if (lrcs.size() > 0) {
                        Element lrc = lrcs.get(0);
                        String lrcstr = lrc.html();
                        if (StringUtils.isNotBlank(lrcstr)) {
                            song.setXiamiLyric(lrcstr);
                        }
                    }
                    // 视听次数
                    Elements pcns = albumDoc.select("#play_count_num");
                    if (pcns.size() > 0) {
                        Element pcn = pcns.get(0);
                        String pcnn = pcn.text();
                        try {
                            song.setListenNum(Integer.valueOf(pcnn));
                        } catch (NumberFormatException e) {
                        }
                    }
                } catch (IOException e1) {
                    e1.printStackTrace();
                }

            }

            // --------------------------------------------------------------------
            // --------------------------------
            // 解析歌曲文件-------------------------------
            // --------------------------------------------------------------------

            getMp3Info(mp3Path, song);

            model.addAttribute("xiamiSong", song);
        }

        return "admin/result";
    }

    @RequestMapping("save")
    public String saveXiamiSong(SongXiami song, Model model) {
        try {

            if (StringUtils.isBlank(song.getXiamiSongName()) || StringUtils.isBlank(song.getXiamiAlbum())
                    || StringUtils.isBlank(song.getXiamiArtist()) || StringUtils.isBlank(song.getXiamiCoverImg())
                    || song.getDuration() <= 0 || StringUtils.isBlank(song.getFilePath())) {

                model.addAttribute("err_msg", "信息不完整");
                model.addAttribute("xiamiSong", song);
                return "admin/result";
            } else {

                int id = songXiamiDao.addSongXiami(song);

                return "redirect:/admin/xiami/" + id;
            }
        } catch (SQLException e) {
            return "500";
        }
    }

    @RequestMapping("xiami/{id}")
    public String xiamiInfo(@PathVariable int id, Model model) {
        try {
            SongXiami song = songXiamiDao.getSongXiamibyKey(id);
            if (song.getSongId() > 0) {
                Song s = songDao.getSongbyKey(song.getSongId());
                model.addAttribute("song", s);
            }
            model.addAttribute("xiamiSong", song);
            return "admin/result";
        } catch (SQLException e) {
            return "500";
        }
    }

    @RequestMapping("add_song_by_xiami")
    public String addSong(@RequestParam int id, Model model) {
        try {
            SongXiami songSx = songXiamiDao.getSongXiamibyKey(id);

            SongXiami updateSx = new SongXiami();
            updateSx.setId(songSx.getId());

            String fullPath = uploadPath + songSx.getFilePath();
            File f = new File(fullPath);
            String filename = null;
            if (StringUtils.isNotBlank(songSx.getXiamiSongName())) {
                filename = songSx.getXiamiSongName()
                        + (StringUtils.isBlank(songSx.getXiamiArtist()) ? "" : (" - " + songSx.getXiamiArtist()));
            }
            if (StringUtils.isBlank(filename) && StringUtils.isNotBlank(songSx.getName())) {
                filename = songSx.getName()
                        + (StringUtils.isBlank(songSx.getArtist()) ? "" : (" - " + songSx.getArtist()));
            }
            if (StringUtils.isBlank(filename)) {
                filename = songSx.getFileName();
            }
            updateSx.setPcsFileName(filename + ".mp3");

            Song song = new Song();
            song.setAlbum(StringUtils.isBlank(songSx.getXiamiAlbum()) ? songSx.getAlbum() : songSx.getXiamiAlbum());
            song.setArtist(StringUtils.isBlank(songSx.getXiamiArtist()) ? songSx.getArtist() : songSx.getXiamiArtist());
            song.setComposer(StringUtils.isBlank(songSx.getXiamiComposer()) ? songSx.getComposer() : songSx
                    .getXiamiComposer());
            song.setCoverImg(StringUtils.isBlank(songSx.getXiamiCoverImg()) ? songSx.getCoverImg() : songSx
                    .getXiamiCoverImg());
            int now = (int) (System.currentTimeMillis() / 1000L);
            song.setCreateTime(now);
            song.setDuration(songSx.getDuration());
            song.setLyric(songSx.getXiamiLyric());
            song.setLyricist(StringUtils.isBlank(songSx.getXiamiLyricist()) ? songSx.getLyricist() : songSx
                    .getXiamiLyricist());
            song.setName(StringUtils.isBlank(songSx.getXiamiSongName()) ? songSx.getName() : songSx.getXiamiSongName());
            song.setPcsFilename(updateSx.getPcsFileName());
            song.setSxId(songSx.getId());

            logger.info("Start Upload : " + updateSx.getPcsFileName());
            PutObjectRequest request = new PutObjectRequest(bucket, "/" + updateSx.getPcsFileName(), f);
            ObjectMetadata metadata = new ObjectMetadata();
            // metadata.setContentType("text/html");
            request.setMetadata(metadata);
            BaiduBCSResponse<ObjectMetadata> response = baiduBCS.putObject(request);

            int sid = songDao.addSong(song);
            updateSx.setSongId(sid);
            songXiamiDao.updateSongXiami(updateSx);

            return "redirect:/admin/xiami/" + id;
        } catch (SQLException e) {
            return "500";
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
                // byte[] albumImageData = id3v2Tag.getAlbumImage();
                // if (albumImageData != null) {
                // // Write image to file - can determine appropriate
                // // file extension from the mime type
                // String artname = song.getName().toLowerCase().replace(" ",
                // "_") + ".jpg";
                // IOUtils.copy(new ByteArrayInputStream(albumImageData), new
                // FileOutputStream(new File(
                // "D:\\BaiduMusicAIO\\Artwork\\" + artname)));
                // song.setCoverImg(artname);
                // }
            }
        } catch (UnsupportedTagException e) {
            e.printStackTrace();
        } catch (InvalidDataException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String toFirstUpper(String str) {
        if (StringUtils.isNotBlank(str)) {
            char ss[] = str.toCharArray();
            boolean first = true;
            for (int i = 0; i < ss.length; i++) {
                if (ss[i] == ' ') {
                    first = true;
                    continue;
                }
                if (first && CharUtils.isAsciiAlphaLower(ss[i])) {
                    ss[i] = (char) (ss[i] - 32);
                }
                first = false;
            }
            return new String(ss);
        }
        return str;
    }
}
