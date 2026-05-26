package com.fashionstore.dao.cart;

import java.util.List;
import com.fashionstore.model.CartItem;

public interface CartDAO {

    // ================= ADD =================

    // Add item to cart (if exists → increase quantity)
    boolean addToCart(int userId, int productId, String size, int quantity);

    // ================= FETCH =================

    // Get all cart items for a user
    List<CartItem> getCartItemsByUser(int userId);

    // Get single cart item (used internally)
    CartItem getCartItem(int userId, int productId, String size);

    // ================= UPDATE =================

    // Update quantity
    boolean updateQuantity(int userId, int productId, String size, int quantity);

    // ================= REMOVE =================

    // Remove one item from cart
    boolean removeFromCart(int userId, int productId, String size);

    // Clear entire cart
    boolean clearCart(int userId);
}