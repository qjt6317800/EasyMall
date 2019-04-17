package com.EasyMall.dao;

import com.EasyMall.bean.User;

public interface UserDao {
    public void addUser(User user);

    public User findUserByUsername(String username);

    public User findUserByUNameAndPsw(String username, String password);
}
