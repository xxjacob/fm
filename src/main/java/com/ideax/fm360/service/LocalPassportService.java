package com.ideax.fm360.service;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.RandomStringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ideax.fm360.dao.UserDAO;
import com.ideax.fm360.pojo.User;

@Service("passportService")
public class LocalPassportService implements IPassportService {

    Logger logger = LoggerFactory.getLogger(this.getClass());

    // local store session
    public static Map<String, User> loginAdmins = new HashMap<String, User>();

    @Autowired
    UserDAO userDao;

    static final String sso_cookie = "xhjuss";

    @Override
    public User getLoginUser(HttpServletRequest request) {
        Cookie cs[] = request.getCookies();
        if (cs != null) {
            for (Cookie c : cs) {
                if (c.getName().equals(sso_cookie)) {
                    String uss = c.getValue();
                    return loginAdmins.get(uss);
                }
            }
        }
        return null;
    }

    @Override
    public void login(User user, HttpServletResponse resp) {
        String sessionid = RandomStringUtils.random(32, true, true);
        loginAdmins.put(sessionid, user);
        Cookie cookie = new Cookie(sso_cookie, sessionid);
        cookie.setMaxAge(3600 * 24 * 10);
        cookie.setPath("/");
        cookie.setDomain("360.fm");
        resp.addCookie(cookie);
    }

}
