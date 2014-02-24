package com.ideax.fm360.service;

import java.sql.SQLException;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ideax.fm360.dao.SongDAO;
import com.ideax.fm360.pojo.Song;
import com.ideax.fm360.query.SongQuery;

@Service
public class SongService {

    @Autowired
    SongDAO songDao;

    public List<Song> getSongs() {
        try {
            return songDao.getSongList(new SongQuery());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Collections.emptyList();
    }
}
