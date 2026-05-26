package com.fashionstore.controller;

import java.io.IOException;
import java.util.List;

import com.fashionstore.dao.product.ProductDAO;
import com.fashionstore.dao.product.ProductDAOImpl;
import com.fashionstore.model.Product;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

@WebServlet("/products")
public class ProductController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String categoryParam = request.getParameter("category");

        ProductDAO dao = new ProductDAOImpl();
        List<Product> products;

        if (categoryParam != null && !categoryParam.isEmpty()) {
            int categoryId = Integer.parseInt(categoryParam);
            products = dao.getProductsByCategory(categoryId);
        } else {
            products = dao.getAllProducts();
        }

        request.setAttribute("products", products);

        request.getRequestDispatcher("/WEB-INF/views/products.jsp")
               .forward(request, response);
    }
}