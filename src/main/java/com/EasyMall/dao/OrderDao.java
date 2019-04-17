package com.EasyMall.dao;

import com.EasyMall.bean.Order;
import com.EasyMall.bean.OrderItem;
import com.EasyMall.bean.SaleInfo;

import java.util.List;

public interface OrderDao {
    public void addOrder(Order order);
    public void addOrderItem(OrderItem item);
    List<Order> findAll();
    boolean deleteOrderItemsByOid(String id);
    boolean deleteOrderByOid(String id);
    List<SaleInfo> findSaleInfos();
    boolean updateRIOrder(String info,int oid);
}
