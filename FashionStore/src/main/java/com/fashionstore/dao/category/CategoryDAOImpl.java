package com.fashionstore.dao.category;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.fashionstore.model.Category;
import com.fashionstore.util.DBConnection;

public class CategoryDAOImpl implements CategoryDAO {

    private Connection conn;

    public CategoryDAOImpl() {
        conn = DBConnection.getConnection();
    }

    // ================= FETCH =================

    @Override
    public List<Category> getAllCategories() {
        List<Category> list = new ArrayList<>();

        String sql = "SELECT * FROM categories";

        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Category c = new Category();

                c.setCategoryId(rs.getInt("category_id"));
                c.setCategoryName(rs.getString("category_name"));

                list.add(c);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }

    @Override
    public Category getCategoryById(int categoryId) {
        Category c = null;

        String sql = "SELECT * FROM categories WHERE category_id=?";

        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, categoryId);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                c = new Category();

                c.setCategoryId(rs.getInt("category_id"));
                c.setCategoryName(rs.getString("category_name"));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return c;
    }

    @Override
    public Category getCategoryByName(String categoryName) {
        Category c = null;

        String sql = "SELECT * FROM categories WHERE category_name=?";

        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, categoryName);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                c = new Category();

                c.setCategoryId(rs.getInt("category_id"));
                c.setCategoryName(rs.getString("category_name"));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return c;
    }

    // ================= WRITE =================

    @Override
    public boolean addCategory(Category category) {
        boolean status = false;

        String sql = "INSERT INTO categories (category_name) VALUES (?)";

        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, category.getCategoryName());

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
    public boolean updateCategory(Category category) {
        boolean status = false;

        String sql = "UPDATE categories SET category_name=? WHERE category_id=?";

        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, category.getCategoryName());
            ps.setInt(2, category.getCategoryId());

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
    public boolean deleteCategory(int categoryId) {
        boolean status = false;

        String sql = "DELETE FROM categories WHERE category_id=?";

        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, categoryId);

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