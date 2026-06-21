package com.fashionstore.util;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnection {

    private static Connection conn;

    public static Connection getConnection() {

        try {
            if (conn == null) {

                Class.forName("com.mysql.cj.jdbc.Driver");

                conn = DriverManager.getConnection(
                		"jdbc:mysql://reseau.proxy.rlwy.net:55510/railway",
                    "root",
                    "rErtUgpOURjcodlZyxsAkNTsnOCpSZkS"
                );

                System.out.println("Database Connected Successfully");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return conn;
    }
}