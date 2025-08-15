package tradingsystem.service;

import tradingsystem.core.OrderBook;
import tradingsystem.core.TradingEngine;
import tradingsystem.factory.OrderFactory;
import tradingsystem.matchingstrategy.MatchingStrategy;
import tradingsystem.matchingstrategy.PriceTimeStrategy;
import tradingsystem.model.Order;
import tradingsystem.model.OrderStatus;
import tradingsystem.model.OrderType;
import tradingsystem.store.InMemoryStore;
import tradingsystem.validation.OrderValidator;

public class OrderService {
    private final InMemoryStore store = InMemoryStore.getInstance();

    MatchingStrategy priceTimeStrategy = new PriceTimeStrategy();
    private final TradingEngine engine = new TradingEngine(priceTimeStrategy);

    public Order placeOrder(String userId, OrderType type, String symbol, int qty, double price) {
        OrderValidator.validateNewOrder(userId, type, symbol, qty, price);

        Order order = OrderFactory.createLimitOrder(userId, type, symbol, qty,price); // factory pattern
        store.addOrder(order); // hashSet
        OrderBook book = store.getOrderBook(symbol);

        if (type == OrderType.BUY) {
            book.getBuyOrders().add(order);
        } else {
            book.getSellOrders().add(order);
        }
        return order;
    }

    // order execution
    public void executeOrders(String symbol) {
        engine.match(symbol);
    }

    public void cancelOrder(long orderId) {
        Order order = store.getOrders().get(orderId);
        OrderValidator.validateExistingOrder(order, "canceled");
        order.setStatus(OrderStatus.CANCELED);
        store.removeFromOrderBook(order);
    }

    public Order modifyOrder(long orderId, int newQty, double newPrice) {
        Order order = store.getOrders().get(orderId);

        OrderValidator.validateExistingOrder(order, "modified");
        OrderValidator.validateQuantityPrice(newQty, newPrice);

        cancelOrder(orderId);
        return placeOrder(order.getUserId(), order.getType(), order.getSymbol(), newQty, newPrice);
    }


    public OrderStatus queryOrderStatus(long orderId) {
        Order order = store.getOrders().get(orderId);
        if (order == null) throw new IllegalArgumentException("Order not found.");
        return order.getStatus();
    }
}
