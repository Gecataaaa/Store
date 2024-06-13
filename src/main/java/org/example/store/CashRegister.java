package org.example.store;

import java.util.ArrayList;
import java.util.List;

import org.example.exception.InsufficientStockException;
import org.example.stock.Stock;
import org.example.store.CashReceipt;
import org.example.staff.Cashier;

public class CashRegister {
    private int registerNumber;
    private Cashier cashier;
    private List<Stock> markedStocks;

    private List<CashReceipt> cashReceipts;

    public List<CashReceipt> getCashReceipts() {
        if (cashReceipts == null) {
            cashReceipts = new ArrayList<>();
        }
        return cashReceipts;
    }

    public CashRegister(int registerNumber, Cashier cashier) {
        this.registerNumber = registerNumber;
        this.cashier = cashier;
        this.markedStocks = new ArrayList<>();
    }

    public void markStock(Stock stock) throws InsufficientStockException {
        if (stock.getQuantity() > 0) {
            stock.setQuantity(stock.getQuantity() - 1);
        } else {
            throw new InsufficientStockException(stock);
        }
    }

    public void sellStocks(Customer customer) {
        double totalCost = calculateTotalCost();

        if (customer.getBalance() >= totalCost) {
            // If the customer has enough money, sell the stocks and issue a receipt
            customer.deductBalance(totalCost);
            CashReceipt receipt = new CashReceipt(cashier, markedStocks);
            receipt.saveToFile(); // Save the receipt to a file
            cashReceipts.add(receipt); // Add the receipt to the cash register
        } else {
            System.out.println("Insufficient funds. Cannot complete the purchase.");
        }

        // Clear the marked stocks after selling
        markedStocks.clear();
    }

    private double calculateTotalCost() {
        double totalCost = 0.0;

        for (Stock stock : markedStocks) {
            totalCost += stock.getUnitPrice();
        }

        return totalCost;
    }
}
