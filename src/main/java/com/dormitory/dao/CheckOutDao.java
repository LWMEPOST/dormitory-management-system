package com.dormitory.dao;

import com.dormitory.model.CheckOut;
import com.dormitory.util.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * 退宿数据访问层
 */
public class CheckOutDao {
    
    /**
     * 查询所有退宿记录
     * @return 退宿记录列表
     */
    public List<CheckOut> findAll() {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        List<CheckOut> checkOutList = new ArrayList<>();
        try {
            conn = DBUtil.getConnection();
            if (conn != null) {
                String sql = "SELECT * FROM check_out";
                pstmt = conn.prepareStatement(sql);
                rs = pstmt.executeQuery();
                while (rs.next()) {
                    CheckOut checkOut = new CheckOut();
                    checkOut.setId(rs.getInt("id"));
                    checkOut.setStudentId(rs.getInt("student_id"));
                    checkOut.setDormitoryId(rs.getInt("dormitory_id"));
                    checkOut.setCheckOutDate(rs.getDate("check_out_date"));
                    checkOut.setReason(rs.getString("reason"));
                    checkOut.setNote(rs.getString("note"));
                    checkOut.setCreateTime(rs.getTimestamp("create_time"));
                    checkOutList.add(checkOut);
                }
            } else {
                System.err.println("数据库连接失败，无法执行查询");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(conn, pstmt, rs);
        }
        return checkOutList;
    }
    
    /**
     * 根据ID查询退宿记录
     * @param id 退宿记录ID
     * @return 退宿记录对象
     */
    public CheckOut findById(Integer id) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        CheckOut checkOut = null;
        try {
            conn = DBUtil.getConnection();
            if (conn != null) {
                String sql = "SELECT * FROM check_out WHERE id = ?";
                pstmt = conn.prepareStatement(sql);
                pstmt.setInt(1, id);
                rs = pstmt.executeQuery();
                if (rs.next()) {
                    checkOut = new CheckOut();
                    checkOut.setId(rs.getInt("id"));
                    checkOut.setStudentId(rs.getInt("student_id"));
                    checkOut.setDormitoryId(rs.getInt("dormitory_id"));
                    checkOut.setCheckOutDate(rs.getDate("check_out_date"));
                    checkOut.setReason(rs.getString("reason"));
                    checkOut.setNote(rs.getString("note"));
                    checkOut.setCreateTime(rs.getTimestamp("create_time"));
                }
            } else {
                System.err.println("数据库连接失败，无法执行查询");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(conn, pstmt, rs);
        }
        return checkOut;
    }
    
    /**
     * 添加退宿记录
     * @param checkOut 退宿记录对象
     * @return 添加成功返回1，否则返回0
     */
    public int add(CheckOut checkOut) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        int result = 0;
        try {
            conn = DBUtil.getConnection();
            if (conn != null) {
                String sql = "INSERT INTO check_out (student_id, dormitory_id, check_out_date, reason, note) VALUES (?, ?, ?, ?, ?)";
                pstmt = conn.prepareStatement(sql);
                pstmt.setInt(1, checkOut.getStudentId());
                pstmt.setInt(2, checkOut.getDormitoryId());
                pstmt.setDate(3, new java.sql.Date(checkOut.getCheckOutDate().getTime()));
                pstmt.setString(4, checkOut.getReason());
                pstmt.setString(5, checkOut.getNote());
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
}
