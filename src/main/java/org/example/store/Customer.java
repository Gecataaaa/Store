package org.example.store;
import java.util.ArrayList;
import java.util.List;

import org.example.stock.Stock;


public class Customer {
    private final String name;
    private double money;
    private final List<Stock> items;

    public Customer(String name, double money) {
        this.name = name;
        this.money = money;
        this.items = new ArrayList<>();
    }


    public String getName() {
        return name;
    }

    public double getBalance() {
        return money;
    }

    public void addMoney(double amount) {
        money += amount;
    }

    public boolean hasEnoughMoney(double amount) {
        return money >= amount;
    }

    public void deductBalance(double amount) {
        money -= amount;
    }

    public List<Stock> getItems() {
        return items;
    }

    public void addItem(Stock item) {
        items.add(item);
    }

    public void removeItem(Stock item) {
        items.remove(item);
    }
}
