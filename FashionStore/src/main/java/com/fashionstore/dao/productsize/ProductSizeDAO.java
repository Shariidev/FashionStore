package com.fashionstore.dao.productsize;

import java.util.List;
import com.fashionstore.model.ProductSize;

public interface ProductSizeDAO {

    // ================= FETCH =================

    // Get all sizes for a product
    List<ProductSize> getSizesByProductId(int productId);

    // Get specific size details (stock check)
    ProductSize getProductSize(int productId, String size);

    // Get available sizes only (stock > 0)
    List<String> getAvailableSizes(int productId);

    // ================= STOCK =================

    // Update stock after order
    boolean updateStock(int productId, String size, int quantity);

    // Reduce stock (used during checkout)
    boolean reduceStock(int productId, String size, int quantity);

    // Increase stock (optional - future use like returns)
    boolean increaseStock(int productId, String size, int quantity);
}