package com.fashionstore.dao.user;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.fashionstore.model.User;
import com.fashionstore.util.DBConnection;

public class UserDAOImpl implements UserDAO {

    private Connection conn;

    public UserDAOImpl() {
        conn = DBConnection.getConnection();
    }

    // ================= AUTH =================

    @Override
    public boolean registerUser(User user) {
        boolean status = false;

        String sql = "INSERT INTO users (name, email, phone, password, address_line, city, state, pincode) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        try {
            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setString(1, user.getName());
            ps.setString(2, user.getEmail());
            ps.setString(3, user.getPhone());
            ps.setString(4, user.getPassword());
            ps.setString(5, user.getAddressLine());
            ps.setString(6, user.getCity());
            ps.setString(7, user.getState());
            ps.setString(8, user.getPincode());

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
    public User loginUser(String email, String password) {
        User user = null;

        String sql = "SELECT * FROM users WHERE email=? AND password=?";

        try {
            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setString(1, email);
            ps.setString(2, password);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                user = new User();

                user.setUserId(rs.getInt("user_id"));
                user.setName(rs.getString("name"));
                user.setEmail(rs.getString("email"));
                user.setPhone(rs.getString("phone"));
                user.setPassword(rs.getString("password"));
                user.setAddressLine(rs.getString("address_line"));
                user.setCity(rs.getString("city"));
                user.setState(rs.getString("state"));
                user.setPincode(rs.getString("pincode"));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return user;
    }

    // ================= FETCH =================

    @Override
    public User getUserById(int userId) {
        User user = null;

        String sql = "SELECT * FROM users WHERE user_id=?";

        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, userId);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                user = new User();

                user.setUserId(rs.getInt("user_id"));
                user.setName(rs.getString("name"));
                user.setEmail(rs.getString("email"));
                user.setPhone(rs.getString("phone"));
                user.setPassword(rs.getString("password"));
                user.setAddressLine(rs.getString("address_line"));
                user.setCity(rs.getString("city"));
                user.setState(rs.getString("state"));
                user.setPincode(rs.getString("pincode"));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return user;
    }

    @Override
    public User getUserByEmail(String email) {
        User user = null;

        String sql = "SELECT * FROM users WHERE email=?";

        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, email);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                user = new User();

                user.setUserId(rs.getInt("user_id"));
                user.setName(rs.getString("name"));
                user.setEmail(rs.getString("email"));
                user.setPhone(rs.getString("phone"));
                user.setPassword(rs.getString("password"));
                user.setAddressLine(rs.getString("address_line"));
                user.setCity(rs.getString("city"));
                user.setState(rs.getString("state"));
                user.setPincode(rs.getString("pincode"));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return user;
    }

    // ================= UPDATE =================

    @Override
    public boolean updateUser(User user) {
        boolean status = false;

        String sql = "UPDATE users SET name=?, email=?, phone=?, address_line=?, city=?, state=?, pincode=? WHERE user_id=?";

        try {
            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setString(1, user.getName());
            ps.setString(2, user.getEmail());
            ps.setString(3, user.getPhone());
            ps.setString(4, user.getAddressLine());
            ps.setString(5, user.getCity());
            ps.setString(6, user.getState());
            ps.setString(7, user.getPincode());
            ps.setInt(8, user.getUserId());

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
    public boolean updateUserAddress(int userId, String addressLine, String city, String state, String pincode) {
        boolean status = false;

        String sql = "UPDATE users SET address_line=?, city=?, state=?, pincode=? WHERE user_id=?";

        try {
            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setString(1, addressLine);
            ps.setString(2, city);
            ps.setString(3, state);
            ps.setString(4, pincode);
            ps.setInt(5, userId);

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
    public boolean updatePassword(int userId, String newPassword) {
        boolean status = false;

        String sql = "UPDATE users SET password=? WHERE user_id=?";

        try {
            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setString(1, newPassword);
            ps.setInt(2, userId);

            int rows = ps.executeUpdate();

            if (rows > 0) {
                status = true;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return status;
    }

    // ================= VALIDATION =================

    @Override
    public boolean isEmailExists(String email) {
        boolean exists = false;

        String sql = "SELECT * FROM users WHERE email=?";

        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, email);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                exists = true;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return exists;
    }
}