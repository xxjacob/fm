package com.ideax.fm360.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ideax.fm360.pojo.Album;
import com.ideax.fm360.pojo.Song;
import com.ideax.fm360.service.AlbumService;
import com.ideax.fm360.service.SongService;

@Controller
@RequestMapping("fm")
public class ServiceC {

    @Autowired
    SongService songService;

    @Autowired
    AlbumService albumService;

    Random random = new Random();

    @RequestMapping("predict")
    @ResponseBody
    public Object predict(HttpServletRequest req, HttpServletResponse resp) {
        List<Song> list = songService.getSongs();
        Song s = list.get(random.nextInt(list.size()));
        Album a = albumService.getAlbumById(s.getAlbumId());
        Map<String, Object> result = new HashMap<String, Object>();
        result.put("song", s);
        result.put("album", a);
        result.put("err_no", 1);
        return result;
    }
}
