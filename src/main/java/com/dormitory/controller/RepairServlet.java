package com.dormitory.controller;

import com.dormitory.model.Repair;
import com.dormitory.service.RepairService;
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
 * 报修管理控制器
 */

public class RepairServlet extends HttpServlet {
    
    private RepairService repairService = new RepairService();
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
                // 查询所有报修记录
                List<Repair> repairList = repairService.findAll();
                request.setAttribute("repairList", repairList);
                request.getRequestDispatcher("/WEB-INF/repair/list.jsp").forward(request, response);
                break;
            case "/add":
                // 跳转到添加报修页面，同时传递学生列表和宿舍列表
                List<Student> studentList = studentService.findAll();
                List<Dormitory> dormitoryList = dormitoryService.findAll();
                request.setAttribute("studentList", studentList);
                request.setAttribute("dormitoryList", dormitoryList);
                request.getRequestDispatcher("/WEB-INF/repair/add.jsp").forward(request, response);
                break;
            case "/handle":
                // 跳转到处理报修页面
                String id = request.getParameter("id");
                Repair repair = repairService.findById(Integer.parseInt(id));
                request.setAttribute("repair", repair);
                request.getRequestDispatcher("/WEB-INF/repair/handle.jsp").forward(request, response);
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
                // 添加报修记录
                Repair newRepair = new Repair();
                newRepair.setStudentId(Integer.parseInt(request.getParameter("studentId")));
                newRepair.setDormitoryId(Integer.parseInt(request.getParameter("dormitoryId")));
                newRepair.setTitle(request.getParameter("title"));
                newRepair.setContent(request.getParameter("content"));
                
                // 解析日期
                String repairDateStr = request.getParameter("repairDate");
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                try {
                    Date repairDate = sdf.parse(repairDateStr);
                    newRepair.setRepairDate(repairDate);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                
                repairService.add(newRepair);
                response.sendRedirect(request.getContextPath() + "/repair/");
                break;
            case "/handle":
                // 处理报修记录
                Repair editRepair = new Repair();
                editRepair.setId(Integer.parseInt(request.getParameter("id")));
                editRepair.setStatus(Integer.parseInt(request.getParameter("status")));
                editRepair.setHandler(request.getParameter("handler"));
                editRepair.setNote(request.getParameter("note"));
                
                // 如果状态为已完成，设置完成日期
                if (editRepair.getStatus() == 2) {
                    editRepair.setCompleteDate(new Date());
                }
                
                repairService.update(editRepair);
                response.sendRedirect(request.getContextPath() + "/repair/");
                break;
            default:
                response.sendError(HttpServletResponse.SC_NOT_FOUND);
                break;
        }
    }
}
