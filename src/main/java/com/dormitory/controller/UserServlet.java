package com.dormitory.controller;

import com.dormitory.model.User;
import com.dormitory.service.UserService;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

/**
 * 用户管理控制器
 */

public class UserServlet extends HttpServlet {
    
    private UserService userService = new UserService();
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String requestURI = request.getRequestURI();
        String contextPath = request.getContextPath();
        String servletPath = request.getServletPath();
        String path = requestURI.substring(contextPath.length() + servletPath.length());
        
        // 确保path以/开头
        if (!path.startsWith("/")) {
            path = "/" + path;
        }
        
        // 如果path为空或只有/，设置为/
        if (path == null || path.isEmpty()) {
            path = "/";
        }
        
        switch (path) {
            case "/":
            case "":
            case "/list":
                // 查询所有用户
                List<User> userList = userService.findAll();
                request.setAttribute("userList", userList);
                request.getRequestDispatcher("/WEB-INF/user/list.jsp").forward(request, response);
                break;
            case "/add":
                // 跳转到添加用户页面
                request.getRequestDispatcher("/WEB-INF/user/add.jsp").forward(request, response);
                break;
            case "/edit":
                // 跳转到编辑用户页面
                String id = request.getParameter("id");
                User user = userService.findById(Integer.parseInt(id));
                request.setAttribute("user", user);
                request.getRequestDispatcher("/WEB-INF/user/edit.jsp").forward(request, response);
                break;
            case "/delete":
                // 删除用户
                String deleteId = request.getParameter("id");
                userService.delete(Integer.parseInt(deleteId));
                response.sendRedirect(request.getContextPath() + "/user/");
                break;
            default:
                // 让Tomcat直接处理JSP文件请求
                response.sendError(HttpServletResponse.SC_NOT_FOUND);
                break;
        }
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 设置请求编码
        request.setCharacterEncoding("UTF-8");
        
        String requestURI = request.getRequestURI();
        String contextPath = request.getContextPath();
        String servletPath = request.getServletPath();
        String path = requestURI.substring(contextPath.length() + servletPath.length());
        
        // 确保path以/开头
        if (!path.startsWith("/")) {
            path = "/" + path;
        }
        
        // 如果path为空或只有/，设置为/
        if (path == null || path.isEmpty()) {
            path = "/";
        }
        
        switch (path) {
            case "/add":
                // 添加用户
                User newUser = new User();
                newUser.setUsername(request.getParameter("username"));
                newUser.setPassword(request.getParameter("password"));
                newUser.setRole(Integer.parseInt(request.getParameter("role")));
                newUser.setName(request.getParameter("name"));
                newUser.setPhone(request.getParameter("phone"));
                userService.add(newUser);
                response.sendRedirect(request.getContextPath() + "/user/");
                break;
            case "/edit":
                // 编辑用户
                User editUser = new User();
                editUser.setId(Integer.parseInt(request.getParameter("id")));
                editUser.setUsername(request.getParameter("username"));
                editUser.setPassword(request.getParameter("password"));
                editUser.setRole(Integer.parseInt(request.getParameter("role")));
                editUser.setName(request.getParameter("name"));
                editUser.setPhone(request.getParameter("phone"));
                userService.update(editUser);
                response.sendRedirect(request.getContextPath() + "/user/");
                break;
            default:
                response.sendError(HttpServletResponse.SC_NOT_FOUND);
                break;
        }
    }
}
