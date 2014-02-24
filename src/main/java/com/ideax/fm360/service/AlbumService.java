package com.ideax.fm360.service;

import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ideax.fm360.dao.AlbumDAO;
import com.ideax.fm360.pojo.Album;

@Service
public class AlbumService {

    @Autowired
    AlbumDAO albumDao;

    public Album getAlbumById(int aid) {
        try {
            return albumDao.getAlbumbyKey(aid);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
