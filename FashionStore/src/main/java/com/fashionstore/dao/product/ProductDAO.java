package com.fashionstore.dao.product;

import java.math.BigDecimal;
import java.util.List;

import com.fashionstore.model.Product;

public interface ProductDAO {

    // ================= BASIC =================

    // Get all products
    List<Product> getAllProducts();

    // Get single product (Product Details Page)
    Product getProductById(int productId);

    // ================= CATEGORY =================

    // Get products by category (Men / Women)
    List<Product> getProductsByCategory(int categoryId);

    // ================= SEARCH =================

    // Search by name or description
    List<Product> searchProducts(String keyword);

    // ================= FILTER =================

    // Filter (category + price range)
    List<Product> getFilteredProducts(Integer categoryId, BigDecimal minPrice, BigDecimal maxPrice);

    // ================= SIZE (IMPORTANT - matches product_sizes table) =================

    // Get available sizes for a product
    List<String> getAvailableSizes(int productId);

    // Get stock for specific product + size
    int getStockByProductAndSize(int productId, String size);
}