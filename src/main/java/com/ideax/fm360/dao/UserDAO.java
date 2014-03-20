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
import com.ideax.fm360.pojo.User;
import com.ideax.fm360.query.UserQuery;
/**
 * @author xxjacob
 */
@Repository
public class UserDAO {
	
	@Resource
	SqlMapClient sqlMapClientTemplate;
	
	public Integer addUser(User user) throws SQLException{
		return (Integer)this.sqlMapClientTemplate.insert("User.insertUser", user);
	}
	
	public User getUserbyKey(Integer id) throws SQLException {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("id", id);
		User result = (User) this.sqlMapClientTemplate.queryForObject(
				"User.getUser", params);
		return result;
	}
	
    public List<User> getUsersByKeys(List<Integer> ids) throws SQLException {
		if (ids.isEmpty()) {
			return Collections.emptyList();
		}
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("keys", ids);
		return  (List<User>)this.sqlMapClientTemplate.queryForList("User.getUsersByKeys", params);
	}	
	
	public Integer deleteByKey(Integer id) throws SQLException {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("key", id);
		Integer row = (Integer) this.sqlMapClientTemplate.delete("User.deleteByKey", params);
		return row;
	}
	
    public Integer deleteByKeys(List<Integer> ids) throws SQLException {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("keys", ids);
		Integer row = (Integer) this.sqlMapClientTemplate.delete("User.deleteByKeys", params);
		return row;
	}
	
    public Integer updateUser(User user) throws SQLException{
		return (Integer) this.sqlMapClientTemplate.update("User.updateUser", user);
	}
	
	/**
	 * 成批更新对象，需要设置对象中的keys。
	 */
    public Integer updateUsersByKeys(User user) throws SQLException{
		return (Integer) sqlMapClientTemplate.update("User.updateUsersByKeys", user);
	}

    	
		@SuppressWarnings("unchecked")
    public Result<User> getUserListWithPage(UserQuery userQuery){
	    Result<User> rs = new Result<User>(); 
		try{		
			rs.setCount((Integer) this.sqlMapClientTemplate.queryForObject("User.getUserListCount",userQuery));
			rs.setList((List<User>)this.sqlMapClientTemplate.queryForList("User.getUserListWithPage", userQuery));
		}catch(SQLException e){
			rs.setSuccess(false);
			rs.setCount(0);
			rs.setList(Collections.EMPTY_LIST);
			rs.setErrorMsg(e.toString());
		}
		return rs;
	}
        
    @SuppressWarnings("unchecked")
    public List<User> getUserList(UserQuery userQuery) throws SQLException{
		return (List<User>)this.sqlMapClientTemplate.queryForList("User.getUserList", userQuery);
	}
	
	@SuppressWarnings("unchecked")
    public List<User> getUserListWithText(UserQuery userQuery) throws SQLException{
		return (List<User>)this.sqlMapClientTemplate.queryForList("User.getUserListWithText", userQuery);
	}

}
