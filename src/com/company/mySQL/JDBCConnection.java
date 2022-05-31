package com.company.mySQL;

import com.company.servicii.CSVReadService;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Arrays;

public class JDBCConnection {

    private static JDBCConnection instance;
    private static Connection con;

    private JDBCConnection() {

    }

    public static synchronized JDBCConnection getInstance() {
        if(instance == null) {
            instance = new JDBCConnection();
        }
        return instance;
    }

    public Connection initializeConnection() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con=DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/etapa3","root","ciscosecpa55");

            return con;
        }
        catch(Exception e) {
            System.out.println("Eroare:");
            System.out.println(e);
            return null;
        }
    }

    public void closeConnection() {

        try {
            if(con != null && !con.isClosed()) {
                con.close();
            }
        }
        catch(Exception e) {
            System.out.println("Nu a mers:");
            System.out.println(Arrays.toString(e.getStackTrace()));
        }
    }


}
