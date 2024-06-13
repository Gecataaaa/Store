package org.example.store;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

import org.example.stock.Stock;
import org.example.staff.Cashier;

public class CashReceipt {
    private static int nextReceiptNumber = 1;

    private int receiptNumber;
    private Cashier cashier;
    private LocalDateTime dateTime;
    private List<Stock> items;
    private double totalCost;

    public CashReceipt(Cashier cashier, List<Stock> items) {
        this.receiptNumber = getNextReceiptNumber();
        this.cashier = cashier;
        this.dateTime = LocalDateTime.now();
        this.items = items;
        this.totalCost = calculateTotalCost();
    }

    private int getNextReceiptNumber() {
        return nextReceiptNumber++;
    }

    public double calculateTotalCost() {
        double totalCost = 0.0;
        for (Stock item : items) {
            totalCost += item.getUnitPrice();
        }
        return totalCost;
    }

    public void saveToFile() {
        String filename = "receipt_" + receiptNumber + ".txt";

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            writer.write(toString());
            System.out.println("Cash receipt saved to file: " + filename);
        } catch (IOException e) {
            System.out.println("Error saving cash receipt to file: " + e.getMessage());
        }
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("Receipt Number: ").append(receiptNumber).append("\n");
        builder.append("Cashier: ").append(cashier.getName()).append("\n");
        builder.append("Date and Time: ").append(dateTime).append("\n");
        builder.append("Items:\n");
        for (Stock item : items) {
            builder.append("- ").append(item.getName()).append(": $").append(item.getUnitPrice()).append("\n");
        }
        builder.append("Total Cost: $").append(totalCost).append("\n");
        return builder.toString();
    }
}

