package com.ideax.fm360.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.databind.JsonNode;
import com.ideax.common.Util;
import com.ideax.common.exception.EC;
import com.ideax.common.exception.IllegalException;
import com.ideax.fm360.constant.Const;
import com.ideax.fm360.pojo.SongList;
import com.ideax.fm360.pojo.User;
import com.ideax.fm360.service.AlbumService;
import com.ideax.fm360.service.IPassportService;
import com.ideax.fm360.service.SongListService;
import com.ideax.fm360.service.SongService;
import com.ideax.fm360.service.UserService;

@Controller
public class UserC {

    Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    SongService songService;

    @Autowired
    AlbumService albumService;

    @Autowired
    UserService userService;

    @Autowired
    SongListService songListService;

    @Autowired
    IPassportService passportService;

    @Value("${open.qq.client_id}")
    String clientId;

    @Value("${open.qq.client_secret}")
    String clientSecret;

    @Value("${open.qq.redirect_uri}")
    String redirectUri;

    @RequestMapping("dologin")
    @ResponseBody
    public Object login(@RequestParam String email, @RequestParam String password, HttpServletResponse resp) {
        Map<String, Object> result = new HashMap<String, Object>();
        result.put("err_no", EC.OK);
        try {
            User user = userService.getUserByNamePwd(email, Util.md5Encoding(password));
            if (user == null) {
                result.put("login", false);
                return result;
            }
            passportService.login(user, resp);
            result.put("login", true);
            result.put("user", user);
            result.put("playLists", songListService.getSongListsByUser(user.getId()));
        } catch (IllegalException e) {
            result.put("err_no", e.getErrorCode());
        }
        return result;
    }

    @RequestMapping("my")
    @ResponseBody
    public Object myInfo(HttpServletRequest req, HttpServletResponse resp) {
        Map<String, Object> result = new HashMap<String, Object>();
        result.put("err_no", EC.OK);
        User user = passportService.getLoginUser(req);
        if (user == null) {
            result.put("login", false);
            return result;
        }
        result.put("login", true);
        result.put("user", user);
        List<SongList> list = songListService.getSongListsByUser(user.getId());
        result.put("playLists", list);
        return result;
    }

    @RequestMapping("/3rd/success")
    @ResponseBody
    public String thirdPatrySuccess(@RequestParam String code, HttpServletResponse response) {
        Map<String, String> params = new HashMap<String, String>();
        params.put("grant_type", "authorization_code");
        params.put("client_id", clientId);
        params.put("client_secret", clientSecret);
        params.put("code", code);
        params.put("state", "login");
        params.put("redirect_uri", redirectUri);
        String str = Util.getHttpResponseString("https://graph.qq.com/oauth2.0/token", params);
        if (str == null) {
            return "ungeilivable! cannot connet to qq!";
        }
        String accessToken = null;
        for (String s : str.split("&")) {
            String kvs[] = s.split("=");
            if (kvs.length == 2) {
                if ("access_token".equals(kvs[0])) {
                    accessToken = kvs[1];
                }
            }
        }
        if (accessToken == null) {
            logger.error("get access_token error response:" + str);
            return "get access_token error response:" + str;
        }
        params.clear();
        params.put("access_token", accessToken);
        String openidStr = Util.getHttpResponseString("https://graph.qq.com/oauth2.0/me", params);
        if (openidStr == null) {
            return "ungeilivable! cannot connet to qq!";
        }

        String openid = null;
        int oi = openidStr.indexOf("\"openid\"");

        if (oi < 0) {
            logger.error("get openid error response:" + oi);
            return "get openid error response:" + openidStr;
        }
        int start = openidStr.indexOf('"', oi + "\"openid\"".length());
        if (start < 0) {
            logger.error("get openid error response:" + start);
            return "get openid error response:" + openidStr;
        }
        int end = openidStr.indexOf('"', start + 1);
        if (end <= start) {
            logger.error("get openid error response:" + oi + start + end);
            return "get openid error response:" + openidStr;
        }

        openid = openidStr.substring(start + 1, end).trim();

        // whether user exists
        User u = userService.getUserByOpenid(Const.PLATFORM_QQ, openid);
        if (u == null) {
            params.clear();
            params.put("access_token", accessToken);
            params.put("oauth_consumer_key", clientId);
            params.put("openid", openid);
            String userinfoStr = Util.getHttpResponseString("https://graph.qq.com/user/get_user_info", params);
            if (userinfoStr == null) {
                return "ungeilivable! cannot connet to qq!";
            }

            JsonNode node;
            try {
                node = Util.mapper.readTree(userinfoStr);
            } catch (Exception e) {
                return "ungeilivable! qq!" + userinfoStr;
            }
            int ret = node.get("ret").asInt();
            if (ret != 0) {
                return "ungeilivable!  qq return error!";
            } else {
                u = new User();
                u.setNickname(node.get("nickname").asText());
                u.setQqId(openid);
                u.setFigureurl(node.get("figureurl_qq_1").asText());
                u.setFigureurl2(node.get("figureurl_2").asText());
                u.setGender("男".equals(node.get("gender").asText()) ? (byte) 0 : (byte) 1);
                u.setQqToken(accessToken);
                u.setQqId(openid);
                u.setQqTokenTime((int) (System.currentTimeMillis() / 1000L));
                int id = userService.addUser(u);
                u.setId(id);

                // 新建 thumb列表
                SongList up = new SongList();
                up.setUid(u.getId());
                up.setName("Thumbups");
                up.setType(Const.SONG_LIST_TYPE_THUMBUP);
                songListService.addSongList(up);

                SongList down = new SongList();
                down.setUid(u.getId());
                down.setName("~Thumbdowns");
                down.setType(Const.SONG_LIST_TYPE_THUMBDOWN);
                songListService.addSongList(down);
            }
        } else {
            User update = new User();
            update.setQqToken(accessToken);
            update.setQqTokenTime((int) (System.currentTimeMillis() / 1000L));
            update.setId(u.getId());
            userService.updateUser(update);
        }

        response.setContentType("text/html");
        passportService.login(u, response);

        return "<!DOCTYPE html><html><body>login success , closing ...</body><script>window.opener.login();window.close()</script></html>";
    }

    @RequestMapping("register")
    @ResponseBody
    public Object register(@RequestParam String email, @RequestParam String password, HttpServletResponse resp) {
        Map<String, Object> result = new HashMap<String, Object>();
        result.put("err_no", EC.OK);
        try {
            User user = userService.getUserByNamePwd(email, Util.md5Encoding(password));
            if (user == null) {
                result.put("login", false);
                return result;
            }
            passportService.login(user, resp);
            result.put("login", true);
            result.put("user", user);
            result.put("playLists", songListService.getSongListsByUser(user.getId()));
        } catch (IllegalException e) {
            result.put("err_no", e.getErrorCode());
        }
        return result;
    }
}
