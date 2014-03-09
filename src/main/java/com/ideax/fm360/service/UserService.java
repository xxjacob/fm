package com.ideax.fm360.service;

import java.sql.SQLException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ideax.common.exception.EC;
import com.ideax.common.exception.IllegalException;
import com.ideax.fm360.dao.SongDAO;
import com.ideax.fm360.dao.SongListDAO;
import com.ideax.fm360.dao.UserDAO;
import com.ideax.fm360.pojo.User;
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

    /**
     * get
     */
    public User getUserByOpenid(String platform, String id) {
        try {
            UserQuery q = new UserQuery();
            if (platform.equals("qq")) {
                q.setQqId(id);
            } else {
                return null;
            }
            List<User> us = userDao.getUserList(q);
            if (us != null && us.size() > 0) {
                return us.get(0);
            }
            return null;
        } catch (SQLException e) {
            logger.error("", e);
            throw new IllegalException(EC.EC_DB, "internal error!");
        }
    }

    /**
     * get
     */
    public int addUser(User newuser) {
        try {
            return userDao.addUser(newuser);
        } catch (SQLException e) {
            logger.error("", e);
            throw new IllegalException(EC.EC_DB, "internal error!");
        }
    }

    public int updateUser(User update) {
        try {
            return userDao.updateUser(update);
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
}
