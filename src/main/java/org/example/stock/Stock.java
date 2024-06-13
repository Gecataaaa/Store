package org.example.stock;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class Stock {
    private final String id;
    private final String name;
    private final double unitPrice;
    private final Category category;
    private final LocalDate expirationDate;
    private int quantity;
    private final double deliveryPrice;
    private int quantitySold; // Track the quantity of stock sold

    public Stock(String id, String name, double unitPrice, Category category, LocalDate expirationDate) {
        this.id = id;
        this.name = name;
        this.unitPrice = unitPrice;
        this.category = category;
        this.expirationDate = expirationDate;
        this.quantity = 0;
        this.deliveryPrice = 0.0;
        this.quantitySold = 0;
    }



    public Stock( String id, String name, int unitPrice, double v, String s, LocalDate of) {
        this.id = id;
        this.name = name;
        this.unitPrice = unitPrice;
        this.category = Category.NOTFOOD;
        this.expirationDate = LocalDate.now();
        this.quantity = 0;
        this.deliveryPrice = 0.0;
        this.quantitySold = 0;



     }

    // Getter methods for accessing the properties of the stock

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getQuantity() {
        return quantity;
    }

    public double getDeliveryPrice() {
        return deliveryPrice;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public Category getCategory() {
        return category;
    }

    public LocalDate getExpirationDate() {
        return expirationDate;
    }

    public int getQuantitySold() {
        return quantitySold;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public boolean isExpired() {
        LocalDate currentDate = LocalDate.now();
        return expirationDate.isBefore(currentDate);
    }

    public int getDaysRemaining() {
        return (int) ChronoUnit.DAYS.between(LocalDate.now(), expirationDate);
    }

    // Method to calculate the selling price of the stock based on markup and expiration date
    public double calculateSellingPrice(double markup, int daysBeforeExpiration) {
        double sellingPrice = unitPrice + (unitPrice * markup / 100);

        // Check if the stock has expired or is close to expiration
        if (expirationDate.isBefore(LocalDate.now()) || (daysBeforeExpiration >= 0 && daysBeforeExpiration < 10)) {
            double discount = 10.0; // Example discount percentage for stocks close to expiration
            sellingPrice -= (sellingPrice * discount / 100);
        }

        return sellingPrice;
    }

    // Method to sell a specified quantity of stock
    public void sellStock(int quantity) {
        if (quantity <= this.quantity) {
            this.quantity -= quantity;
            this.quantitySold += quantity;
        }
    }

    // Define the Category enum within the Stock class
    public enum Category {
        FOOD,
        NOTFOOD
    }
}
