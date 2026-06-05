package com.dormitory.dao;

import com.dormitory.model.CheckIn;
import com.dormitory.util.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * 入住数据访问层
 */
public class CheckInDao {
    
    /**
     * 查询所有入住记录
     * @return 入住记录列表
     */
    public List<CheckIn> findAll() {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        List<CheckIn> checkInList = new ArrayList<>();
        try {
            conn = DBUtil.getConnection();
            if (conn != null) {
                String sql = "SELECT * FROM check_in";
                pstmt = conn.prepareStatement(sql);
                rs = pstmt.executeQuery();
                while (rs.next()) {
                    CheckIn checkIn = new CheckIn();
                    checkIn.setId(rs.getInt("id"));
                    checkIn.setStudentId(rs.getInt("student_id"));
                    checkIn.setDormitoryId(rs.getInt("dormitory_id"));
                    checkIn.setCheckInDate(rs.getDate("check_in_date"));
                    checkIn.setNote(rs.getString("note"));
                    checkIn.setCreateTime(rs.getTimestamp("create_time"));
                    checkInList.add(checkIn);
                }
            } else {
                System.err.println("数据库连接失败，无法执行查询");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(conn, pstmt, rs);
        }
        return checkInList;
    }
    
    /**
     * 根据ID查询入住记录
     * @param id 入住记录ID
     * @return 入住记录对象
     */
    public CheckIn findById(Integer id) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        CheckIn checkIn = null;
        try {
            conn = DBUtil.getConnection();
            if (conn != null) {
                String sql = "SELECT * FROM check_in WHERE id = ?";
                pstmt = conn.prepareStatement(sql);
                pstmt.setInt(1, id);
                rs = pstmt.executeQuery();
                if (rs.next()) {
                    checkIn = new CheckIn();
                    checkIn.setId(rs.getInt("id"));
                    checkIn.setStudentId(rs.getInt("student_id"));
                    checkIn.setDormitoryId(rs.getInt("dormitory_id"));
                    checkIn.setCheckInDate(rs.getDate("check_in_date"));
                    checkIn.setNote(rs.getString("note"));
                    checkIn.setCreateTime(rs.getTimestamp("create_time"));
                }
            } else {
                System.err.println("数据库连接失败，无法执行查询");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(conn, pstmt, rs);
        }
        return checkIn;
    }
    
    /**
     * 添加入住记录
     * @param checkIn 入住记录对象
     * @return 添加成功返回1，否则返回0
     */
    public int add(CheckIn checkIn) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        int result = 0;
        try {
            conn = DBUtil.getConnection();
            if (conn != null) {
                String sql = "INSERT INTO check_in (student_id, dormitory_id, check_in_date, note) VALUES (?, ?, ?, ?)";
                pstmt = conn.prepareStatement(sql);
                pstmt.setInt(1, checkIn.getStudentId());
                pstmt.setInt(2, checkIn.getDormitoryId());
                pstmt.setDate(3, new java.sql.Date(checkIn.getCheckInDate().getTime()));
                pstmt.setString(4, checkIn.getNote());
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
     * 根据学生ID查询入住记录
     * @param studentId 学生ID
     * @return 入住记录对象
     */
    public CheckIn findByStudentId(Integer studentId) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        CheckIn checkIn = null;
        try {
            conn = DBUtil.getConnection();
            if (conn != null) {
                String sql = "SELECT * FROM check_in WHERE student_id = ?";
                pstmt = conn.prepareStatement(sql);
                pstmt.setInt(1, studentId);
                rs = pstmt.executeQuery();
                if (rs.next()) {
                    checkIn = new CheckIn();
                    checkIn.setId(rs.getInt("id"));
                    checkIn.setStudentId(rs.getInt("student_id"));
                    checkIn.setDormitoryId(rs.getInt("dormitory_id"));
                    checkIn.setCheckInDate(rs.getDate("check_in_date"));
                    checkIn.setNote(rs.getString("note"));
                    checkIn.setCreateTime(rs.getTimestamp("create_time"));
                }
            } else {
                System.err.println("数据库连接失败，无法执行查询");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(conn, pstmt, rs);
        }
        return checkIn;
    }
}
