package com.EasyMall.dao;

import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.List;

import com.EasyMall.bean.Product;
import com.EasyMall.utils.BeanHandler;
import com.EasyMall.utils.BeanListHandler;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class ProdDaoImpl implements ProdDao {
    public List<Product> findAll() {
//        try {
//            return JDBCUtils.query("select * from products",
//                    new BeanListHandler<Product>(Product.class));
//        } catch (SQLException e) {
//            e.printStackTrace();
//            throw new RuntimeException(e);
//        }
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
        ProdMapper mapper = session.getMapper(ProdMapper.class);
        List<Product> list = mapper.findAll();
        return list;
    }

    public boolean updatePnum(String pid, int pnum) {
//        try {
//            return JDBCUtils.update("update products set pnum=? where id=?",
//                    pnum, pid) > 0;
//        } catch (SQLException e) {
//            e.printStackTrace();
//            throw new RuntimeException(e);
//        }


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
        ProdMapper mapper = session.getMapper(ProdMapper.class);
        boolean flag = mapper.updatePnum(pid, pnum);
        session.commit();
        return flag;
    }

    public boolean delProd(String pid) {
//        String sql = "delete from products where id=?";
//        try {
//            return JDBCUtils.update(sql, pid) > 0;
//        } catch (SQLException e) {
//            e.printStackTrace();
//            throw new RuntimeException(e);
//        }
        return false;
    }

    public List<Product> findAllByCondition(String _name, String _category,
                                            double _minprice, double _maxprice) {
//        try {
//            String sql = "select * from products where name like ? and category like ? and price>=? and price<=?";
//            return JDBCUtils.query(sql, new BeanListHandler<Product>(Product.class),
//                    "%" + _name + "%", "%" + _category + "%",
//                    _minprice, _maxprice);
//        } catch (SQLException e) {
//            e.printStackTrace();
//            throw new RuntimeException(e);
//        }
        return null;
    }

    public List<Product> findAllBySearch(String search) {
//        try {
//            String sql = "select * from products where name like ? or category like ? or description like ?";
//            return JDBCUtils.query(sql, new BeanListHandler<Product>(Product.class), "%"+search+"%", "%"+search+"%", "%"+search+"%");
//        } catch (SQLException e) {
//            e.printStackTrace();
//            throw new RuntimeException(e);
//        }
        return null;
    }

    public Product findProdById(String pid) {
//        String sql = "select * from products where id=?";
//        try {
//            return JDBCUtils.query(sql, new BeanHandler<Product>(Product.class), pid);
//        } catch (SQLException e) {
//            e.printStackTrace();
//            throw new RuntimeException(e);
//        }
        return null;
    }

    public void changePnum(String product_id, int pnumAdd) {
        //1、编写sql语句
//        String sql = "update products set pnum = pnum+?" +
//                " where id=?";
//        //2、调用update执行修改操作
//        try {
//            JDBCUtils.update(sql,pnumAdd,product_id);
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
    }
}
