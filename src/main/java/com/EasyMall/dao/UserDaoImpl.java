package com.EasyMall.dao;

import com.EasyMall.bean.User;
import com.EasyMall.utils.JDBCUtils;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class UserDaoImpl implements UserDao {
    /**
     * 增加用户到数据库
     * @param user
     */
    public void addUser(User user)  {
        //1.读取sqlMapConfig.xml文件，获取其中的基本信息
        InputStream in = null;
        try {
            in = Resources.getResourceAsStream("sqlMapConfig.xml");
        } catch (IOException e) {
            e.printStackTrace();
        }
        //2.根据配置信息生成SqlSessionFactory工厂对象，
        SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(in);
        //3.通过工厂获取一个SqlSession对象(用于执行SQL及返回结果)
        SqlSession session = factory.openSession();
        //4.执行SQL语句，查询emp表中的所有记录，封装到List集合中
        UserMapper mapper = session.getMapper(UserMapper.class);
        mapper.addUser(user);
        session.commit();
    }
//    public void addUser(User user){
//        Connection conn=null;
//        PreparedStatement ps = null;
//        ResultSet rs= null;
//        String username = user.getUsername();
//        String password = user.getPassword();
//        String nickname = user.getNickname();
//        String email = user.getEmail();
//        try {
//            Class.forName("com.mysql.jdbc.Driver");
//            conn = JDBCUtils.getConn();
//            //关闭自动提交事务
//            conn.setAutoCommit(false);
//            String sql = "insert into user values(null,?,?,?,?)";
//            ps = conn.prepareStatement(sql);
//            ps.setString(1, username);
//            ps.setString(2, password);
//            ps.setString(3, nickname);
//            ps.setString(4, email);
//            ps.executeUpdate();
//            //提交事务
//            conn.commit();
//        } catch (Exception e) {
//            e.printStackTrace();
//        } finally {
//            JDBCUtils.close(conn,ps,rs);
//        }
//    }

    /**
     * 根据用户名查找用户
     * @param username
     * @return 没找到则返回null,找到就返回user对象
     */
    public User findUserByUsername(String username){
        Connection conn=null;
        PreparedStatement ps = null;
        ResultSet rs= null;
        try {
            conn = JDBCUtils.getConn();
            String sql = "select * from user where username=?";
            ps = conn.prepareStatement(sql);
            ps.setString(1, username);
            rs = ps.executeQuery();
            if(rs.next()==true){
                User user = new User(rs.getInt("id"),rs.getString("username"),rs.getString("password"),rs.getString("nickname"),rs.getString("email"),rs.getString("role"));
                return user;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.close(conn,ps,rs);
        }
        return null;
    }

    /**
     * 根据用户名密码查找用户
     * @param username
     * @param password
     * @return
     */
    public User findUserByUNameAndPsw(String username,String password){
        Connection conn=null;
        PreparedStatement ps = null;
        ResultSet rs= null;
        try {
            conn = JDBCUtils.getConn();
            String sql = "select * from user where username=?";
            ps = conn.prepareStatement(sql);
            ps.setString(1, username);
            rs = ps.executeQuery();
            if(rs.next()==true){
                if(password.equals(rs.getString("password"))){
                    User user = new User(rs.getInt("id"),rs.getString("username"),rs.getString("password"),rs.getString("nickname"),rs.getString("email"),rs.getString("role"));
                    return user;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.close(conn,ps,rs);
        }
        return null;
    }
}
