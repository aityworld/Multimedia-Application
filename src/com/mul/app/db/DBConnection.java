/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mul.app.db;

import java.sql.*;

/**
 *
 * @author Aity
 */
public class DBConnection {

    private Connection conn;

    /** Creates a new instance of MyDBConnection */
    public DBConnection() {
    }

    public void init(String database, String user, String pass) {

        try {
            Class.forName("SQLite.JDBCDriver");
            conn = DriverManager.getConnection("jdbc:sqlite:/"+database+"", user, pass);
        } catch (Exception e) {
            System.out.println("Failed to get connection to embedded database \n"
                    +e.getMessage());
        }

    }

    public Connection getConnection() {
        return conn;
    }

    public void close(ResultSet rs) {
        if (rs != null) {
            try {
                rs.close();
            } catch (Exception e) {
            }
        }
    }

    public void close(java.sql.Statement stmt) {

        if (stmt != null) {
            try {
                stmt.close();
            } catch (Exception e) {
            }

        }
    }

    public void destroy() {
        if (conn != null) {
            try {
                conn.close();
            } catch (Exception e) {

            }
        }
    }
}
