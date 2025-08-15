package tradingsystem.factory;

import tradingsystem.model.Order;
import tradingsystem.model.OrderType;

public class OrderFactory {

    public static Order createLimitOrder(String userId, OrderType type, String symbol, int qty, double price) {
        return new Order(userId, type, symbol, qty, price);
    }
}
