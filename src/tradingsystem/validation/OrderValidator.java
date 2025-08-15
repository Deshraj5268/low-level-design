package tradingsystem.validation;

import tradingsystem.model.*;
import tradingsystem.store.InMemoryStore;

public class OrderValidator {
    private static final InMemoryStore store = InMemoryStore.getInstance();

    public static void validateNewOrder(String userId, OrderType type, String symbol, int quantity, double price) {
        if (userId == null || !store.getUsers().containsKey(userId)) {
            throw new IllegalArgumentException("Invalid user ID");
        }
        if (symbol == null || symbol.trim().isEmpty()) {
            throw new IllegalArgumentException("Symbol must be provided.");
        }
        if (quantity <= 0) {
            throw new IllegalArgumentException("Quantity must be greater than zero.");
        }
        if (type != OrderType.BUY && type != OrderType.SELL) {
            throw new IllegalArgumentException("Invalid order type.");
        }
        if (price <= 0) {
            throw new IllegalArgumentException("Price must be greater than zero.");
        }
    }

    public static void validateExistingOrder(Order order, String action) {
        if (order == null) {
            throw new IllegalArgumentException("Order not found.");
        }
        if (order.getStatus() != OrderStatus.ACCEPTED) {
            throw new IllegalStateException("Order cannot be " + action + ". Current status: " + order.getStatus());
        }
    }

    public static void validateQuantityPrice(int quantity, double price) {
        if (quantity <= 0) {
            throw new IllegalArgumentException("Quantity must be greater than zero.");
        }
        if (price <= 0) {
            throw new IllegalArgumentException("Price must be greater than zero.");
        }
    }
}
