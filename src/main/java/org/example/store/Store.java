package org.example.store;

import java.time.LocalDate;
import java.util.ArrayList;

import org.example.exception.InsufficientStockException;
import org.example.stock.Stock;
import org.example.staff.Cashier;





public class Store {
    private final double foodMarkupPercentage;
    private final double nonFoodMarkupPercentage;
    private final int daysBeforeExpiration;
    private final double expirationDiscountPercentage;
    private final ArrayList<Stock> stocks;
    private final ArrayList<Cashier> cashiers;
    private final CashRegister cashRegister;



    public Store(double foodMarkupPercentage, double nonFoodMarkupPercentage, int daysBeforeExpiration,
                 double expirationDiscountPercentage) {
        this.foodMarkupPercentage = foodMarkupPercentage;
        this.nonFoodMarkupPercentage = nonFoodMarkupPercentage;
        this.daysBeforeExpiration = daysBeforeExpiration;
        this.expirationDiscountPercentage = expirationDiscountPercentage;
        this.stocks = new ArrayList<Stock>();
        this.cashiers = new ArrayList<Cashier>();
        this.cashRegister = new CashRegister(1, null); // Initialize with a dummy cash register, should be set later

    }

    public double calculateSellingPrice(Stock stock) {
        double markupPercentage = stock.getCategory().equals("Food") ? foodMarkupPercentage : nonFoodMarkupPercentage;

        if (stock.getExpirationDate().isBefore(LocalDate.now().plusDays(daysBeforeExpiration))) {
            // If the stock is close to expiration, apply a discount
            double discount = stock.getUnitPrice() * expirationDiscountPercentage / 100.0;
            return stock.getUnitPrice() + (stock.getUnitPrice() * markupPercentage / 100.0) - discount;
        } else {
            // Otherwise, calculate the selling price with the markup
            return stock.getUnitPrice() + (stock.getUnitPrice() * markupPercentage / 100.0);
        }
    }
    public void addStock(Stock stock) {
        stocks.add(stock);
    }

    public void addCashier(Cashier cashier) {
        cashiers.add(cashier);
    } public double calculateCashierExpenses() {
        double totalExpenses = 0.0;
        for (Cashier cashier : cashiers) {
            totalExpenses += cashier.getSalary();
        }
        return totalExpenses;
    }

    public double calculateStockDeliveryExpenses() {
        double totalExpenses = 0.0;
        for (Stock stock : stocks) {
            totalExpenses += stock.getDeliveryPrice();
        }
        return totalExpenses;
    }
    public void processSale(Cashier selectedCashier, ArrayList<Stock> itemsToPurchase) {
        if (selectedCashier != null && selectedCashier.getCustomer() != null) {
            for (Stock item : itemsToPurchase) {
                try {
                    cashRegister.markStock(item);
                } catch (InsufficientStockException e) {
                    System.out.println("Insufficient stock for item: " + e.getStock().getName());
                }
            }
            cashRegister.sellStocks(selectedCashier.getCustomer());
        } else {
            System.out.println("No cashier selected or no customer associated with the cashier.");
        }
    }


    public double calculateRevenue() {
        double totalRevenue = 0.0;
        for (CashReceipt receipt :cashRegister.getCashReceipts()) {
            totalRevenue += receipt.calculateTotalCost();
        }
        return totalRevenue;
    }

    public double calculateProfit() {
        double totalProfit = calculateRevenue() - calculateCashierExpenses() - calculateStockDeliveryExpenses();
        return totalProfit;
    }

    public void addCustomer(Customer customer1) {
    }

    public void processCashReceipt(CashReceipt cashReceipt) {
    }
}




