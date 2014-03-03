package com.ideax.fm360.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ideax.common.exception.EC;
import com.ideax.common.exception.IllegalException;
import com.ideax.fm360.dao.SongDAO;
import com.ideax.fm360.dao.SongListDAO;
import com.ideax.fm360.pojo.Song;
import com.ideax.fm360.pojo.SongList;
import com.ideax.fm360.query.SongQuery;

@Service
public class SongService {

    Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    SongDAO songDao;
    @Autowired
    SongListDAO songListDao;

    public List<Song> getSongs() {
        try {
            return songDao.getSongList(new SongQuery());
        } catch (SQLException e) {
            logger.error("", e);
            throw new IllegalException(EC.EC_DB, "internal error!");
        }
    }

    public Song getSongById(int id) {
        try {
            return songDao.getSongbyKey(id);
        } catch (SQLException e) {
            logger.error("", e);
            throw new IllegalException(EC.EC_DB, "internal error!");
        }
    }

    /**
     * 歌单随机下一首
     * 
     * @param slid
     * @param avoidIds
     * @return
     */
    public Song nextSong(int slid, int[] avoidIds) {
        try {
            SongList songList = songListDao.getSongListbyKey(slid);
            String songstrs = songList.getSongs();
            String ss[] = songstrs.split(",");
            Random r = new Random();
            String songstr = ss[r.nextInt(ss.length)];
            return songDao.getSongbyKey(Integer.parseInt(songstr));
        } catch (SQLException e) {
            logger.error("", e);
            throw new IllegalException(EC.EC_DB, "internal error!");
        }
    }

}
