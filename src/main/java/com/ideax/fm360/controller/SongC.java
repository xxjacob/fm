package com.ideax.fm360.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.math.RandomUtils;
import org.springframework.beans.factory.InitializingBean;
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
import com.ideax.fm360.pojo.SongListItem;
import com.ideax.fm360.pojo.User;
import com.ideax.fm360.search.SongSearch;
import com.ideax.fm360.service.AlbumService;
import com.ideax.fm360.service.IPassportService;
import com.ideax.fm360.service.SongListService;
import com.ideax.fm360.service.SongService;
import com.ideax.fm360.service.UserService;

@Controller
@RequestMapping("fm")
public class SongC implements InitializingBean {

    @Autowired
    SongService songService;

    @Autowired
    AlbumService albumService;

    @Autowired
    UserService userService;

    @Autowired
    IPassportService passportService;

    @Autowired
    SongListService songListService;

    SongSearch search = null;

    @RequestMapping("predict")
    @ResponseBody
    public Object predict(HttpServletRequest req, HttpServletResponse resp, @RequestParam(defaultValue = "0") int sid) {
        User user = passportService.getLoginUser(req);
        Map<String, Object> result = new HashMap<String, Object>();
        result.put("err_no", EC.OK);
        try {
            Song s = null;
            if (sid > 0) {
                s = songService.getSongById(sid);
            } else {
                int plid = 0;
                if (user != null) {
                    int[] plids = ParamUtils.getValue(req.getParameterValues("plid[]"), int[].class, 0, EC.EC_PARAM);
                    if (plids != null && plids.length > 0) {
                        plid = plids[RandomUtils.nextInt(plids.length)];
                    }
                }
                s = songService.nextSong(user == null ? 0 : user.getId(), plid, null);
            }
            
            if (s == null) {
                throw new IllegalException(EC.EC_UNKNOWM);
            }
            
            s.setStreamUrl(Util.genPcsUrl("GET", "fmstore", s.getPcsFilename(), 0));
            result.put("song", s);
            if (user != null) {
                List<SongListItem> songlist = songListService.thumbInfo(s.getId(), user.getId());
                // 歌曲所在的歌单id
                List<Integer> listIds = new ArrayList<Integer>();
                int thumbStatus = Const.THUMB_NO;
                if (songlist != null && songlist.size() > 0) {
                    for (SongListItem sl : songlist) {
                        if (sl.getType() == Const.SONG_LIST_TYPE_THUMBDOWN) {
                            thumbStatus = Const.SONG_LIST_TYPE_THUMBDOWN;
                        } else if (sl.getType() == Const.SONG_LIST_TYPE_THUMBUP) {
                            thumbStatus = Const.SONG_LIST_TYPE_THUMBUP;
                        } else if (sl.getType() == Const.SONG_LIST_TYPE_TIRED) {
                            thumbStatus = Const.SONG_LIST_TYPE_TIRED;
                        } else {
                            listIds.add(sl.getListId());
                        }
                    }
                }
                result.put("thumb", thumbStatus);
                result.put("playList", listIds);
            } else
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
            int[] ret = songListService.action(sid, user.getId(), Const.SONG_LIST_TYPE_THUMBUP);
            return "{\"err_no\":0,\"thumb\":" + ret[0] + ", \"thumbup_num\":" + ret[1] + ",\"thumbdown_num\":" + ret[2]
                    + ",\"tired_num\":" + ret[3] + "}";
        } catch (IllegalException ie) {
            return "{\"err_no\":1}";
        }
    }

    @RequestMapping("thumbdown")
    @ResponseBody
    public String thumbdown(HttpServletRequest req, @RequestParam int sid) {
        User user = passportService.getLoginUser(req);
        try {
            int[] ret = songListService.action(sid, user.getId(), Const.SONG_LIST_TYPE_THUMBDOWN);
            return "{\"err_no\":0,\"thumb\":" + ret[0] + ", \"thumbup_num\":" + ret[1] + ",\"thumbdown_num\":" + ret[2]
                    + ",\"tired_num\":" + ret[3] + "}";
        } catch (IllegalException ie) {
            return "{\"err_no\":1}";
        }
    }

    @RequestMapping("tired")
    @ResponseBody
    public String tired(HttpServletRequest req, @RequestParam int sid) {
        User user = passportService.getLoginUser(req);
        try {
            int[] ret = songListService.action(sid, user.getId(), Const.SONG_LIST_TYPE_TIRED);
            return "{\"err_no\":0,\"thumb\":" + ret[0] + ", \"thumbup_num\":" + ret[1] + ",\"thumbdown_num\":" + ret[2]
                    + ",\"tired_num\":" + ret[3] + "}";
        } catch (IllegalException ie) {
            return "{\"err_no\":1}";
        }
    }

    @RequestMapping("search")
    @ResponseBody
    public String[] search(HttpServletRequest req, @RequestParam String key) {
        return search.suggest(key);
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        search = new SongSearch();
        search.build(songService.songs);
    }
}
