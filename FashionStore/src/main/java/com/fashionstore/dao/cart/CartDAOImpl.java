package com.fashionstore.dao.cart;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.fashionstore.model.CartItem;
import com.fashionstore.util.DBConnection;

public class CartDAOImpl implements CartDAO {

    private Connection conn;

    public CartDAOImpl() {
        conn = DBConnection.getConnection();
    }

    // ================= ADD =================

    @Override
    public boolean addToCart(int userId, int productId, String size, int quantity) {

        boolean status = false;

        try {
            // Step 1: check if item already exists
            String checkSql = "SELECT * FROM cart_items WHERE user_id=? AND product_id=? AND size=?";
            PreparedStatement checkPs = conn.prepareStatement(checkSql);

            checkPs.setInt(1, userId);
            checkPs.setInt(2, productId);
            checkPs.setString(3, size);

            ResultSet rs = checkPs.executeQuery();

            if (rs.next()) {
                // already exists → update quantity
                int existingQty = rs.getInt("quantity");
                int newQty = existingQty + quantity;

                String updateSql = "UPDATE cart_items SET quantity=? WHERE user_id=? AND product_id=? AND size=?";
                PreparedStatement updatePs = conn.prepareStatement(updateSql);

                updatePs.setInt(1, newQty);
                updatePs.setInt(2, userId);
                updatePs.setInt(3, productId);
                updatePs.setString(4, size);

                updatePs.executeUpdate();
                status = true;

            } else {
                // insert new item
                String insertSql = "INSERT INTO cart_items (user_id, product_id, size, quantity) VALUES (?, ?, ?, ?)";
                PreparedStatement insertPs = conn.prepareStatement(insertSql);

                insertPs.setInt(1, userId);
                insertPs.setInt(2, productId);
                insertPs.setString(3, size);
                insertPs.setInt(4, quantity);

                insertPs.executeUpdate();
                status = true;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return status;
    }

    // ================= FETCH =================

    @Override
    public List<CartItem> getCartItemsByUser(int userId) {

        List<CartItem> list = new ArrayList<>();

        String sql = "SELECT * FROM cart_items WHERE user_id=?";

        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, userId);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                CartItem item = new CartItem();

                item.setCartItemId(rs.getInt("cart_item_id"));
                item.setUserId(rs.getInt("user_id"));
                item.setProductId(rs.getInt("product_id"));
                item.setSize(rs.getString("size"));
                item.setQuantity(rs.getInt("quantity"));

                list.add(item);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }

    @Override
    public CartItem getCartItem(int userId, int productId, String size) {

        CartItem item = null;

        String sql = "SELECT * FROM cart_items WHERE user_id=? AND product_id=? AND size=?";

        try {
            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setInt(1, userId);
            ps.setInt(2, productId);
            ps.setString(3, size);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                item = new CartItem();

                item.setCartItemId(rs.getInt("cart_item_id"));
                item.setUserId(rs.getInt("user_id"));
                item.setProductId(rs.getInt("product_id"));
                item.setSize(rs.getString("size"));
                item.setQuantity(rs.getInt("quantity"));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return item;
    }

    // ================= UPDATE =================

    @Override
    public boolean updateQuantity(int userId, int productId, String size, int quantity) {

        boolean status = false;

        String sql = "UPDATE cart_items SET quantity=? WHERE user_id=? AND product_id=? AND size=?";

        try {
            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setInt(1, quantity);
            ps.setInt(2, userId);
            ps.setInt(3, productId);
            ps.setString(4, size);

            int rows = ps.executeUpdate();

            if (rows > 0) {
                status = true;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return status;
    }

    // ================= REMOVE =================

    @Override
    public boolean removeFromCart(int userId, int productId, String size) {

        boolean status = false;

        String sql = "DELETE FROM cart_items WHERE user_id=? AND product_id=? AND size=?";

        try {
            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setInt(1, userId);
            ps.setInt(2, productId);
            ps.setString(3, size);

            int rows = ps.executeUpdate();

            if (rows > 0) {
                status = true;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return status;
    }

    @Override
    public boolean clearCart(int userId) {

        boolean status = false;

        String sql = "DELETE FROM cart_items WHERE user_id=?";

        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, userId);

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