package com.EasyMall.dao;

import com.EasyMall.bean.Order;

import java.util.List;

public interface OrderMapper {
    List<Order> findAll();

    boolean deleteOrder(String id);

    boolean updateRIOrder(String info,int oid);
}
