package tradingsystem.core;

import tradingsystem.model.Order;

import java.util.Comparator;
import java.util.PriorityQueue;

public class OrderBook {
    private final PriorityQueue<Order> buyOrders = new PriorityQueue<>(
            Comparator.comparingDouble(Order::getPrice).reversed().thenComparing(Order::getTimestamp)
    ); // max heap

    private final PriorityQueue<Order> sellOrders = new PriorityQueue<>(
            Comparator.comparingDouble(Order::getPrice).thenComparing(Order::getTimestamp)
    ); // min heap

    public PriorityQueue<Order> getBuyOrders() { return buyOrders; }
    public PriorityQueue<Order> getSellOrders() { return sellOrders; }
}
