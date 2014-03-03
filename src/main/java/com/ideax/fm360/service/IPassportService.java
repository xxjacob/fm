package com.ideax.fm360.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ideax.fm360.pojo.User;

public interface IPassportService {

    public User getLoginUser(HttpServletRequest request);

    public void login(User user, HttpServletResponse resp);
}
