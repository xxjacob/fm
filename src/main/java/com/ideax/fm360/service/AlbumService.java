package com.ideax.fm360.service;

import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ideax.common.exception.EC;
import com.ideax.common.exception.IllegalException;
import com.ideax.fm360.dao.AlbumDAO;
import com.ideax.fm360.pojo.Album;

@Service
public class AlbumService {
    Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    AlbumDAO albumDao;

    public Album getAlbumById(int aid) {
        try {
            return albumDao.getAlbumbyKey(aid);
        } catch (SQLException e) {
            logger.error("", e);
            throw new IllegalException(EC.EC_DB, "internal error!");
        }
    }
}
