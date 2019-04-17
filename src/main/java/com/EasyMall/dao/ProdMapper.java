package com.EasyMall.dao;

import com.EasyMall.bean.Product;

import java.util.List;

public interface ProdMapper {


    boolean updatePnum(String pid, int pnum);
    List<Product> findAll();

}
