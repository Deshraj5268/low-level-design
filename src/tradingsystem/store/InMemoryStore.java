package tradingsystem.store;

import tradingsystem.core.OrderBook;
import tradingsystem.model.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class InMemoryStore {
    private static final InMemoryStore INSTANCE = new InMemoryStore();

    private final Map<String, User> users = new ConcurrentHashMap<>();
    private final Map<Long, Order> orders = new ConcurrentHashMap<>();
    private final Map<String, OrderBook> books = new ConcurrentHashMap<>();
    private final List<Trade> trades = Collections.synchronizedList(new ArrayList<>());


    // private constructor for single instance
    private InMemoryStore(){
    }
    public static InMemoryStore getInstance() {
        return INSTANCE;  // singleton design pattern
    }

    public void addUser(User user) {
        users.put(user.getUserId(), user);
    }

    public Map<String, User> getUsers() { return users; }
    public Map<Long, Order> getOrders() { return orders; }
    public List<Trade> getTrades() { return trades; }

    public void addOrder(Order order) {
        orders.put(order.getOrderId(), order);
    }

    public void addTrade(Trade trade) {
        trades.add(trade);
    }

    public OrderBook getOrderBook(String symbol) {
        return books.computeIfAbsent(symbol, k -> new OrderBook());
    }

    public void removeFromOrderBook(Order order) {
        OrderBook book = books.get(order.getSymbol());
        if (order.getType() == OrderType.BUY) {
            book.getBuyOrders().remove(order);
        } else {
            book.getSellOrders().remove(order);
        }
    }
}
