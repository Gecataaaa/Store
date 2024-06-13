package org.example.staff;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

import org.example.store.Customer;
import org.example.exception.InsufficientStockException;
import org.example.stock.Stock;

public class Cashier {
    private final String name;
    private final int id;
    private final double monthlySalary;
    private int totalReceipts;
    private double totalRevenue;

    public Cashier(String name, int id, double monthlySalary) {
        this.name = name;
        this.id = id;
        this.monthlySalary = monthlySalary;
        this.totalReceipts = 0;
        this.totalRevenue = 0.0;
    }

    public void sellItems(Customer customer, List<Stock> items) {
        try {
            double totalCost = calculateTotalCost(items);
            if (customer.hasEnoughMoney(totalCost)) {
                customer.deductBalance(totalCost);
                generateReceipt(customer, items, totalCost);
                updateRevenue(totalCost);
                totalReceipts++;
            } else {
                System.out.println("Customer does not have enough money to purchase the items.");
            }
        } catch (InsufficientStockException e) {
            System.out.println("Insufficient stock: " + e.getMessage());
        }
    }

    public String getName() {
        return name;
    }

    public double getSalary() {
        return monthlySalary;
    }

    public Customer getCustomer() {
        // Implement logic to retrieve the customer associated with the cashier
        return null;
    }



    private double calculateTotalCost(List<Stock> items) throws InsufficientStockException {
        double totalCost = 0.0;
        for (Stock item : items) {
            if (item.getQuantity() <= 0) {
                throw new InsufficientStockException(item);
            }
            totalCost += item.getDeliveryPrice();
        }
        return totalCost;
    }

    private void generateReceipt(Customer customer, List<Stock> items, double totalCost) {
        int receiptNumber = totalReceipts + 1;
        String receiptFileName = "receipt" + receiptNumber + ".txt";
        try {
            FileWriter fileWriter = new FileWriter(receiptFileName);
            fileWriter.write("Receipt Number: " + receiptNumber + "\n");
            fileWriter.write("Cashier: " + name + "\n");
            fileWriter.write("Date and Time: " + LocalDateTime.now() + "\n");
            fileWriter.write("Customer: " + customer.getName() + "\n\n");
            fileWriter.write("Items Purchased:\n");
            for (Stock item : items) {
                fileWriter.write("- " + item.getName() + " (Quantity: " + item.getQuantity() + ", Price: " + item.getDeliveryPrice() + ")\n");
            }
            fileWriter.write("\nTotal Cost: " + totalCost + "\n");
            fileWriter.close();
        } catch (IOException e) {
            System.out.println("Failed to generate receipt: " + e.getMessage());
        }
    }

    private void updateRevenue(double amount) {
        totalRevenue += amount;
    }
}
