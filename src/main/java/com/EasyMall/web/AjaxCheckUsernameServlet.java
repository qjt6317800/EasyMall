package com.EasyMall.web;

import com.EasyMall.dao.UserDao;
import com.EasyMall.factory.BasicFactory;
import com.EasyMall.service.UserService;
import com.EasyMall.service.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLDecoder;

@WebServlet(name = "AjaxCheckUsernameServlet")
public class AjaxCheckUsernameServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        UserService service = BasicFactory.getFactory().getInstance(UserService.class);
        String username = request.getParameter("username");
        String valistr = request.getParameter("valistr");
        String valistr2 = (String)request.getSession().getAttribute("valistr");
        //防止乱码转码
        username= URLDecoder.decode(username, "utf-8");
        if(service.findUserByUName(username)){
            response.getWriter().write("1");
            return;
        }if(valistr==null || "".equals(valistr) || valistr2==null || "".equals(valistr2) || !valistr.equals(valistr2)){
            response.getWriter().write("2");
            return;
        }
        response.getWriter().write("0");
    }
}
