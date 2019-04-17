package com.EasyMall.web;

import com.EasyMall.bean.User;
import com.EasyMall.factory.BasicFactory;
import com.EasyMall.service.UserService;
import com.EasyMall.utils.WebUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;

@WebServlet(name = "LoginServlet")
public class LoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //解决请求乱码
        //request.setCharacterEncoding("utf-8");
        //解决相应乱码
        //response.setContentType("text/html;charset=utf-8");

        //获取用户提交的用户名 密码
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String remname = request.getParameter("remname");

        /*
            调用Service层的方法根据用户名和密码查询用户
         */

        UserService service = BasicFactory.getFactory().getInstance(UserService.class);
        User user = service.loginUser(username,password);
        if(user == null){
            //用户名密码不正确
            request.setAttribute("msg","用户名或密码不正确");
            request.getRequestDispatcher("/login.jsp").forward(request,response);
            return;
        }else{
            // 实现记住用户名功能
            if("true".equals(remname)){//记住用户名
                //URL编码 -- 将中文字符进行编码
                Cookie remnameCookie = new Cookie("remname",URLEncoder.encode(username,"utf-8"));
                // 设置最大生存时间(保存30天)
                remnameCookie.setMaxAge(30*24*3600);
                // 设置path, 让浏览器访问当前WEB应用下任何一个资源都能带Cookie!!
                remnameCookie.setPath(request.getContextPath()+"/");
                // 将Cookie添加到response中发送给浏览器
                response.addCookie(remnameCookie);
            }else{//取消记住用户名 -- 删除Cookie
                Cookie remnameCookie = new Cookie("remname","");
                remnameCookie.setMaxAge(0);
                remnameCookie.setPath(request.getContextPath()+"/");
                response.addCookie(remnameCookie);
            }

            // 判断是否需要30天内自动登陆
            String autologin = request.getParameter("autologin");
            if ("true".equals(autologin)) {// 实现30天自动登陆
                // 将用户名和密码保存进Cookie中
                Cookie c = new Cookie("autologin", URLEncoder.encode(username, "utf-8") + ":" + password);
                c.setMaxAge(60 * 60 * 24 * 30);// 保存Cookie30天
                c.setPath(request.getContextPath() + "/");
                // 将Cookie发送给浏览器
                response.addCookie(c);
            } else {// 取消30天自动登陆
                Cookie c = new Cookie("autologin", "");
                c.setMaxAge(0);// 设置为0立即删除
                c.setPath(request.getContextPath() + "/");
                response.addCookie(c);
            }

            // 在session中保存登陆标记
            request.getSession().setAttribute("user",user);
            // 登陆成功, 跳转回首页
            response.sendRedirect(request.getContextPath()+"/index.jsp");
        }
    }
}
