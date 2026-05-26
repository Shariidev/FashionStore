package com.fashionstore.model;

public class ProductSize {

    private int sizeId;
    private int productId;        // ✅ ADD THIS
    private String size;
    private int stockQuantity;    // ✅ ADD THIS

    // Getters and Setters

    public int getSizeId() {
        return sizeId;
    }

    public void setSizeId(int sizeId) {
        this.sizeId = sizeId;
    }

    public int getProductId() {   // ✅ ADD
        return productId;
    }

    public void setProductId(int productId) {   // ✅ ADD
        this.productId = productId;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public int getStockQuantity() {   // ✅ ADD
        return stockQuantity;
    }

    public void setStockQuantity(int stockQuantity) {   // ✅ ADD
        this.stockQuantity = stockQuantity;
    }
}