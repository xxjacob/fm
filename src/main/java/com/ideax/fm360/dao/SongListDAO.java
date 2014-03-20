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
import com.ideax.fm360.pojo.SongList;
import com.ideax.fm360.query.SongListQuery;
/**
 * @author xxjacob
 */
@Repository
public class SongListDAO {
	
	@Resource
	SqlMapClient sqlMapClientTemplate;
	
	public Integer addSongList(SongList songList) throws SQLException{
		return (Integer)this.sqlMapClientTemplate.insert("SongList.insertSongList", songList);
	}
	
	public SongList getSongListbyKey(Integer id) throws SQLException {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("id", id);
		SongList result = (SongList) this.sqlMapClientTemplate.queryForObject(
				"SongList.getSongList", params);
		return result;
	}
	
    public List<SongList> getSongListsByKeys(List<Integer> ids) throws SQLException {
		if (ids.isEmpty()) {
			return Collections.emptyList();
		}
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("keys", ids);
		return  (List<SongList>)this.sqlMapClientTemplate.queryForList("SongList.getSongListsByKeys", params);
	}	
	
	public Integer deleteByKey(Integer id) throws SQLException {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("key", id);
		Integer row = (Integer) this.sqlMapClientTemplate.delete("SongList.deleteByKey", params);
		return row;
	}
	
    public Integer deleteByKeys(List<Integer> ids) throws SQLException {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("keys", ids);
		Integer row = (Integer) this.sqlMapClientTemplate.delete("SongList.deleteByKeys", params);
		return row;
	}
	
    public Integer updateSongList(SongList songList) throws SQLException{
		return (Integer) this.sqlMapClientTemplate.update("SongList.updateSongList", songList);
	}
	
	/**
	 * 成批更新对象，需要设置对象中的keys。
	 */
    public Integer updateSongListsByKeys(SongList songList) throws SQLException{
		return (Integer) sqlMapClientTemplate.update("SongList.updateSongListsByKeys", songList);
	}

    	
		@SuppressWarnings("unchecked")
    public Result<SongList> getSongListListWithPage(SongListQuery songListQuery){
	    Result<SongList> rs = new Result<SongList>(); 
		try{		
			rs.setCount((Integer) this.sqlMapClientTemplate.queryForObject("SongList.getSongListListCount",songListQuery));
			rs.setList((List<SongList>)this.sqlMapClientTemplate.queryForList("SongList.getSongListListWithPage", songListQuery));
		}catch(SQLException e){
			rs.setSuccess(false);
			rs.setCount(0);
			rs.setList(Collections.EMPTY_LIST);
			rs.setErrorMsg(e.toString());
		}
		return rs;
	}
        
    @SuppressWarnings("unchecked")
    public List<SongList> getSongListList(SongListQuery songListQuery) throws SQLException{
		return (List<SongList>)this.sqlMapClientTemplate.queryForList("SongList.getSongListList", songListQuery);
	}
	
	@SuppressWarnings("unchecked")
    public List<SongList> getSongListListWithText(SongListQuery songListQuery) throws SQLException{
		return (List<SongList>)this.sqlMapClientTemplate.queryForList("SongList.getSongListListWithText", songListQuery);
	}

}
