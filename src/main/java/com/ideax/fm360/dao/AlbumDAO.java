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
import com.ideax.fm360.pojo.Album;
import com.ideax.fm360.query.AlbumQuery;
/**
 * @author xxjacob
 */
@Repository
public class AlbumDAO {
	
	@Resource
	SqlMapClient sqlMapClientTemplate;
	
	public Integer addAlbum(Album album) throws SQLException{
		return (Integer)this.sqlMapClientTemplate.insert("Album.insertAlbum", album);
	}
	
	public Album getAlbumbyKey(Integer aid) throws SQLException {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("aid", aid);
		Album result = (Album) this.sqlMapClientTemplate.queryForObject(
				"Album.getAlbum", params);
		return result;
	}
	
    public List<Album> getAlbumsByKeys(List<Integer> aids) throws SQLException {
		if (aids.isEmpty()) {
			return Collections.emptyList();
		}
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("keys", aids);
		return  (List<Album>)this.sqlMapClientTemplate.queryForList("Album.getAlbumsByKeys", params);
	}	
	
	public Integer deleteByKey(Integer aid) throws SQLException {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("key", aid);
		Integer row = (Integer) this.sqlMapClientTemplate.delete("Album.deleteByKey", params);
		return row;
	}
	
    public Integer deleteByKeys(List<Integer> aids) throws SQLException {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("keys", aids);
		Integer row = (Integer) this.sqlMapClientTemplate.delete("Album.deleteByKeys", params);
		return row;
	}
	
    public Integer updateAlbum(Album album) throws SQLException{
		return (Integer) this.sqlMapClientTemplate.update("Album.updateAlbum", album);
	}
	
	/**
	 * 成批更新对象，需要设置对象中的keys。
	 */
    public Integer updateAlbumsByKeys(Album album) throws SQLException{
		return (Integer) sqlMapClientTemplate.update("Album.updateAlbumsByKeys", album);
	}

    	
		@SuppressWarnings("unchecked")
    public Result<Album> getAlbumListWithPage(AlbumQuery albumQuery){
	    Result<Album> rs = new Result<Album>(); 
		try{		
			rs.setCount((Integer) this.sqlMapClientTemplate.queryForObject("Album.getAlbumListCount",albumQuery));
			rs.setList((List<Album>)this.sqlMapClientTemplate.queryForList("Album.getAlbumListWithPage", albumQuery));
		}catch(SQLException e){
			rs.setSuccess(false);
			rs.setCount(0);
			rs.setList(Collections.EMPTY_LIST);
			rs.setErrorMsg(e.toString());
		}
		return rs;
	}
        
    @SuppressWarnings("unchecked")
    public List<Album> getAlbumList(AlbumQuery albumQuery) throws SQLException{
		return (List<Album>)this.sqlMapClientTemplate.queryForList("Album.getAlbumList", albumQuery);
	}
	
	@SuppressWarnings("unchecked")
    public List<Album> getAlbumListWithText(AlbumQuery albumQuery) throws SQLException{
		return (List<Album>)this.sqlMapClientTemplate.queryForList("Album.getAlbumListWithText", albumQuery);
	}

}
