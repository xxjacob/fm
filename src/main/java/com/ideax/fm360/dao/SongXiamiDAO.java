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
import com.ideax.fm360.pojo.SongXiami;
import com.ideax.fm360.query.SongXiamiQuery;
/**
 * @author xxjacob
 */
@Repository
public class SongXiamiDAO {
	
	@Resource
	SqlMapClient sqlMapClientTemplate;
	
	public Integer addSongXiami(SongXiami songXiami) throws SQLException{
		return (Integer)this.sqlMapClientTemplate.insert("SongXiami.insertSongXiami", songXiami);
	}
	
	public SongXiami getSongXiamibyKey(Integer id) throws SQLException {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("id", id);
		SongXiami result = (SongXiami) this.sqlMapClientTemplate.queryForObject(
				"SongXiami.getSongXiami", params);
		return result;
	}
	
    public List<SongXiami> getSongXiamisByKeys(List<Integer> ids) throws SQLException {
		if (ids.isEmpty()) {
			return Collections.emptyList();
		}
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("keys", ids);
		return  (List<SongXiami>)this.sqlMapClientTemplate.queryForList("SongXiami.getSongXiamisByKeys", params);
	}	
	
	public Integer deleteByKey(Integer id) throws SQLException {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("key", id);
		Integer row = (Integer) this.sqlMapClientTemplate.delete("SongXiami.deleteByKey", params);
		return row;
	}
	
    public Integer deleteByKeys(List<Integer> ids) throws SQLException {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("keys", ids);
		Integer row = (Integer) this.sqlMapClientTemplate.delete("SongXiami.deleteByKeys", params);
		return row;
	}
	
    public Integer updateSongXiami(SongXiami songXiami) throws SQLException{
		return (Integer) this.sqlMapClientTemplate.update("SongXiami.updateSongXiami", songXiami);
	}
	
	/**
	 * 成批更新对象，需要设置对象中的keys。
	 */
    public Integer updateSongXiamisByKeys(SongXiami songXiami) throws SQLException{
		return (Integer) sqlMapClientTemplate.update("SongXiami.updateSongXiamisByKeys", songXiami);
	}

    	
		@SuppressWarnings("unchecked")
    public Result<SongXiami> getSongXiamiListWithPage(SongXiamiQuery songXiamiQuery){
	    Result<SongXiami> rs = new Result<SongXiami>(); 
		try{		
			rs.setCount((Integer) this.sqlMapClientTemplate.queryForObject("SongXiami.getSongXiamiListCount",songXiamiQuery));
			rs.setList((List<SongXiami>)this.sqlMapClientTemplate.queryForList("SongXiami.getSongXiamiListWithPage", songXiamiQuery));
		}catch(SQLException e){
			rs.setSuccess(false);
			rs.setCount(0);
			rs.setList(Collections.EMPTY_LIST);
			rs.setErrorMsg(e.toString());
		}
		return rs;
	}
        
    @SuppressWarnings("unchecked")
    public List<SongXiami> getSongXiamiList(SongXiamiQuery songXiamiQuery) throws SQLException{
		return (List<SongXiami>)this.sqlMapClientTemplate.queryForList("SongXiami.getSongXiamiList", songXiamiQuery);
	}
	
	@SuppressWarnings("unchecked")
    public List<SongXiami> getSongXiamiListWithText(SongXiamiQuery songXiamiQuery) throws SQLException{
		return (List<SongXiami>)this.sqlMapClientTemplate.queryForList("SongXiami.getSongXiamiListWithText", songXiamiQuery);
	}

}
