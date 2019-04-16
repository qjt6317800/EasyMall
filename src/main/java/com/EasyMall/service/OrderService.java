package com.EasyMall.service;

import com.EasyMall.bean.Order;
import com.EasyMall.bean.OrderItem;
import com.EasyMall.exception.MsgException;

import java.util.List;


public interface OrderService{

    /**
     * 增加订单
     * @param order 订单信息
     * @param list	订单项信息
     * @throws MsgException
     */
    void addOrder(Order order, List<OrderItem> list) throws MsgException;



}
