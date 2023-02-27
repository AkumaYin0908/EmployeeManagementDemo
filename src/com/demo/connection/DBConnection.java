package com.demo.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {

    private final String url = "jdbc:mysql://localhost:3307/dbemployee";
    private final String user = "root";
    private final String pass = "";
    private Connection sqlConn;

    public void connectDB() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            sqlConn = DriverManager.getConnection(url, user, pass);
            //System.out.println("Connection successful");
        } catch (ClassNotFoundException | SQLException ex) {
            System.out.println(ex);

        }
    }

    public Connection getSqlConn() {
        return sqlConn;
    }

}
