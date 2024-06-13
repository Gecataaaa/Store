package org.example.Test;

import org.example.stock.Stock;
import org.example.stock.Stock.Category;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class StockTest {
    @Test
    public void testIsExpired_NotExpired() {
        LocalDate futureExpiryDate = LocalDate.now().plusDays(10);
        Stock stock = new Stock("123", "Product", 10.0, Category.FOOD, futureExpiryDate);
        assertFalse(stock.isExpired());
    }

    @Test
    public void testIsExpired_Expired() {
        LocalDate pastExpiryDate = LocalDate.now().minusDays(10);
        Stock stock = new Stock("123", "Product", 10.0, Category.FOOD, pastExpiryDate);

        assertTrue(stock.isExpired());
    }

    @Test
    public void testGetDaysRemaining() {
        LocalDate futureExpiryDate = LocalDate.now().plusDays(10);
        Stock stock = new Stock("123", "Product", 10.0, Category.FOOD, futureExpiryDate);

        int daysRemaining = stock.getDaysRemaining();
        assertEquals(10, daysRemaining);
    }
}
