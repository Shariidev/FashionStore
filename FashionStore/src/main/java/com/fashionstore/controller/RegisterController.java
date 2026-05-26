package com.fashionstore.controller;

import java.io.IOException;
import java.sql.*;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

@WebServlet("/register")
public class RegisterController extends HttpServlet {

    private static final String URL =
            "jdbc:mysql://localhost:3306/fashion_store";

    private static final String USER = "root";
    private static final String PASS = "root";

    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
            throws ServletException, IOException {

        request.getRequestDispatcher("/WEB-INF/views/register.jsp")
               .forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response)
            throws ServletException, IOException {

        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String phone = request.getParameter("phone");
        String password = request.getParameter("password");
        String address = request.getParameter("address");
        String city = request.getParameter("city");
        String state = request.getParameter("state");
        String pincode = request.getParameter("pincode");

        try {

            Class.forName("com.mysql.cj.jdbc.Driver");

            Connection con = DriverManager.getConnection(
                    URL, USER, PASS
            );

            String sql =
                "INSERT INTO users " +
                "(name,email,phone,password,address_line,city,state,pincode) " +
                "VALUES (?,?,?,?,?,?,?,?)";

            PreparedStatement ps = con.prepareStatement(sql);

            ps.setString(1, name);
            ps.setString(2, email);
            ps.setString(3, phone);
            ps.setString(4, password);
            ps.setString(5, address);
            ps.setString(6, city);
            ps.setString(7, state);
            ps.setString(8, pincode);

            ps.executeUpdate();

            con.close();

            response.sendRedirect("login");

        } catch (Exception e) {

            e.printStackTrace();

            request.setAttribute("error",
                    "Registration failed. Email may already exist.");

            request.getRequestDispatcher(
                    "/WEB-INF/views/register.jsp")
                    .forward(request, response);
        }
    }
}