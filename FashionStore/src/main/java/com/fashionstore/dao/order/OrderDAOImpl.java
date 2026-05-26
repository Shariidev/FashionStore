package com.fashionstore.dao.order;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.fashionstore.model.Order;
import com.fashionstore.model.OrderItem;
import com.fashionstore.util.DBConnection;

public class OrderDAOImpl implements OrderDAO {

    private Connection conn;

    public OrderDAOImpl() {
        conn = DBConnection.getConnection();
    }

    // ================= CREATE =================

    @Override
    public int createOrder(Order order) {

        int orderId = 0;

        String sql = "INSERT INTO orders (user_id, total_amount, status, address_line, city, state, pincode) VALUES (?, ?, ?, ?, ?, ?, ?)";

        try {
            PreparedStatement ps = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);

            ps.setInt(1, order.getUserId());
            ps.setBigDecimal(2, order.getTotalAmount());
            ps.setString(3, order.getStatus());
            ps.setString(4, order.getAddressLine());
            ps.setString(5, order.getCity());
            ps.setString(6, order.getState());
            ps.setString(7, order.getPincode());

            ps.executeUpdate();

            ResultSet rs = ps.getGeneratedKeys();

            if (rs.next()) {
                orderId = rs.getInt(1);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return orderId;
    }

    @Override
    public boolean addOrderItems(int orderId, List<OrderItem> items) {

        boolean status = false;

        String sql = "INSERT INTO order_items (order_id, product_id, size, quantity, price) VALUES (?, ?, ?, ?, ?)";

        try {
            PreparedStatement ps = conn.prepareStatement(sql);

            for (OrderItem item : items) {

                ps.setInt(1, orderId);
                ps.setInt(2, item.getProductId());
                ps.setString(3, item.getSize());
                ps.setInt(4, item.getQuantity());
                ps.setBigDecimal(5, item.getPrice());

                ps.addBatch();
            }

            ps.executeBatch();
            status = true;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return status;
    }

    // ================= FETCH =================

    @Override
    public List<Order> getOrdersByUser(int userId) {

        List<Order> list = new ArrayList<>();

        String sql = "SELECT * FROM orders WHERE user_id=? ORDER BY order_date DESC";

        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, userId);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Order order = new Order();

                order.setOrderId(rs.getInt("order_id"));
                order.setUserId(rs.getInt("user_id"));
                order.setTotalAmount(rs.getBigDecimal("total_amount"));
                order.setStatus(rs.getString("status"));
                order.setOrderDate(rs.getTimestamp("order_date"));
                order.setAddressLine(rs.getString("address_line"));
                order.setCity(rs.getString("city"));
                order.setState(rs.getString("state"));
                order.setPincode(rs.getString("pincode"));

                list.add(order);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }

    @Override
    public Order getOrderById(int orderId) {

        Order order = null;

        String sql = "SELECT * FROM orders WHERE order_id=?";

        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, orderId);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                order = new Order();

                order.setOrderId(rs.getInt("order_id"));
                order.setUserId(rs.getInt("user_id"));
                order.setTotalAmount(rs.getBigDecimal("total_amount"));
                order.setStatus(rs.getString("status"));
                order.setOrderDate(rs.getTimestamp("order_date"));
                order.setAddressLine(rs.getString("address_line"));
                order.setCity(rs.getString("city"));
                order.setState(rs.getString("state"));
                order.setPincode(rs.getString("pincode"));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return order;
    }

    @Override
    public List<OrderItem> getOrderItems(int orderId) {

        List<OrderItem> list = new ArrayList<>();

        String sql = "SELECT * FROM order_items WHERE order_id=?";

        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, orderId);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                OrderItem item = new OrderItem();

                item.setOrderItemId(rs.getInt("order_item_id"));
                item.setOrderId(rs.getInt("order_id"));
                item.setProductId(rs.getInt("product_id"));
                item.setSize(rs.getString("size"));
                item.setQuantity(rs.getInt("quantity"));
                item.setPrice(rs.getBigDecimal("price"));

                list.add(item);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }

    // ================= STATUS =================

    @Override
    public boolean updateOrderStatus(int orderId, String statusValue) {

        boolean status = false;

        String sql = "UPDATE orders SET status=? WHERE order_id=?";

        try {
            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setString(1, statusValue);
            ps.setInt(2, orderId);

            int rows = ps.executeUpdate();

            if (rows > 0) {
                status = true;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return status;
    }
}