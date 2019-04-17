package com.EasyMall.web.back;

import com.EasyMall.bean.SaleInfo;
import com.EasyMall.factory.BasicFactory;
import com.EasyMall.service.OrderService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "SaleListServlet")
public class SaleListServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1、创建业务层对象
        OrderService os = BasicFactory.getFactory().
                getInstance(OrderService.class);
        //2、调用业务层方法查询全部的销售榜单
        List<SaleInfo> list = os.findSaleInfos();
        //3、将list保存到request作用域中
        request.setAttribute("list", list);
        //4、转发至sale_list.jsp
        request.getRequestDispatcher("/backend/sale_list.jsp")
                .forward(request, response);
    }
}
