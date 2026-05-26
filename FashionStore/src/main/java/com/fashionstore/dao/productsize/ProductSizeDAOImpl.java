package com.fashionstore.dao.productsize;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.fashionstore.model.ProductSize;
import com.fashionstore.util.DBConnection;

public class ProductSizeDAOImpl implements ProductSizeDAO {

    private Connection conn;

    public ProductSizeDAOImpl() {
        conn = DBConnection.getConnection();
    }

    // ================= FETCH =================

    @Override
    public List<ProductSize> getSizesByProductId(int productId) {

        List<ProductSize> list = new ArrayList<>();

        String sql = "SELECT * FROM product_sizes WHERE product_id=?";

        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, productId);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                ProductSize psObj = new ProductSize();

                psObj.setSizeId(rs.getInt("size_id"));
                psObj.setProductId(rs.getInt("product_id"));
                psObj.setSize(rs.getString("size"));
                psObj.setStockQuantity(rs.getInt("stock_quantity"));

                list.add(psObj);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }

    @Override
    public ProductSize getProductSize(int productId, String size) {

        ProductSize psObj = null;

        String sql = "SELECT * FROM product_sizes WHERE product_id=? AND size=?";

        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, productId);
            ps.setString(2, size);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                psObj = new ProductSize();

                psObj.setSizeId(rs.getInt("size_id"));
                psObj.setProductId(rs.getInt("product_id"));
                psObj.setSize(rs.getString("size"));
                psObj.setStockQuantity(rs.getInt("stock_quantity"));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return psObj;
    }

    @Override
    public List<String> getAvailableSizes(int productId) {

        List<String> sizes = new ArrayList<>();

        String sql = "SELECT size FROM product_sizes WHERE product_id=? AND stock_quantity > 0";

        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, productId);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                sizes.add(rs.getString("size"));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return sizes;
    }

    // ================= STOCK =================

    @Override
    public boolean updateStock(int productId, String size, int quantity) {

        boolean status = false;

        String sql = "UPDATE product_sizes SET stock_quantity=? WHERE product_id=? AND size=?";

        try {
            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setInt(1, quantity);
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
    public boolean reduceStock(int productId, String size, int quantity) {

        boolean status = false;

        String sql = "UPDATE product_sizes SET stock_quantity = stock_quantity - ? WHERE product_id=? AND size=? AND stock_quantity >= ?";

        try {
            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setInt(1, quantity);
            ps.setInt(2, productId);
            ps.setString(3, size);
            ps.setInt(4, quantity);

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
    public boolean increaseStock(int productId, String size, int quantity) {

        boolean status = false;

        String sql = "UPDATE product_sizes SET stock_quantity = stock_quantity + ? WHERE product_id=? AND size=?";

        try {
            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setInt(1, quantity);
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
}