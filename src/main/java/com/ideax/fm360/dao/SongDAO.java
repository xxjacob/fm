package com.ideax.fm360.dao;

import java.sql.SQLException;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.ibatis.sqlmap.client.SqlMapClient;
import com.ideax.fm360.common.Result;
import com.ideax.fm360.pojo.Song;
import com.ideax.fm360.query.SongQuery;
/**
 * @author xxjacob
 */
@Repository
public class SongDAO {
	
	@Resource
	SqlMapClient sqlMapClientTemplate;
	
	public Integer addSong(Song song) throws SQLException{
		return (Integer)this.sqlMapClientTemplate.insert("Song.insertSong", song);
	}
	
	public Song getSongbyKey(Integer id) throws SQLException {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("id", id);
		Song result = (Song) this.sqlMapClientTemplate.queryForObject(
				"Song.getSong", params);
		return result;
	}
	
    public List<Song> getSongsByKeys(List<Integer> ids) throws SQLException {
		if (ids.isEmpty()) {
			return Collections.emptyList();
		}
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("keys", ids);
		return  (List<Song>)this.sqlMapClientTemplate.queryForList("Song.getSongsByKeys", params);
	}	
	
	public Integer deleteByKey(Integer id) throws SQLException {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("key", id);
		Integer row = (Integer) this.sqlMapClientTemplate.delete("Song.deleteByKey", params);
		return row;
	}
	
    public Integer deleteByKeys(List<Integer> ids) throws SQLException {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("keys", ids);
		Integer row = (Integer) this.sqlMapClientTemplate.delete("Song.deleteByKeys", params);
		return row;
	}
	
    public Integer updateSong(Song song) throws SQLException{
		return (Integer) this.sqlMapClientTemplate.update("Song.updateSong", song);
	}
	
	/**
	 * 成批更新对象，需要设置对象中的keys。
	 */
    public Integer updateSongsByKeys(Song song) throws SQLException{
		return (Integer) sqlMapClientTemplate.update("Song.updateSongsByKeys", song);
	}

    	
		@SuppressWarnings("unchecked")
    public Result<Song> getSongListWithPage(SongQuery songQuery){
	    Result<Song> rs = new Result<Song>(); 
		try{		
			rs.setCount((Integer) this.sqlMapClientTemplate.queryForObject("Song.getSongListCount",songQuery));
			rs.setList((List<Song>)this.sqlMapClientTemplate.queryForList("Song.getSongListWithPage", songQuery));
		}catch(SQLException e){
			rs.setSuccess(false);
			rs.setCount(0);
			rs.setList(Collections.EMPTY_LIST);
			rs.setErrorMsg(e.toString());
		}
		return rs;
	}
        
    @SuppressWarnings("unchecked")
    public List<Song> getSongList(SongQuery songQuery) throws SQLException{
		return (List<Song>)this.sqlMapClientTemplate.queryForList("Song.getSongList", songQuery);
	}
	
	@SuppressWarnings("unchecked")
    public List<Song> getSongListWithText(SongQuery songQuery) throws SQLException{
		return (List<Song>)this.sqlMapClientTemplate.queryForList("Song.getSongListWithText", songQuery);
	}

}
