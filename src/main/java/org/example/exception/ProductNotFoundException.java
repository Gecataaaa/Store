package org.example.exception;

// Custom exception for product not found
public class ProductNotFoundException extends Exception {
    private final String productName;

    public ProductNotFoundException(String productName) {
        this.productName = productName;
    }

    public String getProductName() {
        return productName;
    }

    @Override
    public String getMessage() {
        return "Product not found: " + productName;
    }
}
