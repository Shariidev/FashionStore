package com.fashionstore.dao.order;

import java.util.List;
import com.fashionstore.model.Order;
import com.fashionstore.model.OrderItem;

public interface OrderDAO {

    // ================= CREATE =================

    // Place order (main checkout method)
    int createOrder(Order order);

    // Add items to order
    boolean addOrderItems(int orderId, List<OrderItem> items);

    // ================= FETCH =================

    // Get all orders for a user (Order History)
    List<Order> getOrdersByUser(int userId);

    // Get single order details
    Order getOrderById(int orderId);

    // Get items of a specific order
    List<OrderItem> getOrderItems(int orderId);

    // ================= STATUS =================

    // Update order status (PLACED, SHIPPED, DELIVERED)
    boolean updateOrderStatus(int orderId, String status);
}