package com.fashionstore.controller;

import java.io.IOException;
import java.util.*;

import com.fashionstore.dao.product.ProductDAO;
import com.fashionstore.dao.product.ProductDAOImpl;
import com.fashionstore.model.Product;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

@WebServlet("/add-to-cart")
public class AddToCartController extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        int productId = Integer.parseInt(request.getParameter("productId"));
        String size = request.getParameter("size");

        HttpSession session = request.getSession();

        List<Map<String, Object>> cart =
                (List<Map<String, Object>>) session.getAttribute("cart");

        if (cart == null) {
            cart = new ArrayList<>();
        }

        // 🔥 Get product details from DB
        ProductDAO dao = new ProductDAOImpl();
        Product p = dao.getProductById(productId);

        boolean itemExists = false;

        // 🔥 Check if same product + size already exists
        for (Map<String, Object> item : cart) {

            int existingId = (int) item.get("productId");
            String existingSize = (String) item.get("size");

            if (existingId == productId && existingSize.equals(size)) {
                int qty = (int) item.get("quantity");
                item.put("quantity", qty + 1);
                itemExists = true;
                break;
            }
        }

        // 🔥 If not exists → add new item
        if (!itemExists) {
            Map<String, Object> item = new HashMap<>();
            item.put("productId", productId);
            item.put("name", p.getName());
            item.put("price", p.getPrice());
            item.put("image", p.getImageUrl());
            item.put("size", size);
            item.put("quantity", 1);

            cart.add(item);
        }

        session.setAttribute("cart", cart);

        response.sendRedirect("cart");
    }
}