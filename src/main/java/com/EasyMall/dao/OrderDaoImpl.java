package com.EasyMall.dao;

import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.EasyMall.bean.Order;
import com.EasyMall.bean.OrderItem;
import com.EasyMall.bean.Product;
import com.EasyMall.bean.SaleInfo;
import com.EasyMall.utils.BeanHandler;
import com.EasyMall.utils.BeanListHandler;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class OrderDaoImpl implements OrderDao {
    OrderMapper mapper = getOrderMapper();
    SqlSession session;

    public void addOrder(Order order) {
//        String sql = "insert into orders values(?,?,?,?,null,?)";
//        try {
//            JDBCUtils.update(sql, order.getId(), order.getMoney(),
//                    order.getReceiverinfo(), order.getPaystate(),
//                    order.getUser_id());
//        } catch (SQLException e) {
//            e.printStackTrace();
//            throw new RuntimeException(e);
//        }
    }

    public void addOrderItem(OrderItem orderItem) {
//        String sql = "insert into orderitem values(?,?,?)";
//        try {
//            JDBCUtils.update(sql, orderItem.getOrder_id(),
//                    orderItem.getProduct_id(),
//                    orderItem.getBuynum());
//        } catch (Exception e) {
//            e.printStackTrace();
//            throw new RuntimeException(e);
//        }

    }

    public List<Order> findOrderByUserId(int userId) {
//        String sql = "select * from orders where user_id=?";
//        try {
//            return JDBCUtils.query(sql, new BeanListHandler<Order>(Order.class), userId);
//        } catch (SQLException e) {
//            e.printStackTrace();
//            throw new RuntimeException(e);
//        }
        return null;
    }

    public List<OrderItem> findOrderItemByOrderId(String orderId) {
//        try {
//            String sql = "select * from orderitem where order_id=?";
//            return JDBCUtils.query(sql, new BeanListHandler<OrderItem>(OrderItem.class), orderId);
//        } catch (SQLException e) {
//            e.printStackTrace();
//            throw new RuntimeException(e);
//        }
        return null;
    }

    public Order findOrderByOid(String id) {
        //1、编写sql语句
//        String sql = "select * from orders where id=?";
//        try {
//            //2、调用query进行查询
//            return JDBCUtils.query(sql, new BeanHandler<Order>
//                    (Order.class), id);
//        } catch (SQLException e) {
//            e.printStackTrace();
//            return null;
//        }
        return null;
    }

    public boolean deleteOrderItemsByOid(String id) {
        //1、编写sql语句
//        String sql = "delete from orderitem where order_id=?";
//        //2、调用update方法执行删除
//        try {
//            JDBCUtils.update(sql, id);
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        mapper.deleteOrder(id);
        return false;
    }
    public boolean deleteOrderByOid(String id) {
        //1、编写sql
//        String sql = "delete from orders where id=?";
//        //2、调用update方法执行删除
//        try {
//            JDBCUtils.update(sql, id);
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
        boolean flag = mapper.deleteOrder(id);
        session.commit();
        return flag;
    }

    public void updatePaystateByOid(String oid, int paystate) {
//        String sql = "update orders set paystate=? where id=?";
//        try {
//            JDBCUtils.update(sql, paystate,oid);
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
    }

    public List<SaleInfo> findSaleInfos() {
//        String sql = "SELECT pd.id prod_id,pd.name prod_name,"+
//                "SUM(oi.buynum) sale_num " +
//                " FROM products pd,orderitem oi,orders od"+
//                " WHERE pd.id = oi.product_id"+
//                " AND oi.order_id = od.id"+
//                " AND od.paystate = 1"+
//                " GROUP BY prod_id"+
//                " ORDER BY sale_num DESC";
//        //+"LIMIT 0,5";
//        try {
//            return JDBCUtils.query(sql, new BeanListHandler
//                    <SaleInfo>(SaleInfo.class));
//        } catch (SQLException e) {
//            e.printStackTrace();
//            return new ArrayList<SaleInfo>();
//        }
//    }
//        List<OrderItem> list = mapper.findAll();
        return null;
    }

    public List<Order> findAll() {

        List<Order> list = mapper.findAll();
        return list;
    }
    public boolean updateRIOrder(String info,int oid){
        boolean flag = mapper.updateRIOrder(info,oid);
        session.commit();
        return flag;
    }










    private OrderMapper getOrderMapper() {
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
        session = factory.openSession();
        //4.执行SQL语句，查询emp表中的所有记录，封装到List集合中
        return session.getMapper(OrderMapper.class);
    }

}
