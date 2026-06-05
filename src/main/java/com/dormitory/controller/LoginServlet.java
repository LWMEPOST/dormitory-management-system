package com.dormitory.controller;

import com.dormitory.model.User;
import com.dormitory.service.UserService;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

/**
 * 登录控制器
 */

public class LoginServlet extends HttpServlet {
    
    private UserService userService = new UserService();
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 跳转到登录页面
        request.getRequestDispatcher("login.jsp").forward(request, response);
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 设置请求编码
        request.setCharacterEncoding("UTF-8");
        
        // 获取请求参数
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        
        // 调用业务逻辑层进行登录验证
        User user = userService.login(username, password);
        
        if (user != null) {
            // 登录成功，将用户信息存入session
            HttpSession session = request.getSession();
            session.setAttribute("user", user);
            
            // 跳转到主页面
            response.sendRedirect("index.jsp");
        } else {
            // 登录失败，返回登录页面并显示错误信息
            request.setAttribute("error", "用户名或密码错误");
            request.getRequestDispatcher("login.jsp").forward(request, response);
        }
    }
}
