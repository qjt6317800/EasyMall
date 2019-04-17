package com.EasyMall.web.back;

import com.EasyMall.factory.BasicFactory;
import com.EasyMall.service.OrderService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "BackOrderDelServlet")
public class BackOrderDelServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1.获取订单的id
        String oid = request.getParameter("oid");
        //2.调用service层的方法根据商品id删除指定的商品
        OrderService service = BasicFactory.getFactory().getInstance(OrderService.class);
        boolean result = service.delOrder(oid);
        //3.做出响应
        response.getWriter().write(result+"");
    }
}
