package com.fashionstore.controller;

import java.io.IOException;
import java.util.*;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

@WebServlet("/update-cart")
public class UpdateCartController extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        int productId = Integer.parseInt(request.getParameter("productId"));
        String size = request.getParameter("size");
        String action = request.getParameter("action");

        HttpSession session = request.getSession();

        List<Map<String, Object>> cart =
                (List<Map<String, Object>>) session.getAttribute("cart");

        if (cart != null) {

            Iterator<Map<String, Object>> iterator = cart.iterator();

            while (iterator.hasNext()) {

                Map<String, Object> item = iterator.next();

                int id = (int) item.get("productId");
                String s = (String) item.get("size");

                if (id == productId && s.equals(size)) {

                    int qty = (int) item.get("quantity");

                    if ("increase".equals(action)) {
                        item.put("quantity", qty + 1);
                    } 
                    else if ("decrease".equals(action)) {
                        if (qty > 1) {
                            item.put("quantity", qty - 1);
                        } else {
                            iterator.remove(); // remove if qty = 1
                        }
                    } 
                    else if ("remove".equals(action)) {
                        iterator.remove();
                    }

                    break;
                }
            }
        }

        response.sendRedirect("cart");
    }
}