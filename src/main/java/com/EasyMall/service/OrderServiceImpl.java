package com.EasyMall.service;

import com.EasyMall.bean.Order;
import com.EasyMall.bean.OrderItem;
import com.EasyMall.bean.Product;
import com.EasyMall.dao.OrderDao;
import com.EasyMall.dao.ProdDao;
import com.EasyMall.exception.MsgException;
import com.EasyMall.factory.BasicFactory;

import java.util.List;

public class OrderServiceImpl implements OrderService {
    private OrderDao order_dao = BasicFactory.getFactory().getInstance(OrderDao.class);
    private ProdDao prod_dao = BasicFactory.getFactory().getInstance(ProdDao.class);
    @Override
    public void addOrder(Order order, List<OrderItem> list) throws MsgException {
        //1.增加订单信息到数据库
        order_dao.addOrder(order);

        //2.检查订单中所有商品的库存数量是否充足
        for (OrderItem item : list) {
            //获取商品的id
            String pid = item.getProduct_id();
            //查询商品信息
            Product prod = prod_dao.findProdById(pid);
            //如果库存数量大于等于购买数量, 则允许下订单, 否则抛出异常
            if(prod.getPnum() >= item.getBuynum()){
                //3.添加订单项信息到数据库
                order_dao.addOrderItem(item);
                //4.将购买的数量从库存数量中扣除
                prod_dao.updatePnum(prod.getId(), prod.getPnum() - item.getBuynum());
            }else{
                throw new MsgException("库存数量不足, 订单添加失败!");
            }
        }

    }
}
