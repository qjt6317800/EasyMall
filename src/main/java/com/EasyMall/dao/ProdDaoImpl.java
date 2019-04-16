package com.EasyMall.dao;

import java.sql.SQLException;
import java.util.List;

import com.EasyMall.bean.Product;
import com.EasyMall.utils.BeanHandler;
import com.EasyMall.utils.BeanListHandler;
import com.EasyMall.utils.JDBCUtils;

public class ProdDaoImpl implements ProdDao {
    public List<Product> findAll() {
        try {
            return JDBCUtils.query("select * from products",
                    new BeanListHandler<Product>(Product.class));
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    public boolean updatePnum(String pid, int pnum) {
        try {
            return JDBCUtils.update("update products set pnum=? where id=?",
                    pnum, pid) > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    public boolean delProd(String pid) {
        String sql = "delete from products where id=?";
        try {
            return JDBCUtils.update(sql, pid) > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    public List<Product> findAllByCondition(String _name, String _category,
                                            double _minprice, double _maxprice) {
        try {
            String sql = "select * from products where name like ? and category like ? and price>=? and price<=?";
            return JDBCUtils.query(sql, new BeanListHandler<Product>(Product.class),
                    "%" + _name + "%", "%" + _category + "%",
                    _minprice, _maxprice);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    public List<Product> findAllBySearch(String search) {
        try {
            String sql = "select * from products where name like ? or category like ? or description like ?";
            return JDBCUtils.query(sql, new BeanListHandler<Product>(Product.class), "%"+search+"%", "%"+search+"%", "%"+search+"%");
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    public Product findProdById(String pid) {
        String sql = "select * from products where id=?";
        try {
            return JDBCUtils.query(sql, new BeanHandler<Product>(Product.class), pid);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    public void changePnum(String product_id, int pnumAdd) {
        //1、编写sql语句
        String sql = "update products set pnum = pnum+?" +
                " where id=?";
        //2、调用update执行修改操作
        try {
            JDBCUtils.update(sql,pnumAdd,product_id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
