package com.EasyMall.service;

import com.EasyMall.bean.User;

import java.io.IOException;

public interface UserService {
    public boolean findUserByUName(String username);

    public void addUser(User user);

    public User findUserByUNameAndPsw(String username, String password);

    public User loginUser(String username, String password);
}
