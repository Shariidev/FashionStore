package com.fashionstore.controller;

import java.io.IOException;
import java.sql.*;
import java.util.*;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

@WebServlet("/orders")
public class OrderHistoryController extends HttpServlet {

    private static final String URL = "jdbc:mysql://localhost:3306/fashion_store";
    private static final String USER = "root";
    private static final String PASS = "root";

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();
        Integer userId = (Integer) session.getAttribute("userId");

        if (userId == null) {
            response.sendRedirect("login");
            return;
        }

        List<Map<String, Object>> orders = new ArrayList<>();

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection(URL, USER, PASS);

            String sql = "SELECT * FROM orders WHERE user_id=? ORDER BY order_id DESC";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, userId);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Map<String, Object> order = new HashMap<>();

                order.put("id", rs.getInt("order_id"));
                order.put("total", rs.getDouble("total_amount"));
                order.put("status", rs.getString("status"));
                order.put("date", rs.getTimestamp("order_date"));

                orders.add(order);
            }

            con.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        request.setAttribute("orders", orders);
        request.getRequestDispatcher("/WEB-INF/views/order-history.jsp").forward(request, response);
    }
}