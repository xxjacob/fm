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
import com.ideax.fm360.pojo.SongListItem;
import com.ideax.fm360.query.SongListItemQuery;
/**
 * @author xxjacob
 */
@Repository
public class SongListItemDAO {
	
	@Resource
	SqlMapClient sqlMapClientTemplate;
	
	public Integer addSongListItem(SongListItem songListItem) throws SQLException{
		return (Integer)this.sqlMapClientTemplate.insert("SongListItem.insertSongListItem", songListItem);
	}
	
	public SongListItem getSongListItembyKey(Integer id) throws SQLException {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("id", id);
		SongListItem result = (SongListItem) this.sqlMapClientTemplate.queryForObject(
				"SongListItem.getSongListItem", params);
		return result;
	}
	
    public List<SongListItem> getSongListItemsByKeys(List<Integer> ids) throws SQLException {
		if (ids.isEmpty()) {
			return Collections.emptyList();
		}
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("keys", ids);
		return  (List<SongListItem>)this.sqlMapClientTemplate.queryForList("SongListItem.getSongListItemsByKeys", params);
	}	
	
	public Integer deleteByKey(Integer id) throws SQLException {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("key", id);
		Integer row = (Integer) this.sqlMapClientTemplate.delete("SongListItem.deleteByKey", params);
		return row;
	}
	
    public Integer deleteByKeys(List<Integer> ids) throws SQLException {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("keys", ids);
		Integer row = (Integer) this.sqlMapClientTemplate.delete("SongListItem.deleteByKeys", params);
		return row;
	}
	
    public Integer updateSongListItem(SongListItem songListItem) throws SQLException{
		return (Integer) this.sqlMapClientTemplate.update("SongListItem.updateSongListItem", songListItem);
	}
	
	/**
	 * 成批更新对象，需要设置对象中的keys。
	 */
    public Integer updateSongListItemsByKeys(SongListItem songListItem) throws SQLException{
		return (Integer) sqlMapClientTemplate.update("SongListItem.updateSongListItemsByKeys", songListItem);
	}

    	
		@SuppressWarnings("unchecked")
    public Result<SongListItem> getSongListItemListWithPage(SongListItemQuery songListItemQuery){
	    Result<SongListItem> rs = new Result<SongListItem>(); 
		try{		
			rs.setCount((Integer) this.sqlMapClientTemplate.queryForObject("SongListItem.getSongListItemListCount",songListItemQuery));
			rs.setList((List<SongListItem>)this.sqlMapClientTemplate.queryForList("SongListItem.getSongListItemListWithPage", songListItemQuery));
		}catch(SQLException e){
			rs.setSuccess(false);
			rs.setCount(0);
			rs.setList(Collections.EMPTY_LIST);
			rs.setErrorMsg(e.toString());
		}
		return rs;
	}
        
    @SuppressWarnings("unchecked")
    public List<SongListItem> getSongListItemList(SongListItemQuery songListItemQuery) throws SQLException{
		return (List<SongListItem>)this.sqlMapClientTemplate.queryForList("SongListItem.getSongListItemList", songListItemQuery);
	}
	
	@SuppressWarnings("unchecked")
    public List<SongListItem> getSongListItemListWithText(SongListItemQuery songListItemQuery) throws SQLException{
		return (List<SongListItem>)this.sqlMapClientTemplate.queryForList("SongListItem.getSongListItemListWithText", songListItemQuery);
	}

}
