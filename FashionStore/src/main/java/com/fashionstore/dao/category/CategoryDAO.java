package com.fashionstore.dao.category;

import java.util.List;
import com.fashionstore.model.Category;

public interface CategoryDAO {

    // ================= FETCH =================

    // Get all categories
    List<Category> getAllCategories();

    // Get category by ID
    Category getCategoryById(int categoryId);

    // Get category by name
    Category getCategoryByName(String categoryName);

    // ================= WRITE =================

    // Add new category
    boolean addCategory(Category category);

    // Update category
    boolean updateCategory(Category category);

    // Delete category
    boolean deleteCategory(int categoryId);
}