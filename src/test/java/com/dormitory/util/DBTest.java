package com.dormitory.util;

import java.sql.Connection;
import java.sql.SQLException;

public class DBTest {
    public static void main(String[] args) {
        System.out.println("Testing database connection...");
        Connection conn = DBUtil.getConnection();
        if (conn != null) {
            System.out.println("Connection successful!");
            try {
                conn.close();
                System.out.println("Connection closed.");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("Connection failed!");
        }
    }
}