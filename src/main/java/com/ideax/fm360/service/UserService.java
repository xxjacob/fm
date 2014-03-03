package com.ideax.fm360.service;

import java.sql.SQLException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ideax.common.exception.EC;
import com.ideax.common.exception.IllegalException;
import com.ideax.fm360.constant.Const;
import com.ideax.fm360.dao.RedisAccess;
import com.ideax.fm360.dao.SongDAO;
import com.ideax.fm360.dao.SongListDAO;
import com.ideax.fm360.dao.UserDAO;
import com.ideax.fm360.pojo.SongList;
import com.ideax.fm360.pojo.User;
import com.ideax.fm360.query.SongListQuery;
import com.ideax.fm360.query.UserQuery;

@Service
public class UserService {

    Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    SongDAO songDao;

    @Autowired
    UserDAO userDao;

    @Autowired
    SongListDAO songListDao;

    @Autowired
    RedisAccess redisAccess;

    /**
     * 赞或是踩
     * 
     * @param sid
     * @param user
     * @param up
     */
    public void thumbup(int sid, User user) {
        try {
            List<SongList> list = songListDao.getSongListListWithText(new SongListQuery().setUid(user.getId()).setType(
                    Const.SONG_LIST_TYPE_THUMBUP));
            if (list == null || list.size() == 0) {
                SongList newlist = new SongList();
                newlist.setName("thumbup list");
                newlist.setSongs(sid + "");
                newlist.setUid(user.getId());
                newlist.setType(Const.SONG_LIST_TYPE_THUMBUP);
                songListDao.addSongList(newlist);
            } else {
                SongList sl = list.get(0);
                SongList update = new SongList();
                update.setId(sl.getId());
                update.setSongs(sl.getSongs() + "," + sid);
                songListDao.updateSongList(update);
            }
            // TODO 这首歌的赞数+1
        } catch (SQLException e) {
            logger.error("", e);
            throw new IllegalException(EC.EC_DB, "internal error!");
        }
    }

    public void thumbdown(int sid, User user) {
        try {
            List<SongList> list = songListDao.getSongListListWithText(new SongListQuery().setUid(user.getId()).setType(
                    Const.SONG_LIST_TYPE_THUNBDOWN));
            if (list == null || list.size() == 0) {
                SongList newlist = new SongList();
                newlist.setName("thumbup list");
                newlist.setSongs(sid + "");
                newlist.setUid(user.getId());
                newlist.setType(Const.SONG_LIST_TYPE_THUNBDOWN);
                songListDao.addSongList(newlist);
            } else {
                SongList sl = list.get(0);
                SongList update = new SongList();
                update.setId(sl.getId());
                update.setSongs(sl.getSongs() + "," + sid);
                songListDao.updateSongList(update);
            }
        } catch (SQLException e) {
            logger.error("", e);
            throw new IllegalException(EC.EC_DB, "internal error!");
        }
    }

    /**
     * 获取某个用户对某首歌的状态
     * 
     * @param sid
     * @return
     */
    public int thumbInfo(int sid, User user) {
        List<SongList> songlist = this.getSongListByUidType(user.getId(), (byte) 0);
        if (songlist != null && songlist.size() > 0)
            for (SongList sl : songlist) {
                if (sl.getType() == Const.SONG_LIST_TYPE_THUNBDOWN) {
                    if (sl.getSongs().contains(String.valueOf(sid)))
                        return Const.THUMB_DOWN;
                } else if (sl.getType() == Const.SONG_LIST_TYPE_THUMBUP) {
                    if (sl.getSongs().contains(String.valueOf(sid)))
                        return Const.THUMB_UP;
                }
            }
        return Const.THUMB_NO;
    }

    /**
     * 获取用户的歌单
     */
    public List<SongList> getUserPlayLists(int uid) {
        try {
            return songListDao.getSongListList(new SongListQuery().setUid(uid));
        } catch (SQLException e) {
            logger.error("", e);
            throw new IllegalException(EC.EC_DB, "internal error!");
        }
    }

    /**
     * for login
     */
    public User getUserByNamePwd(String name, String pwd) {
        try {
            List<User> us = userDao.getUserList(new UserQuery().setEmail(name).setPassword(pwd));
            if (us != null && us.size() > 0) {
                return us.get(0);
            }
            return null;
        } catch (SQLException e) {
            logger.error("", e);
            throw new IllegalException(EC.EC_DB, "internal error!");
        }
    }

    /*
     * ----------------------------------------------------------------
     * --------------------------private ------------------------------
     * ----------------------------------------------------------------
     */
    /**
     * 获取songlist
     * 
     * @param uid
     * @param type
     * @return
     */
    private List<SongList> getSongListByUidType(int uid, byte type) {
        try {
            List<SongList> songlist = songListDao.getSongListListWithText(new SongListQuery().setUid(uid).setType(
                    type > 0 ? type : null));
            return songlist;
        } catch (SQLException e) {
            logger.error("", e);
            throw new IllegalException(EC.EC_DB, "internal error!");
        }
    }
}
