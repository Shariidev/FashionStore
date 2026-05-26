package com.fashionstore.util;

import java.sql.Connection;

public class TestDBConnection {

    public static void main(String[] args) {

        try {
            Connection con = DBConnection.getConnection();

            if (con != null) {
                System.out.println("Database connected successfully!");
            } else {
                System.out.println("Connection failed!");
            }

        } catch (Exception e) {
            System.out.println("Error occurred:");
            e.printStackTrace();
        }
    }
}