package org.example.exception;

import org.example.stock.Stock;

// Custom exception for insufficient stock
public class InsufficientStockException extends Exception {
    private final Stock stock;


    public InsufficientStockException(Stock stock) {
        this.stock = stock;

    }

    public Stock getStock() {
        return stock;
    }



    @Override
    public String getMessage() {
        return "Insufficient stock for item: " + stock.getName();
    }
}
