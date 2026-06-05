package com.dormitory.controller;

import com.dormitory.model.CheckIn;
import com.dormitory.service.CheckInService;
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
 * 入住管理控制器
 */

public class CheckInServlet extends HttpServlet {
    
    private CheckInService checkInService = new CheckInService();
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
                // 查询所有入住记录
                List<CheckIn> checkInList = checkInService.findAll();
                request.setAttribute("checkInList", checkInList);
                request.getRequestDispatcher("/WEB-INF/checkin/list.jsp").forward(request, response);
                break;
            case "/add":
                // 跳转到添加入住页面，同时传递学生列表和宿舍列表
                List<Student> studentList = studentService.findAll();
                List<Dormitory> dormitoryList = dormitoryService.findAll();
                request.setAttribute("studentList", studentList);
                request.setAttribute("dormitoryList", dormitoryList);
                request.getRequestDispatcher("/WEB-INF/checkin/add.jsp").forward(request, response);
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
                // 添加入住记录
                CheckIn checkIn = new CheckIn();
                checkIn.setStudentId(Integer.parseInt(request.getParameter("studentId")));
                checkIn.setDormitoryId(Integer.parseInt(request.getParameter("dormitoryId")));
                
                // 解析日期
                String checkInDateStr = request.getParameter("checkInDate");
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                try {
                    Date checkInDate = sdf.parse(checkInDateStr);
                    checkIn.setCheckInDate(checkInDate);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                
                checkIn.setNote(request.getParameter("note"));
                checkInService.add(checkIn);
                response.sendRedirect(request.getContextPath() + "/checkin/");
                break;
            default:
                response.sendError(HttpServletResponse.SC_NOT_FOUND);
                break;
        }
    }
}
