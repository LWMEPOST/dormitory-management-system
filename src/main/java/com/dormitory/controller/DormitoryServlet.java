package com.dormitory.controller;

import com.dormitory.model.Dormitory;
import com.dormitory.service.DormitoryService;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

/**
 * 宿舍管理控制器
 */

public class DormitoryServlet extends HttpServlet {
    
    private DormitoryService dormitoryService = new DormitoryService();
    
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
                // 查询所有宿舍
                List<Dormitory> dormitoryList = dormitoryService.findAll();
                request.setAttribute("dormitoryList", dormitoryList);
                request.getRequestDispatcher("/WEB-INF/dormitory/list.jsp").forward(request, response);
                break;
            case "/add":
                // 跳转到添加宿舍页面
                request.getRequestDispatcher("/WEB-INF/dormitory/add.jsp").forward(request, response);
                break;
            case "/edit":
                // 跳转到编辑宿舍页面
                String id = request.getParameter("id");
                Dormitory dormitory = dormitoryService.findById(Integer.parseInt(id));
                request.setAttribute("dormitory", dormitory);
                request.getRequestDispatcher("/WEB-INF/dormitory/edit.jsp").forward(request, response);
                break;
            case "/delete":
                // 删除宿舍
                String deleteId = request.getParameter("id");
                dormitoryService.delete(Integer.parseInt(deleteId));
                response.sendRedirect(request.getContextPath() + "/dormitory/");
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
                // 添加宿舍
                Dormitory newDormitory = new Dormitory();
                newDormitory.setDormitoryNumber(request.getParameter("dormitoryNumber"));
                newDormitory.setBuilding(request.getParameter("building"));
                newDormitory.setFloor(Integer.parseInt(request.getParameter("floor")));
                newDormitory.setCapacity(Integer.parseInt(request.getParameter("capacity")));
                newDormitory.setType(request.getParameter("type"));
                dormitoryService.add(newDormitory);
                response.sendRedirect(request.getContextPath() + "/dormitory/");
                break;
            case "/edit":
                // 编辑宿舍
                Dormitory editDormitory = new Dormitory();
                editDormitory.setId(Integer.parseInt(request.getParameter("id")));
                editDormitory.setDormitoryNumber(request.getParameter("dormitoryNumber"));
                editDormitory.setBuilding(request.getParameter("building"));
                editDormitory.setFloor(Integer.parseInt(request.getParameter("floor")));
                editDormitory.setCapacity(Integer.parseInt(request.getParameter("capacity")));
                editDormitory.setCurrentOccupancy(Integer.parseInt(request.getParameter("currentOccupancy")));
                editDormitory.setType(request.getParameter("type"));
                editDormitory.setStatus(Integer.parseInt(request.getParameter("status")));
                dormitoryService.update(editDormitory);
                response.sendRedirect(request.getContextPath() + "/dormitory/");
                break;
            default:
                response.sendError(HttpServletResponse.SC_NOT_FOUND);
                break;
        }
    }
}
