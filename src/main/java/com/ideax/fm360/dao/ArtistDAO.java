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
import com.ideax.fm360.pojo.Artist;
import com.ideax.fm360.query.ArtistQuery;
/**
 * @author xxjacob
 */
@Repository
public class ArtistDAO {
	
	@Resource
	SqlMapClient sqlMapClientTemplate;
	
	public Integer addArtist(Artist artist) throws SQLException{
		return (Integer)this.sqlMapClientTemplate.insert("Artist.insertArtist", artist);
	}
	
	public Artist getArtistbyKey(Integer id) throws SQLException {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("id", id);
		Artist result = (Artist) this.sqlMapClientTemplate.queryForObject(
				"Artist.getArtist", params);
		return result;
	}
	
    public List<Artist> getArtistsByKeys(List<Integer> ids) throws SQLException {
		if (ids.isEmpty()) {
			return Collections.emptyList();
		}
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("keys", ids);
		return  (List<Artist>)this.sqlMapClientTemplate.queryForList("Artist.getArtistsByKeys", params);
	}	
	
	public Integer deleteByKey(Integer id) throws SQLException {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("key", id);
		Integer row = (Integer) this.sqlMapClientTemplate.delete("Artist.deleteByKey", params);
		return row;
	}
	
    public Integer deleteByKeys(List<Integer> ids) throws SQLException {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("keys", ids);
		Integer row = (Integer) this.sqlMapClientTemplate.delete("Artist.deleteByKeys", params);
		return row;
	}
	
    public Integer updateArtist(Artist artist) throws SQLException{
		return (Integer) this.sqlMapClientTemplate.update("Artist.updateArtist", artist);
	}
	
	/**
	 * 成批更新对象，需要设置对象中的keys。
	 */
    public Integer updateArtistsByKeys(Artist artist) throws SQLException{
		return (Integer) sqlMapClientTemplate.update("Artist.updateArtistsByKeys", artist);
	}

    	
		@SuppressWarnings("unchecked")
    public Result<Artist> getArtistListWithPage(ArtistQuery artistQuery){
	    Result<Artist> rs = new Result<Artist>(); 
		try{		
			rs.setCount((Integer) this.sqlMapClientTemplate.queryForObject("Artist.getArtistListCount",artistQuery));
			rs.setList((List<Artist>)this.sqlMapClientTemplate.queryForList("Artist.getArtistListWithPage", artistQuery));
		}catch(SQLException e){
			rs.setSuccess(false);
			rs.setCount(0);
			rs.setList(Collections.EMPTY_LIST);
			rs.setErrorMsg(e.toString());
		}
		return rs;
	}
        
    @SuppressWarnings("unchecked")
    public List<Artist> getArtistList(ArtistQuery artistQuery) throws SQLException{
		return (List<Artist>)this.sqlMapClientTemplate.queryForList("Artist.getArtistList", artistQuery);
	}
	
	@SuppressWarnings("unchecked")
    public List<Artist> getArtistListWithText(ArtistQuery artistQuery) throws SQLException{
		return (List<Artist>)this.sqlMapClientTemplate.queryForList("Artist.getArtistListWithText", artistQuery);
	}

}
