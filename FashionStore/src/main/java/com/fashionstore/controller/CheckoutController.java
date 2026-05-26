package com.fashionstore.controller;

import java.io.IOException;
import java.sql.*;
import java.util.*;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

@WebServlet("/checkout")
public class CheckoutController extends HttpServlet {

    private static final String URL = "jdbc:mysql://localhost:3306/fashion_store";
    private static final String USER = "root";
    private static final String PASS = "root";

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();

        List<Map<String, Object>> cart =
                (List<Map<String, Object>>) session.getAttribute("cart");

        if (cart == null || cart.isEmpty()) {
            response.sendRedirect("cart");
            return;
        }

        Integer userId = (Integer) session.getAttribute("userId");

        if (userId == null) {
            response.sendRedirect("login");
            return;
        }
        double total = 0;

        int orderId = 0; // ✅ MUST BE HERE (outside try)

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection(URL, USER, PASS);

            // TOTAL
            for (Map<String, Object> item : cart) {
                double price = Double.parseDouble(item.get("price").toString());
                int qty = (int) item.get("quantity");
                total += price * qty;
            }

            // INSERT ORDER
            String orderSql = "INSERT INTO orders (user_id, total_amount, status, address_line, city, state, pincode) VALUES (?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement orderStmt = con.prepareStatement(orderSql, Statement.RETURN_GENERATED_KEYS);

            orderStmt.setInt(1, userId);
            orderStmt.setDouble(2, total);
            orderStmt.setString(3, "PLACED");

            orderStmt.setString(4, "Default Address");
            orderStmt.setString(5, "City");
            orderStmt.setString(6, "State");
            orderStmt.setString(7, "000000");

            orderStmt.executeUpdate();

            ResultSet rs = orderStmt.getGeneratedKeys();

            if (rs.next()) {
                orderId = rs.getInt(1); // ✅ assigned here
            }

            // INSERT ITEMS
            String itemSql = "INSERT INTO order_items (order_id, product_id, size, quantity, price) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement itemStmt = con.prepareStatement(itemSql);

            for (Map<String, Object> item : cart) {

                itemStmt.setInt(1, orderId);
                itemStmt.setInt(2, (int) item.get("productId"));
                itemStmt.setString(3, (String) item.get("size"));
                itemStmt.setInt(4, (int) item.get("quantity"));
                itemStmt.setDouble(5, Double.parseDouble(item.get("price").toString()));

                itemStmt.addBatch();
            }

            itemStmt.executeBatch();

            session.removeAttribute("cart");

            con.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        // ✅ NOW THIS WORKS
        response.sendRedirect("order-details?id=" + orderId);
    }
}