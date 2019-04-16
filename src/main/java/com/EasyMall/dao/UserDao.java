package com.EasyMall.dao;

import com.EasyMall.bean.User;
import com.EasyMall.utils.JDBCUtils;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public interface UserDao {
    public void addUser(User user);

    public User findUserByUsername(String username);

    public User findUserByUNameAndPsw(String username, String password);
}
