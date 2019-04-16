package com.EasyMall.service;

import com.EasyMall.bean.User;
import com.EasyMall.dao.UserDao;
import com.EasyMall.factory.BasicFactory;

import java.io.IOException;

public class UserServiceImpl implements UserService{
    private UserDao dao = BasicFactory.getFactory().getInstance(UserDao.class);
    /**
     * 检验用户名是否存在
     * @param username
     * @return
     */
    public boolean findUserByUName(String username){
        return dao.findUserByUsername(username) != null;
    }

    /**
     * 添加用户
     * @param user
     */
    public void addUser(User user) {
        dao.addUser(user);
    }

    /**
     * 根据用户名和密码查找用户
     * @param username
     * @param password
     * @return
     */
    public User findUserByUNameAndPsw(String username,String password){
        return dao.findUserByUNameAndPsw(username,password);
    }

    /**
     * 登录用户
     * @param username
     * @param password
     * @return
     */
    public User loginUser(String username, String password) {
        return dao.findUserByUNameAndPsw(username, password);
    }

}
