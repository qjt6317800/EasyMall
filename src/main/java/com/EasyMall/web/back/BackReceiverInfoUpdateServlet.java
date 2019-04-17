package com.EasyMall.web.back;

import com.EasyMall.factory.BasicFactory;
import com.EasyMall.service.OrderService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "BackReceiverInfoUpdateServlet")
public class BackReceiverInfoUpdateServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1.获取商品的id和需要修改为的订单金额
//        String oid = request.getParameter("oid");
        String info = request.getParameter("info");
        int oid = Integer.parseInt(request.getParameter("oid"));

        //2.调用service层的方法修改指定商品的订单金额
        OrderService service = BasicFactory.getFactory().getInstance(OrderService.class);
        boolean result = service.updateRIOrder(info,oid);

        //3.做出响应
        //true:表示修改成功!
        response.getWriter().write(result+"");
    }
}
