package com.dormitory.dao;

import com.dormitory.model.Student;
import com.dormitory.util.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * 学生数据访问层
 */
public class StudentDao {
    
    /**
     * 查询所有学生
     * @return 学生列表
     */
    public List<Student> findAll() {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        List<Student> studentList = new ArrayList<>();
        try {
            conn = DBUtil.getConnection();
            if (conn != null) {
                String sql = "SELECT * FROM student";
                pstmt = conn.prepareStatement(sql);
                rs = pstmt.executeQuery();
                while (rs.next()) {
                    Student student = new Student();
                    student.setId(rs.getInt("id"));
                    student.setStudentId(rs.getString("student_id"));
                    student.setName(rs.getString("name"));
                    student.setGender(rs.getString("gender"));
                    student.setAge(rs.getInt("age"));
                    student.setDepartment(rs.getString("department"));
                    student.setMajor(rs.getString("major"));
                    student.setClassName(rs.getString("class_name"));
                    student.setPhone(rs.getString("phone"));
                    student.setCreateTime(rs.getTimestamp("create_time"));
                    studentList.add(student);
                }
            } else {
                System.err.println("数据库连接失败，无法执行查询");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(conn, pstmt, rs);
        }
        return studentList;
    }
    
    /**
     * 根据ID查询学生
     * @param id 学生ID
     * @return 学生对象
     */
    public Student findById(Integer id) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Student student = null;
        try {
            conn = DBUtil.getConnection();
            if (conn != null) {
                String sql = "SELECT * FROM student WHERE id = ?";
                pstmt = conn.prepareStatement(sql);
                pstmt.setInt(1, id);
                rs = pstmt.executeQuery();
                if (rs.next()) {
                    student = new Student();
                    student.setId(rs.getInt("id"));
                    student.setStudentId(rs.getString("student_id"));
                    student.setName(rs.getString("name"));
                    student.setGender(rs.getString("gender"));
                    student.setAge(rs.getInt("age"));
                    student.setDepartment(rs.getString("department"));
                    student.setMajor(rs.getString("major"));
                    student.setClassName(rs.getString("class_name"));
                    student.setPhone(rs.getString("phone"));
                    student.setCreateTime(rs.getTimestamp("create_time"));
                }
            } else {
                System.err.println("数据库连接失败，无法执行查询");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(conn, pstmt, rs);
        }
        return student;
    }
    
    /**
     * 添加学生
     * @param student 学生对象
     * @return 添加成功返回1，否则返回0
     */
    public int add(Student student) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        int result = 0;
        try {
            conn = DBUtil.getConnection();
            if (conn != null) {
                String sql = "INSERT INTO student (student_id, name, gender, age, department, major, class_name, phone) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
                pstmt = conn.prepareStatement(sql);
                pstmt.setString(1, student.getStudentId());
                pstmt.setString(2, student.getName());
                pstmt.setString(3, student.getGender());
                pstmt.setInt(4, student.getAge());
                pstmt.setString(5, student.getDepartment());
                pstmt.setString(6, student.getMajor());
                pstmt.setString(7, student.getClassName());
                pstmt.setString(8, student.getPhone());
                result = pstmt.executeUpdate();
            } else {
                System.err.println("数据库连接失败，无法执行添加操作");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(conn, pstmt);
        }
        return result;
    }
    
    /**
     * 更新学生
     * @param student 学生对象
     * @return 更新成功返回1，否则返回0
     */
    public int update(Student student) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        int result = 0;
        try {
            conn = DBUtil.getConnection();
            if (conn != null) {
                String sql = "UPDATE student SET student_id = ?, name = ?, gender = ?, age = ?, department = ?, major = ?, class_name = ?, phone = ? WHERE id = ?";
                pstmt = conn.prepareStatement(sql);
                pstmt.setString(1, student.getStudentId());
                pstmt.setString(2, student.getName());
                pstmt.setString(3, student.getGender());
                pstmt.setInt(4, student.getAge());
                pstmt.setString(5, student.getDepartment());
                pstmt.setString(6, student.getMajor());
                pstmt.setString(7, student.getClassName());
                pstmt.setString(8, student.getPhone());
                pstmt.setInt(9, student.getId());
                result = pstmt.executeUpdate();
            } else {
                System.err.println("数据库连接失败，无法执行更新操作");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(conn, pstmt);
        }
        return result;
    }
    
    /**
     * 删除学生
     * @param id 学生ID
     * @return 删除成功返回1，否则返回0
     */
    public int delete(Integer id) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        int result = 0;
        try {
            conn = DBUtil.getConnection();
            if (conn != null) {
                String sql = "DELETE FROM student WHERE id = ?";
                pstmt = conn.prepareStatement(sql);
                pstmt.setInt(1, id);
                result = pstmt.executeUpdate();
            } else {
                System.err.println("数据库连接失败，无法执行删除操作");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(conn, pstmt);
        }
        return result;
    }
    
    /**
     * 根据学号查询学生
     * @param studentId 学号
     * @return 学生对象
     */
    public Student findByStudentId(String studentId) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Student student = null;
        try {
            conn = DBUtil.getConnection();
            if (conn != null) {
                String sql = "SELECT * FROM student WHERE student_id = ?";
                pstmt = conn.prepareStatement(sql);
                pstmt.setString(1, studentId);
                rs = pstmt.executeQuery();
                if (rs.next()) {
                    student = new Student();
                    student.setId(rs.getInt("id"));
                    student.setStudentId(rs.getString("student_id"));
                    student.setName(rs.getString("name"));
                    student.setGender(rs.getString("gender"));
                    student.setAge(rs.getInt("age"));
                    student.setDepartment(rs.getString("department"));
                    student.setMajor(rs.getString("major"));
                    student.setClassName(rs.getString("class_name"));
                    student.setPhone(rs.getString("phone"));
                    student.setCreateTime(rs.getTimestamp("create_time"));
                }
            } else {
                System.err.println("数据库连接失败，无法执行查询");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(conn, pstmt, rs);
        }
        return student;
    }
}
