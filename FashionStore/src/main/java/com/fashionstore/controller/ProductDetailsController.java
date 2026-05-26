package com.fashionstore.controller;

import java.io.IOException;
import java.util.List;

import com.fashionstore.dao.product.ProductDAO;
import com.fashionstore.dao.product.ProductDAOImpl;
import com.fashionstore.model.Product;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

@WebServlet("/product")
public class ProductDetailsController extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        int productId = Integer.parseInt(request.getParameter("id"));

        ProductDAO dao = new ProductDAOImpl();

        Product product = dao.getProductById(productId);
        List<String> sizes = dao.getAvailableSizes(productId);

        request.setAttribute("product", product);
        request.setAttribute("sizes", sizes);

        request.getRequestDispatcher("/WEB-INF/views/product-details.jsp")
               .forward(request, response);
    }
}