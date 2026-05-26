package com.fashionstore.controller;

import java.io.IOException;
import java.sql.*;
import java.util.*;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

@WebServlet("/order-details")
public class OrderDetailsController extends HttpServlet {

    private static final String URL = "jdbc:mysql://localhost:3306/fashion_store";
    private static final String USER = "root";
    private static final String PASS = "root";

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        int orderId = Integer.parseInt(request.getParameter("id"));

        Map<String, Object> order = new HashMap<>();
        List<Map<String, Object>> items = new ArrayList<>();

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection(URL, USER, PASS);

            // 🔥 Get order
            PreparedStatement orderStmt = con.prepareStatement(
                "SELECT * FROM orders WHERE order_id=?"
            );
            orderStmt.setInt(1, orderId);

            ResultSet rs = orderStmt.executeQuery();

            if (rs.next()) {
                order.put("id", rs.getInt("order_id"));
                order.put("total", rs.getDouble("total_amount"));
                order.put("status", rs.getString("status"));
                order.put("date", rs.getTimestamp("order_date"));
            }

            // 🔥 Get order items + product name + image
            PreparedStatement itemStmt = con.prepareStatement(
                "SELECT oi.*, p.name, p.image_url " +
                "FROM order_items oi " +
                "JOIN products p ON oi.product_id = p.product_id " +
                "WHERE oi.order_id=?"
            );
            itemStmt.setInt(1, orderId);

            ResultSet rs2 = itemStmt.executeQuery();

            while (rs2.next()) {
                Map<String, Object> item = new HashMap<>();

                item.put("name", rs2.getString("name"));
                item.put("image", rs2.getString("image_url"));
                item.put("size", rs2.getString("size"));
                item.put("quantity", rs2.getInt("quantity"));
                item.put("price", rs2.getDouble("price"));

                items.add(item);
            }

            con.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        request.setAttribute("order", order);
        request.setAttribute("items", items);

        request.getRequestDispatcher("/WEB-INF/views/order-details.jsp")
               .forward(request, response);
    }
}