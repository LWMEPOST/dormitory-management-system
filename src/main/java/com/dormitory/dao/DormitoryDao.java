package com.dormitory.dao;

import com.dormitory.model.Dormitory;
import com.dormitory.util.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * 宿舍数据访问层
 */
public class DormitoryDao {
    
    /**
     * 查询所有宿舍
     * @return 宿舍列表
     */
    public List<Dormitory> findAll() {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        List<Dormitory> dormitoryList = new ArrayList<>();
        try {
            conn = DBUtil.getConnection();
            if (conn != null) {
                String sql = "SELECT * FROM dormitory";
                pstmt = conn.prepareStatement(sql);
                rs = pstmt.executeQuery();
                while (rs.next()) {
                    Dormitory dormitory = new Dormitory();
                    dormitory.setId(rs.getInt("id"));
                    dormitory.setDormitoryNumber(rs.getString("dormitory_number"));
                    dormitory.setBuilding(rs.getString("building"));
                    dormitory.setFloor(rs.getInt("floor"));
                    dormitory.setCapacity(rs.getInt("capacity"));
                    dormitory.setCurrentOccupancy(rs.getInt("current_occupancy"));
                    dormitory.setType(rs.getString("type"));
                    dormitory.setStatus(rs.getInt("status"));
                    dormitory.setCreateTime(rs.getTimestamp("create_time"));
                    dormitoryList.add(dormitory);
                }
            } else {
                System.err.println("数据库连接失败，无法执行查询");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(conn, pstmt, rs);
        }
        return dormitoryList;
    }
    
    /**
     * 根据ID查询宿舍
     * @param id 宿舍ID
     * @return 宿舍对象
     */
    public Dormitory findById(Integer id) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Dormitory dormitory = null;
        try {
            conn = DBUtil.getConnection();
            if (conn != null) {
                String sql = "SELECT * FROM dormitory WHERE id = ?";
                pstmt = conn.prepareStatement(sql);
                pstmt.setInt(1, id);
                rs = pstmt.executeQuery();
                if (rs.next()) {
                    dormitory = new Dormitory();
                    dormitory.setId(rs.getInt("id"));
                    dormitory.setDormitoryNumber(rs.getString("dormitory_number"));
                    dormitory.setBuilding(rs.getString("building"));
                    dormitory.setFloor(rs.getInt("floor"));
                    dormitory.setCapacity(rs.getInt("capacity"));
                    dormitory.setCurrentOccupancy(rs.getInt("current_occupancy"));
                    dormitory.setType(rs.getString("type"));
                    dormitory.setStatus(rs.getInt("status"));
                    dormitory.setCreateTime(rs.getTimestamp("create_time"));
                }
            } else {
                System.err.println("数据库连接失败，无法执行查询");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(conn, pstmt, rs);
        }
        return dormitory;
    }
    
    /**
     * 添加宿舍
     * @param dormitory 宿舍对象
     * @return 添加成功返回1，否则返回0
     */
    public int add(Dormitory dormitory) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        int result = 0;
        try {
            conn = DBUtil.getConnection();
            if (conn != null) {
                String sql = "INSERT INTO dormitory (dormitory_number, building, floor, capacity, type) VALUES (?, ?, ?, ?, ?)";
                pstmt = conn.prepareStatement(sql);
                pstmt.setString(1, dormitory.getDormitoryNumber());
                pstmt.setString(2, dormitory.getBuilding());
                pstmt.setInt(3, dormitory.getFloor());
                pstmt.setInt(4, dormitory.getCapacity());
                pstmt.setString(5, dormitory.getType());
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
     * 更新宿舍
     * @param dormitory 宿舍对象
     * @return 更新成功返回1，否则返回0
     */
    public int update(Dormitory dormitory) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        int result = 0;
        try {
            conn = DBUtil.getConnection();
            if (conn != null) {
                String sql = "UPDATE dormitory SET dormitory_number = ?, building = ?, floor = ?, capacity = ?, current_occupancy = ?, type = ?, status = ? WHERE id = ?";
                pstmt = conn.prepareStatement(sql);
                pstmt.setString(1, dormitory.getDormitoryNumber());
                pstmt.setString(2, dormitory.getBuilding());
                pstmt.setInt(3, dormitory.getFloor());
                pstmt.setInt(4, dormitory.getCapacity());
                pstmt.setInt(5, dormitory.getCurrentOccupancy());
                pstmt.setString(6, dormitory.getType());
                pstmt.setInt(7, dormitory.getStatus());
                pstmt.setInt(8, dormitory.getId());
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
     * 删除宿舍
     * @param id 宿舍ID
     * @return 删除成功返回1，否则返回0
     */
    public int delete(Integer id) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        int result = 0;
        try {
            conn = DBUtil.getConnection();
            if (conn != null) {
                String sql = "DELETE FROM dormitory WHERE id = ?";
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
     * 更新宿舍当前入住人数
     * @param dormitoryId 宿舍ID
     * @param increment 增加或减少的人数（正数表示增加，负数表示减少）
     * @return 更新成功返回1，否则返回0
     */
    public int updateCurrentOccupancy(Integer dormitoryId, int increment) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        int result = 0;
        try {
            conn = DBUtil.getConnection();
            if (conn != null) {
                String sql = "UPDATE dormitory SET current_occupancy = current_occupancy + ? WHERE id = ?";
                pstmt = conn.prepareStatement(sql);
                pstmt.setInt(1, increment);
                pstmt.setInt(2, dormitoryId);
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
