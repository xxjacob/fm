package com.ideax.fm360.controller;

import java.io.File;
import java.util.List;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ideax.common.Util;
import com.ideax.fm360.dao.SongDAO;
import com.ideax.fm360.dao.SongXiamiDAO;
import com.ideax.fm360.pojo.Song;
import com.ideax.fm360.pojo.SongXiami;
import com.ideax.fm360.query.SongXiamiQuery;
import com.ideax.fm360.service.AlbumService;
import com.ideax.fm360.service.SongService;

//@Controller
//@RequestMapping("")
public class TestC {

    @Autowired
    SongService songService;

    @Autowired
    AlbumService albumService;

    @Autowired
    SongXiamiDAO songXiamiDao;
    @Autowired
    SongDAO songDao;

    Random random = new Random();

    @RequestMapping("test")
    @ResponseBody
    public Object predict(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        File ppdir = new File("D:\\result");
        File[] dirs = ppdir.listFiles();
        for (File pfile : dirs) {
            if (!pfile.isFile() || !pfile.getName().startsWith("result_"))
                continue;
            System.out.println("################" + pfile.getName());
            String str = FileUtils.readFileToString(pfile, "UTF-8");
            int s = -1, e = -1;
            do {
                s = str.indexOf("{\r\n", s + 1);
                e = str.indexOf("\r\n}", e + 1);
                if (s == -1)
                    break;
                String json = str.substring(s, e + 3);
                try {
                    SongXiami song = Util.mapper.readValue(json, SongXiami.class);
                    songXiamiDao.addSongXiami(song);
                } catch (Exception e1) {
                    System.out.println(json);
                }

            } while (true);
        }
        return 1;
    }

    @RequestMapping("test2")
    @ResponseBody
    public Object two(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        List<SongXiami> sxs = songXiamiDao.getSongXiamiList(new SongXiamiQuery());
        int now = (int)(System.currentTimeMillis()/1000L);
        for (SongXiami sx : sxs) {
            Song song = new Song();
            song.setAlbum(StringUtils.isBlank(sx.getXiamiAlbum()) ? sx.getAlbum() : sx.getXiamiAlbum());
            song.setArtist(StringUtils.isBlank(sx.getXiamiArtist()) ? sx.getArtist() : sx.getXiamiArtist());
            song.setComposer(StringUtils.isBlank(sx.getXiamiComposer()) ? sx.getComposer() : sx.getXiamiComposer());
            song.setCoverImg(StringUtils.isBlank(sx.getXiamiCoverImg()) ? sx.getCoverImg() : sx.getXiamiCoverImg());
            song.setCreateTime(now);
            song.setDuration(sx.getDuration());
            song.setLyric(sx.getXiamiLyric());
            song.setLyricist(StringUtils.isBlank(sx.getXiamiLyricist()) ? sx.getLyricist() : sx.getXiamiLyricist());
            song.setName(StringUtils.isBlank(sx.getName()) ? sx.getName() : sx.getXiamiSongName());
            song.setPcsFilename(sx.getPcsFileName());
            song.setSxId(sx.getId());
            songDao.addSong(song);
        }
        return 2;
    }
}
