package com.dormitory.dao;

import com.dormitory.model.Repair;
import com.dormitory.util.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * 报修数据访问层
 */
public class RepairDao {
    
    /**
     * 查询所有报修记录
     * @return 报修记录列表
     */
    public List<Repair> findAll() {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        List<Repair> repairList = new ArrayList<>();
        try {
            conn = DBUtil.getConnection();
            if (conn != null) {
                String sql = "SELECT * FROM repair";
                pstmt = conn.prepareStatement(sql);
                rs = pstmt.executeQuery();
                while (rs.next()) {
                    Repair repair = new Repair();
                    repair.setId(rs.getInt("id"));
                    repair.setStudentId(rs.getInt("student_id"));
                    repair.setDormitoryId(rs.getInt("dormitory_id"));
                    repair.setTitle(rs.getString("title"));
                    repair.setContent(rs.getString("content"));
                    repair.setStatus(rs.getInt("status"));
                    repair.setRepairDate(rs.getDate("repair_date"));
                    repair.setCompleteDate(rs.getDate("complete_date"));
                    repair.setHandler(rs.getString("handler"));
                    repair.setNote(rs.getString("note"));
                    repair.setCreateTime(rs.getTimestamp("create_time"));
                    repairList.add(repair);
                }
            } else {
                System.err.println("数据库连接失败，无法执行查询");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(conn, pstmt, rs);
        }
        return repairList;
    }
    
    /**
     * 根据ID查询报修记录
     * @param id 报修记录ID
     * @return 报修记录对象
     */
    public Repair findById(Integer id) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Repair repair = null;
        try {
            conn = DBUtil.getConnection();
            if (conn != null) {
                String sql = "SELECT * FROM repair WHERE id = ?";
                pstmt = conn.prepareStatement(sql);
                pstmt.setInt(1, id);
                rs = pstmt.executeQuery();
                if (rs.next()) {
                    repair = new Repair();
                    repair.setId(rs.getInt("id"));
                    repair.setStudentId(rs.getInt("student_id"));
                    repair.setDormitoryId(rs.getInt("dormitory_id"));
                    repair.setTitle(rs.getString("title"));
                    repair.setContent(rs.getString("content"));
                    repair.setStatus(rs.getInt("status"));
                    repair.setRepairDate(rs.getDate("repair_date"));
                    repair.setCompleteDate(rs.getDate("complete_date"));
                    repair.setHandler(rs.getString("handler"));
                    repair.setNote(rs.getString("note"));
                    repair.setCreateTime(rs.getTimestamp("create_time"));
                }
            } else {
                System.err.println("数据库连接失败，无法执行查询");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(conn, pstmt, rs);
        }
        return repair;
    }
    
    /**
     * 添加报修记录
     * @param repair 报修记录对象
     * @return 添加成功返回1，否则返回0
     */
    public int add(Repair repair) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        int result = 0;
        try {
            conn = DBUtil.getConnection();
            if (conn != null) {
                String sql = "INSERT INTO repair (student_id, dormitory_id, title, content, repair_date) VALUES (?, ?, ?, ?, ?)";
                pstmt = conn.prepareStatement(sql);
                pstmt.setInt(1, repair.getStudentId());
                pstmt.setInt(2, repair.getDormitoryId());
                pstmt.setString(3, repair.getTitle());
                pstmt.setString(4, repair.getContent());
                pstmt.setDate(5, new java.sql.Date(repair.getRepairDate().getTime()));
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
     * 更新报修记录
     * @param repair 报修记录对象
     * @return 更新成功返回1，否则返回0
     */
    public int update(Repair repair) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        int result = 0;
        try {
            conn = DBUtil.getConnection();
            if (conn != null) {
                String sql = "UPDATE repair SET status = ?, complete_date = ?, handler = ?, note = ? WHERE id = ?";
                pstmt = conn.prepareStatement(sql);
                pstmt.setInt(1, repair.getStatus());
                pstmt.setDate(2, repair.getCompleteDate() != null ? new java.sql.Date(repair.getCompleteDate().getTime()) : null);
                pstmt.setString(3, repair.getHandler());
                pstmt.setString(4, repair.getNote());
                pstmt.setInt(5, repair.getId());
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
}
