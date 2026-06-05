package com.dormitory.controller;

import com.dormitory.model.CheckOut;
import com.dormitory.service.CheckOutService;
import com.dormitory.service.StudentService;
import com.dormitory.service.DormitoryService;
import com.dormitory.model.Student;
import com.dormitory.model.Dormitory;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * 退宿管理控制器
 */

public class CheckOutServlet extends HttpServlet {
    
    private CheckOutService checkOutService = new CheckOutService();
    private StudentService studentService = new StudentService();
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
                // 查询所有退宿记录
                List<CheckOut> checkOutList = checkOutService.findAll();
                request.setAttribute("checkOutList", checkOutList);
                request.getRequestDispatcher("/WEB-INF/checkout/list.jsp").forward(request, response);
                break;
            case "/add":
                // 跳转到添加退宿页面，同时传递学生列表和宿舍列表
                List<Student> studentList = studentService.findAll();
                List<Dormitory> dormitoryList = dormitoryService.findAll();
                request.setAttribute("studentList", studentList);
                request.setAttribute("dormitoryList", dormitoryList);
                request.getRequestDispatcher("/WEB-INF/checkout/add.jsp").forward(request, response);
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
                // 添加退宿记录
                CheckOut checkOut = new CheckOut();
                checkOut.setStudentId(Integer.parseInt(request.getParameter("studentId")));
                checkOut.setDormitoryId(Integer.parseInt(request.getParameter("dormitoryId")));
                
                // 解析日期
                String checkOutDateStr = request.getParameter("checkOutDate");
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                try {
                    Date checkOutDate = sdf.parse(checkOutDateStr);
                    checkOut.setCheckOutDate(checkOutDate);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                
                checkOut.setReason(request.getParameter("reason"));
                checkOut.setNote(request.getParameter("note"));
                checkOutService.add(checkOut);
                response.sendRedirect(request.getContextPath() + "/checkout/");
                break;
            default:
                response.sendError(HttpServletResponse.SC_NOT_FOUND);
                break;
        }
    }
}
