package com.fashionstore.dao.product;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.fashionstore.model.Product;
import com.fashionstore.util.DBConnection;

public class ProductDAOImpl implements ProductDAO {

    private Connection conn;

    public ProductDAOImpl() {
        conn = DBConnection.getConnection();
    }

    // ================= BASIC =================

    @Override
    public List<Product> getAllProducts() {
        List<Product> list = new ArrayList<>();
        String sql = "SELECT * FROM products";

        try {
        	System.out.println("Fetching products...");
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Product p = new Product();

                p.setProductId(rs.getInt("product_id"));
                p.setName(rs.getString("name"));
                p.setDescription(rs.getString("description"));
                p.setPrice(rs.getBigDecimal("price"));
                p.setCategoryId(rs.getInt("category_id"));
                p.setImageUrl(rs.getString("image_url"));

                list.add(p);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }

    @Override
    public Product getProductById(int productId) {
        Product p = null;

        String sql = "SELECT * FROM products WHERE product_id=?";

        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, productId);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                p = new Product();

                p.setProductId(rs.getInt("product_id"));
                p.setName(rs.getString("name"));
                p.setDescription(rs.getString("description"));
                p.setPrice(rs.getBigDecimal("price"));
                p.setCategoryId(rs.getInt("category_id"));
                p.setImageUrl(rs.getString("image_url"));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return p;
    }

    // ================= CATEGORY =================

    @Override
    public List<Product> getProductsByCategory(int categoryId) {
        List<Product> list = new ArrayList<>();

        String sql = "SELECT * FROM products WHERE category_id=?";

        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, categoryId);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Product p = new Product();

                p.setProductId(rs.getInt("product_id"));
                p.setName(rs.getString("name"));
                p.setDescription(rs.getString("description"));
                p.setPrice(rs.getBigDecimal("price"));
                p.setCategoryId(rs.getInt("category_id"));
                p.setImageUrl(rs.getString("image_url"));

                list.add(p);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }

    // ================= SEARCH =================

    @Override
    public List<Product> searchProducts(String keyword) {
        List<Product> list = new ArrayList<>();

        String sql = "SELECT * FROM products WHERE name LIKE ? OR description LIKE ?";

        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, "%" + keyword + "%");
            ps.setString(2, "%" + keyword + "%");

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Product p = new Product();

                p.setProductId(rs.getInt("product_id"));
                p.setName(rs.getString("name"));
                p.setDescription(rs.getString("description"));
                p.setPrice(rs.getBigDecimal("price"));
                p.setCategoryId(rs.getInt("category_id"));
                p.setImageUrl(rs.getString("image_url"));

                list.add(p);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }

    // ================= FILTER =================

    @Override
    public List<Product> getFilteredProducts(Integer categoryId, BigDecimal minPrice, BigDecimal maxPrice) {
        List<Product> list = new ArrayList<>();

        StringBuilder sql = new StringBuilder("SELECT * FROM products WHERE 1=1");

        if (categoryId != null) {
            sql.append(" AND category_id=?");
        }

        if (minPrice != null) {
            sql.append(" AND price >= ?");
        }

        if (maxPrice != null) {
            sql.append(" AND price <= ?");
        }

        try {
            PreparedStatement ps = conn.prepareStatement(sql.toString());

            int index = 1;

            if (categoryId != null) {
                ps.setInt(index++, categoryId);
            }

            if (minPrice != null) {
                ps.setBigDecimal(index++, minPrice);
            }

            if (maxPrice != null) {
                ps.setBigDecimal(index++, maxPrice);
            }

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Product p = new Product();

                p.setProductId(rs.getInt("product_id"));
                p.setName(rs.getString("name"));
                p.setDescription(rs.getString("description"));
                p.setPrice(rs.getBigDecimal("price"));
                p.setCategoryId(rs.getInt("category_id"));
                p.setImageUrl(rs.getString("image_url"));

                list.add(p);
            }
            System.out.println("Products found: " + list.size());

        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }

    // ================= SIZE =================

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

    @Override
    public int getStockByProductAndSize(int productId, String size) {

        int stock = 0;

        String sql = "SELECT stock_quantity FROM product_sizes WHERE product_id=? AND size=?";

        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, productId);
            ps.setString(2, size);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                stock = rs.getInt("stock_quantity");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return stock;
    }
}