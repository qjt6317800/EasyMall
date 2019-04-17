package com.EasyMall.web;

import com.EasyMall.bean.User;
import com.EasyMall.factory.BasicFactory;
import com.EasyMall.service.UserService;
import com.EasyMall.service.UserServiceImpl;
import com.EasyMall.utils.WebUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class RegistServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //读取web.xml中的编码配置
        //String encode = this.getServletConfig().getInitParameter("encode");
        //请求参数编码设置
        //request.setCharacterEncoding(encode);
        //响应输出编码配置
        //response.setContentType("text/html;charset=utf-8");

        UserService service = BasicFactory.getFactory().getInstance(UserService.class);
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String password2 = request.getParameter("password2");
        String nickname = request.getParameter("nickname");
        String email = request.getParameter("email");
        String valistr = request.getParameter("valistr").toLowerCase();
        String role="user";
        //验证验证码是否正确
        String valistr2 = (String)request.getSession().getAttribute("valistr");
        if(valistr==null || "".equals(valistr) || valistr2==null || "".equals(valistr2) || !valistr.equals(valistr2)){
            response.getWriter().write("验证码错误！");
            return;
        }
        //验证字段非空
        if(username == null || "".equals(username)){
            response.getWriter().write("用户名不能为空！");
            return;
        }

        if(service.findUserByUName(username)){
            response.getWriter().write("用户名已存在！");
            return;
        }

        if(password == null || "".equals(password)){
            response.getWriter().write("密码不能为空！");
            return;
        }
        if(password2 == null || "".equals(password2)){
            response.getWriter().write("确认密码不能为空！");
            return;
        }
        if(nickname == null || "".equals(nickname)){
            response.getWriter().write("昵称不能为空！");
            return;
        }
        if(email == null || "".equals(email)){
            response.getWriter().write("邮箱不能为空！");
            return;
        }

        //验证邮箱格式是否正确
        if(!email.matches("^\\w+@\\w+(\\.\\w+)+$")){
            response.getWriter().write("邮箱格式不正确！");
            return;
        }

        //存入数据库
        User user = new User(username,password,nickname,email,role);
        service.addUser(user);

        //回到主页
        response.getWriter()
                .write("<h1 style='color:red;text-align:center'>恭喜您注册成功, 3秒之后将会跳转到首页...</h1>");
        response.setHeader("refresh", "3;url=" + request.getContextPath()
                + "/index.jsp");
    }
}
