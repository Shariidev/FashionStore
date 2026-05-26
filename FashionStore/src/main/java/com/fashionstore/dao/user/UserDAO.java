package com.fashionstore.dao.user;

import com.fashionstore.model.User;

public interface UserDAO {

    // ================= AUTH =================

    // Register new user
    boolean registerUser(User user);

    // Login (email + password)
    User loginUser(String email, String password);

    // ================= FETCH =================

    // Get user by ID
    User getUserById(int userId);

    // Get user by email (used internally)
    User getUserByEmail(String email);

    // ================= UPDATE =================

    // Update full user details (including address)
    boolean updateUser(User user);

    // Update only address fields
    boolean updateUserAddress(int userId, String addressLine, String city, String state, String pincode);

    // Update password
    boolean updatePassword(int userId, String newPassword);

    // ================= VALIDATION =================

    // Check if email already exists
    boolean isEmailExists(String email);
}
