package com.dormitory.controller;

import com.dormitory.model.Student;
import com.dormitory.service.StudentService;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

/**
 * 学生管理控制器
 */

public class StudentServlet extends HttpServlet {
    
    private StudentService studentService = new StudentService();
    
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
                // 查询所有学生
                List<Student> studentList = studentService.findAll();
                request.setAttribute("studentList", studentList);
                request.getRequestDispatcher("/WEB-INF/student/list.jsp").forward(request, response);
                break;
            case "/add":
                // 跳转到添加学生页面
                request.getRequestDispatcher("/WEB-INF/student/add.jsp").forward(request, response);
                break;
            case "/edit":
                // 跳转到编辑学生页面
                String id = request.getParameter("id");
                Student student = studentService.findById(Integer.parseInt(id));
                request.setAttribute("student", student);
                request.getRequestDispatcher("/WEB-INF/student/edit.jsp").forward(request, response);
                break;
            case "/delete":
                // 删除学生
                String deleteId = request.getParameter("id");
                studentService.delete(Integer.parseInt(deleteId));
                response.sendRedirect(request.getContextPath() + "/student/");
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
                // 添加学生
                Student newStudent = new Student();
                newStudent.setStudentId(request.getParameter("studentId"));
                newStudent.setName(request.getParameter("name"));
                newStudent.setGender(request.getParameter("gender"));
                newStudent.setAge(Integer.parseInt(request.getParameter("age")));
                newStudent.setDepartment(request.getParameter("department"));
                newStudent.setMajor(request.getParameter("major"));
                newStudent.setClassName(request.getParameter("className"));
                newStudent.setPhone(request.getParameter("phone"));
                studentService.add(newStudent);
                response.sendRedirect(request.getContextPath() + "/student/");
                break;
            case "/edit":
                // 编辑学生
                Student editStudent = new Student();
                editStudent.setId(Integer.parseInt(request.getParameter("id")));
                editStudent.setStudentId(request.getParameter("studentId"));
                editStudent.setName(request.getParameter("name"));
                editStudent.setGender(request.getParameter("gender"));
                editStudent.setAge(Integer.parseInt(request.getParameter("age")));
                editStudent.setDepartment(request.getParameter("department"));
                editStudent.setMajor(request.getParameter("major"));
                editStudent.setClassName(request.getParameter("className"));
                editStudent.setPhone(request.getParameter("phone"));
                studentService.update(editStudent);
                response.sendRedirect(request.getContextPath() + "/student/");
                break;
            default:
                response.sendError(HttpServletResponse.SC_NOT_FOUND);
                break;
        }
    }
}
