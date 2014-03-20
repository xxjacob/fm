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
	 * @return 0,现在的顶踩的状态 ; 1-3，各个操作(顶、踩、tired)的数量
	 *         (-1没变),下标对应Const.SONG_LIST_TYPE_XXX
	 */
	public int[] action(int sid, int uid, byte type) {
		int[] ret = new int[] { 0, -1, -1, -1 };
		try {
			List<SongListItem> list = songListItemDAO.getSongListItemList(new SongListItemQuery().setUid(uid).setSid(
					sid));
			boolean cancel = false;
			boolean[] deletes = new boolean[6];
			if (list != null && list.size() > 0) {
				for (SongListItem item : list) {
					// 如果之前已经有，那么本次是撤销操作
					if (item.getType() == type) {
						songListItemDAO.deleteByKey(item.getId()); // 取消赞
						cancel = true;
					}
					// 删除其他状态
					else if (item.getType() == Const.SONG_LIST_TYPE_THUMBDOWN
							|| item.getType() == Const.SONG_LIST_TYPE_THUMBUP
							|| item.getType() == Const.SONG_LIST_TYPE_TIRED) {
						songListItemDAO.deleteByKey(item.getId());
						deletes[item.getType()] = true;
					}
				}
			}
			SongList sl = this.getSongListByUidType(uid, type);
			if (sl == null) {
				sl = new SongList();
				sl.setUid(uid);
				sl.setType(type);
				sl.setName(Const.SONG_LIST_NAMES[type]);
				sl.setSongCount(0);
				int id = songListDao.addSongList(sl);
				sl.setId(id);
			}
			if (!cancel) {
				SongListItem songListItem = new SongListItem();
				songListItem.setListId(sl.getId());
				songListItem.setUid(uid);
				songListItem.setSid(sid);
				songListItem.setType(type);
				songListItemDAO.addSongListItem(songListItem);
				// 该操作数+1
				if (sl != null) {
					SongList update = new SongList();
					update.setId(sl.getId());
					update.setSongCount(sl.getSongCount() + 1);
					songListDao.updateSongList(update);
					ret[type] = update.getSongCount();
				}
				ret[0] = type;
			} else {
				// 该操作数数-1
				if (sl != null) {
					SongList update = new SongList();
					update.setId(sl.getId());
					update.setSongCount(sl.getSongCount() - 1);
					songListDao.updateSongList(update);
					ret[type] = update.getSongCount();
				}
				ret[0] = Const.THUMB_NO;
			}
			for (byte sltype = 0; sltype < deletes.length; sltype++) {
				if (deletes[sltype]) {
					// 原来的操作数减一
					SongList down = this.getSongListByUidType(uid, sltype);
					if (down != null) {
						SongList update = new SongList();
						update.setId(down.getId());
						update.setSongCount(down.getSongCount() - 1);
						songListDao.updateSongList(update);
						ret[sltype] = update.getSongCount();
					}
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
			return songListDao.getSongListList(new SongListQuery().setUid(uid).orderbyType(true));
		} catch (SQLException e) {
			logger.error("", e);
			throw new IllegalException(EC.EC_DB, "internal error!");
		}
	}

	/*
	 * --------------------private
	 */

	public SongList getSongListByUidType(int uid, byte type) {
		List<SongList> songlist;
		try {
			songlist = songListDao.getSongListList(new SongListQuery().setUid(uid).setType(type));
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
