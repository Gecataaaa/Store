package org.example;

import java.time.LocalDate;
import java.util.ArrayList;

import org.example.store.Customer;
import org.example.stock.Stock;
import org.example.staff.Cashier;
import org.example.store.Store;

public class Main {
    public static void main(String[] args) {
        // Create a store
        Store store = new Store(2, 3, 4, 5);

        // Add cashiers
        Cashier cashier1 = new Cashier("John Doe", 1, 1500.0);
        Cashier cashier2 = new Cashier("Jane Smith", 2, 1700.0);
        store.addCashier(cashier1);
        store.addCashier(cashier2);

        // Add stocks
        Stock stock1 = new Stock("Apple", "1", 1.0, Stock.Category.FOOD, LocalDate.now().plusDays(10));
        Stock stock2 = new Stock("Light bulb", "2", 2.0, Stock.Category.NOTFOOD, LocalDate.now().plusDays(5));
        store.addStock(stock1);
        store.addStock(stock2);


        // Create a customer
        Customer customer = new Customer("John Smith", 100.0);

        // Customer buys stocks
        ArrayList<Stock> itemsToPurchase = new ArrayList<>();
        itemsToPurchase.add(stock1);
        itemsToPurchase.add(stock2);
        customer.getItems().addAll(itemsToPurchase);

        // Process sale
        Cashier selectedCashier = cashier1;
        store.processSale(selectedCashier, itemsToPurchase);

        // Calculate and display store statistics
        System.out.println("Total cashier expenses: " + store.calculateCashierExpenses());
        System.out.println("Total stock delivery expenses: " + store.calculateStockDeliveryExpenses());
        System.out.println("Total revenue: " + store.calculateRevenue());
        System.out.println("Total profit: " + store.calculateProfit());
    }
}
