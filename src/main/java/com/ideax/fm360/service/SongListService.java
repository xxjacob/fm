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
import com.ideax.fm360.dao.SongDAO;
import com.ideax.fm360.dao.SongListDAO;
import com.ideax.fm360.dao.SongListItemDAO;
import com.ideax.fm360.dao.UserDAO;
import com.ideax.fm360.pojo.SongList;
import com.ideax.fm360.pojo.SongListItem;
import com.ideax.fm360.query.SongListItemQuery;
import com.ideax.fm360.query.SongListQuery;

@Service
public class SongListService {

    Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    SongDAO songDao;

    @Autowired
    UserDAO userDao;

    @Autowired
    SongListDAO songListDao;
    @Autowired
    SongListItemDAO songListItemDAO;

    /**
     * 赞或取消赞
     * 
     * @param sid
     * @param user
     * @param up
     * @return 0,现在的顶踩的状态 1，顶的数量 (-1没变) 2，踩数量 (-1没变)
     */
    public int[] thumbup(int sid, int uid) {
        int[] ret = new int[] { 0, -1, -1 };
        try {
            List<SongListItem> list = songListItemDAO.getSongListItemList(new SongListItemQuery().setUid(uid).setSid(
                    sid));
            boolean cancel = false;
            boolean deleteDown = false;
            if (list != null && list.size() > 0) {
                for (SongListItem item : list) {
                    if (item.getType() == Const.SONG_LIST_TYPE_THUMBUP) {
                        songListItemDAO.deleteByKey(item.getId()); // 取消赞
                        cancel = true;
                    } else if (item.getType() == Const.SONG_LIST_TYPE_THUMBDOWN) {
                        songListItemDAO.deleteByKey(item.getId());
                        deleteDown = true;
                    }
                }
            }
            SongList sl = this.getThumbupList(uid);
            if (!cancel) {
                SongListItem songListItem = new SongListItem();
                songListItem.setUid(uid);
                songListItem.setSid(sid);
                songListItem.setType(Const.SONG_LIST_TYPE_THUMBUP);
                songListItemDAO.addSongListItem(songListItem);
                // 红心歌曲数+1
                if (sl != null) {
                    SongList update = new SongList();
                    update.setId(sl.getId());
                    update.setSongCount(sl.getSongCount() + 1);
                    songListDao.updateSongList(update);
                    ret[1] = update.getSongCount();
                }
                ret[0] = Const.THUMB_UP;
            } else {
                // 红心歌曲数-1
                if (sl != null) {
                    SongList update = new SongList();
                    update.setId(sl.getId());
                    update.setSongCount(sl.getSongCount() - 1);
                    songListDao.updateSongList(update);
                    ret[1] = update.getSongCount();
                }
                ret[0] = Const.THUMB_NO;
            }
            if (deleteDown) {
                // 踩数减一
                SongList down = this.getThumbdownList(uid);
                if (down != null) {
                    SongList update = new SongList();
                    update.setId(down.getId());
                    update.setSongCount(down.getSongCount() - 1);
                    songListDao.updateSongList(update);
                    ret[2] = update.getSongCount();
                }
            }
            return ret;
        } catch (SQLException e) {
            logger.error("", e);
            throw new IllegalException(EC.EC_DB, "internal error!");
        }
    }

    public int[] thumbdown(int sid, int uid) {
        int[] ret = new int[] { 0, -1, -1 };
        try {
            List<SongListItem> list = songListItemDAO.getSongListItemList(new SongListItemQuery().setUid(uid).setSid(
                    sid));
            boolean cancel = false;
            boolean deleteUp = false;
            if (list != null && list.size() > 0) {
                for (SongListItem item : list) {
                    if (item.getType() == Const.SONG_LIST_TYPE_THUMBDOWN) {
                        songListItemDAO.deleteByKey(item.getId()); // 取消down
                        cancel = true;
                    } else if (item.getType() == Const.SONG_LIST_TYPE_THUMBUP) {
                        songListItemDAO.deleteByKey(item.getId());
                        deleteUp = true;
                    }
                }
            }
            SongList down = this.getThumbdownList(uid);
            if (!cancel) {
                SongListItem songListItem = new SongListItem();
                songListItem.setUid(uid);
                songListItem.setSid(sid);
                songListItem.setType(Const.SONG_LIST_TYPE_THUMBDOWN);
                songListItemDAO.addSongListItem(songListItem);
                // down数+1
                if (down != null) {
                    SongList update = new SongList();
                    update.setId(down.getId());
                    update.setSongCount(down.getSongCount() + 1);
                    songListDao.updateSongList(update);
                    ret[2] = update.getSongCount();
                }
                ret[0] = Const.THUMB_DOWN;
            } else {
                // down歌曲数-1
                if (down != null) {
                    SongList update = new SongList();
                    update.setId(down.getId());
                    update.setSongCount(down.getSongCount() - 1);
                    songListDao.updateSongList(update);
                    ret[2] = update.getSongCount();
                }
                ret[0] = Const.THUMB_NO;
            }
            if (deleteUp) {
                // up数减一
                SongList up = this.getThumbupList(uid);
                if (up != null) {
                    SongList update = new SongList();
                    update.setId(up.getId());
                    update.setSongCount(up.getSongCount() - 1);
                    songListDao.updateSongList(update);
                    ret[1] = update.getSongCount();
                }
            }
            return ret;
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
    public List<SongListItem> thumbInfo(int sid, int uid) {
        try {
            return songListItemDAO.getSongListItemList(new SongListItemQuery().setUid(uid).setSid(sid));
        } catch (SQLException e) {
            logger.error("", e);
            throw new IllegalException(EC.EC_DB, "internal error!");
        }
    }

    /**
     * 添加歌单
     * 
     * @param sl
     * @return
     */
    public int addSongList(SongList sl) {
        try {
            return songListDao.addSongList(sl);
        } catch (SQLException e) {
            logger.error("", e);
            throw new IllegalException(EC.EC_DB, "internal error!");
        }
    }

    /**
     * 获取用户的歌单
     */
    public List<SongList> getSongListsByUser(int uid) {
        try {
            return songListDao.getSongListList(new SongListQuery().setUid(uid));
        } catch (SQLException e) {
            logger.error("", e);
            throw new IllegalException(EC.EC_DB, "internal error!");
        }
    }

    /*
     * --------------------private
     */

    public SongList getThumbupList(int uid) {
        List<SongList> songlist;
        try {
            songlist = songListDao.getSongListList(new SongListQuery().setUid(uid)
                    .setType(Const.SONG_LIST_TYPE_THUMBUP));

            if (songlist != null && songlist.size() > 0) {
                return songlist.get(0);
            }
            return null;
        } catch (SQLException e) {
            logger.error("", e);
            throw new IllegalException(EC.EC_DB, "internal error!");
        }
    }

    public SongList getThumbdownList(int uid) {
        List<SongList> songlist;
        try {
            songlist = songListDao.getSongListList(new SongListQuery().setUid(uid).setType(
                    Const.SONG_LIST_TYPE_THUMBDOWN));

            if (songlist != null && songlist.size() > 0) {
                return songlist.get(0);
            }
            return null;
        } catch (SQLException e) {
            logger.error("", e);
            throw new IllegalException(EC.EC_DB, "internal error!");
        }
    }
}
