package com.EasyMall.web.back;

import com.EasyMall.bean.Product;
import com.EasyMall.factory.BasicFactory;
import com.EasyMall.service.ProdService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "BackProdListServlet")
public class BackProdListServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ProdService service = BasicFactory.getFactory().getInstance(ProdService.class);
        List<Product> list =  service.findAll();

        request.setAttribute("list", list);

        request.getRequestDispatcher("/backend/prod_list.jsp").forward(request, response);
    }
}
