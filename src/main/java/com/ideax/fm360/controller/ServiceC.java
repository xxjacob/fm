package com.ideax.fm360.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ideax.common.ParamUtils;
import com.ideax.common.Util;
import com.ideax.common.exception.EC;
import com.ideax.common.exception.IllegalException;
import com.ideax.fm360.constant.Const;
import com.ideax.fm360.pojo.Song;
import com.ideax.fm360.pojo.User;
import com.ideax.fm360.service.AlbumService;
import com.ideax.fm360.service.IPassportService;
import com.ideax.fm360.service.SongService;
import com.ideax.fm360.service.UserService;

@Controller
@RequestMapping("fm")
public class ServiceC {

    @Autowired
    SongService songService;

    @Autowired
    AlbumService albumService;

    @Autowired
    UserService userService;
    @Autowired
    IPassportService passportService;

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
        result.put("playLists", userService.getUserPlayLists(user.getId()));
        return result;
    }

    @RequestMapping("login")
    @ResponseBody
    public Object login(@RequestParam String username, @RequestParam String pwd, HttpServletResponse resp) {
        Map<String, Object> result = new HashMap<String, Object>();
        result.put("err_no", EC.OK);
        User user = userService.getUserByNamePwd(username, Util.md5Encoding(pwd));
        if (user == null) {
            result.put("login", false);
            return result;
        }
        passportService.login(user, resp);
        result.put("user", user);
        result.put("playLists", userService.getUserPlayLists(user.getId()));
        return result;
    }

    @RequestMapping("predict")
    @ResponseBody
    public Object predict(HttpServletRequest req, HttpServletResponse resp) {
        User user = passportService.getLoginUser(req);
        Map<String, Object> result = new HashMap<String, Object>();
        result.put("err_no", EC.OK);
        try {
            int plid = 1;
            if (user != null) {
                int[] plids = ParamUtils.getValue(req.getParameterValues("plid[]"), int[].class, 0, EC.EC_PARAM);
                if (plids != null && plids.length > 0) {
                    plid = plids[new Random().nextInt(plids.length)];
                }
            }
            Song s = songService.nextSong(plid, null);
            s.setStreamUrl(Util.genPcsUrl("GET", "fmstore", s.getPcsFilename(), 0));
            result.put("song", s);
            if (user != null)
                result.put("thumb", userService.thumbInfo(s.getId(), user));
            else
                result.put("thumb", Const.THUMB_NO);
            // 0未推广， 1订， 2踩
            return result;
        } catch (IllegalException e) {
            result.put("err_no", e.getErrorCode());
            return result;
        }
    }

    /**
     * 加红心
     * 
     * @param req
     * @param resp
     * @return
     */
    @RequestMapping("thumbup")
    @ResponseBody
    public String thumbup(HttpServletRequest req, @RequestParam int sid) {
        User user = passportService.getLoginUser(req);
        try {
            userService.thumbup(sid, user);
        } catch (IllegalException ie) {
            return "{\"err_no\":1}";
        }
        return "{\"err_no\":0}";
    }

    /**
     * 加红心
     * 
     * @param req
     * @param resp
     * @return
     */
    @RequestMapping("thumbdown")
    @ResponseBody
    public String thumbdown(HttpServletRequest req, @RequestParam int sid) {
        User user = passportService.getLoginUser(req);
        try {
            userService.thumbdown(sid, user);
        } catch (IllegalException ie) {
            return "{\"err_no\":1}";
        }
        return "{\"err_no\":0}";
    }
}
