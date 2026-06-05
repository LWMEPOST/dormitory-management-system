package com.dormitory.dao;

import com.dormitory.model.User;
import com.dormitory.util.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * 用户数据访问层
 */
public class UserDao {
    
    /**
     * 根据用户名和密码查询用户
     * @param username 用户名
     * @param password 密码
     * @return 用户对象
     */
    public User findByUsernameAndPassword(String username, String password) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        User user = null;
        try {
            conn = DBUtil.getConnection();
            if (conn != null) {
                String sql = "SELECT * FROM user WHERE username = ? AND password = ?";
                pstmt = conn.prepareStatement(sql);
                pstmt.setString(1, username);
                pstmt.setString(2, password);
                rs = pstmt.executeQuery();
                if (rs.next()) {
                    user = new User();
                    user.setId(rs.getInt("id"));
                    user.setUsername(rs.getString("username"));
                    user.setPassword(rs.getString("password"));
                    user.setRole(rs.getInt("role"));
                    user.setName(rs.getString("name"));
                    user.setPhone(rs.getString("phone"));
                    user.setCreateTime(rs.getTimestamp("create_time"));
                }
            } else {
                System.err.println("数据库连接失败，无法执行查询");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(conn, pstmt, rs);
        }
        return user;
    }
    
    /**
     * 查询所有用户
     * @return 用户列表
     */
    public List<User> findAll() {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        List<User> userList = new ArrayList<>();
        try {
            conn = DBUtil.getConnection();
            if (conn != null) {
                String sql = "SELECT * FROM user";
                pstmt = conn.prepareStatement(sql);
                rs = pstmt.executeQuery();
                while (rs.next()) {
                    User user = new User();
                    user.setId(rs.getInt("id"));
                    user.setUsername(rs.getString("username"));
                    user.setPassword(rs.getString("password"));
                    user.setRole(rs.getInt("role"));
                    user.setName(rs.getString("name"));
                    user.setPhone(rs.getString("phone"));
                    user.setCreateTime(rs.getTimestamp("create_time"));
                    userList.add(user);
                }
            } else {
                System.err.println("数据库连接失败，无法执行查询");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(conn, pstmt, rs);
        }
        return userList;
    }
    
    /**
     * 根据ID查询用户
     * @param id 用户ID
     * @return 用户对象
     */
    public User findById(Integer id) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        User user = null;
        try {
            conn = DBUtil.getConnection();
            if (conn != null) {
                String sql = "SELECT * FROM user WHERE id = ?";
                pstmt = conn.prepareStatement(sql);
                pstmt.setInt(1, id);
                rs = pstmt.executeQuery();
                if (rs.next()) {
                    user = new User();
                    user.setId(rs.getInt("id"));
                    user.setUsername(rs.getString("username"));
                    user.setPassword(rs.getString("password"));
                    user.setRole(rs.getInt("role"));
                    user.setName(rs.getString("name"));
                    user.setPhone(rs.getString("phone"));
                    user.setCreateTime(rs.getTimestamp("create_time"));
                }
            } else {
                System.err.println("数据库连接失败，无法执行查询");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(conn, pstmt, rs);
        }
        return user;
    }
    
    /**
     * 添加用户
     * @param user 用户对象
     * @return 添加成功返回1，否则返回0
     */
    public int add(User user) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        int result = 0;
        try {
            conn = DBUtil.getConnection();
            if (conn != null) {
                String sql = "INSERT INTO user (username, password, role, name, phone) VALUES (?, ?, ?, ?, ?)";
                pstmt = conn.prepareStatement(sql);
                pstmt.setString(1, user.getUsername());
                pstmt.setString(2, user.getPassword());
                pstmt.setInt(3, user.getRole());
                pstmt.setString(4, user.getName());
                pstmt.setString(5, user.getPhone());
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
     * 更新用户
     * @param user 用户对象
     * @return 更新成功返回1，否则返回0
     */
    public int update(User user) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        int result = 0;
        try {
            conn = DBUtil.getConnection();
            if (conn != null) {
                String sql = "UPDATE user SET username = ?, password = ?, role = ?, name = ?, phone = ? WHERE id = ?";
                pstmt = conn.prepareStatement(sql);
                pstmt.setString(1, user.getUsername());
                pstmt.setString(2, user.getPassword());
                pstmt.setInt(3, user.getRole());
                pstmt.setString(4, user.getName());
                pstmt.setString(5, user.getPhone());
                pstmt.setInt(6, user.getId());
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
     * 删除用户
     * @param id 用户ID
     * @return 删除成功返回1，否则返回0
     */
    public int delete(Integer id) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        int result = 0;
        try {
            conn = DBUtil.getConnection();
            if (conn != null) {
                String sql = "DELETE FROM user WHERE id = ?";
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
}
