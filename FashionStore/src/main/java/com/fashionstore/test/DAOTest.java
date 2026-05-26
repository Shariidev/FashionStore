package com.fashionstore.test;

import java.util.List;
import com.fashionstore.util.DBConnection;
import com.fashionstore.dao.product.ProductDAO;
import com.fashionstore.dao.product.ProductDAOImpl;
import com.fashionstore.model.Product;

public class DAOTest {

    public static void main(String[] args) {

        ProductDAO productDAO = new ProductDAOImpl();

        List<Product> products = productDAO.getAllProducts();

        System.out.println(DBConnection.getConnection());

        for (Product p : products) {
            System.out.println(
                p.getProductId() + " | " +
                p.getName() + " | " +
                p.getPrice()
            );
        }
    }
}