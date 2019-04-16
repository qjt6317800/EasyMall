package com.EasyMall.dao;

import com.EasyMall.bean.Order;
import com.EasyMall.bean.OrderItem;

public interface OrderDao {
    public void addOrder(Order order);
    public void addOrderItem(OrderItem item);
}
