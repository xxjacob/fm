package com.ideax.fm360.service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang.math.RandomUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ideax.common.exception.EC;
import com.ideax.common.exception.IllegalException;
import com.ideax.fm360.constant.Const;
import com.ideax.fm360.dao.SongDAO;
import com.ideax.fm360.dao.SongListDAO;
import com.ideax.fm360.dao.SongListItemDAO;
import com.ideax.fm360.pojo.Song;
import com.ideax.fm360.pojo.SongList;
import com.ideax.fm360.pojo.SongListItem;
import com.ideax.fm360.query.SongListItemQuery;
import com.ideax.fm360.query.SongQuery;

@Service
public class SongService implements InitializingBean {

    Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    SongDAO songDao;
    @Autowired
    SongListDAO songListDao;
    @Autowired
    SongListItemDAO songListItemDAO;

    public List<Integer> songsAll = new ArrayList<Integer>();

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
    public Song nextSong(int uid, int slid, int[] avoidIds) {
        try {
            if (uid > 0 && slid > 0) {
                SongList songlist = songListDao.getSongListbyKey(slid);
                if (songlist.getType() == Const.SONG_LIST_TYPE_THUMBDOWN) {
                    // 没踩过的所有歌曲
                    List<SongListItem> list = songListItemDAO.getSongListItemList(new SongListItemQuery().setUid(uid)
                            .setListId(slid));
                    Set<Integer> ss = new HashSet<Integer>();
                    if (list != null && list.size() > 0) {
                        for (SongListItem s : list) {
                            ss.add(s.getId());
                        }
                    }
                    int id = 0;
                    for (int i = 0; i < 10; i++) {
                        id = songsAll.get(RandomUtils.nextInt(songsAll.size()));
                        if (!ss.contains(id))
                            break;
                    }
                    return songDao.getSongbyKey(id);
                } else {
                    List<SongListItem> list = songListItemDAO.getSongListItemList(new SongListItemQuery().setUid(uid)
                            .setListId(slid));
                    if (list != null && list.size() > 0) {
                        SongListItem item = list.get(RandomUtils.nextInt(list.size()));
                        return songDao.getSongbyKey(item.getSid());
                    }
                }
            }
            //
            int id = songsAll.get(RandomUtils.nextInt(songsAll.size()));
            return songDao.getSongbyKey(id);
        } catch (SQLException e) {
            logger.error("", e);
            throw new IllegalException(EC.EC_DB, "internal error!");
        }
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        // 先这样吧。。。
        List<Song> all = songDao.getSongList(new SongQuery());
        for (Song s : all) {
            songsAll.add(s.getId());
        }
    }

}
