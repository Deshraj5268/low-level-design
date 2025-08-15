package tradingsystem.matchingstrategy;

import tradingsystem.core.OrderBook;
import tradingsystem.model.Order;
import tradingsystem.model.OrderStatus;
import tradingsystem.model.Trade;
import tradingsystem.store.InMemoryStore;

public class PriceTimeStrategy implements MatchingStrategy {
    private final InMemoryStore store = InMemoryStore.getInstance();


    //
    @Override
    public void match(OrderBook book) {
        while (!book.getBuyOrders().isEmpty() && !book.getSellOrders().isEmpty()) {
            Order buy = book.getBuyOrders().peek();
            Order sell = book.getSellOrders().peek();

            if (buy.getPrice() >= sell.getPrice()) { // min== 100 , 150 -- > min ( m%n) --> 0(1) --> log(Min(m%n)) , 8 , 12 --> 3, 11 --> logn(
                int qty = Math.min(buy.getQuantity(), sell.getQuantity());
                double price = sell.getPrice();

                store.addTrade(new Trade(buy.getOrderId(), sell.getOrderId(), buy.getSymbol(), qty, price));

                buy.setQuantity(buy.getQuantity() - qty);
                sell.setQuantity(sell.getQuantity() - qty);

                if (buy.getQuantity() == 0) {
                    buy.setStatus(OrderStatus.EXECUTED);
                    book.getBuyOrders().poll();
                }

                if (sell.getQuantity() == 0) {
                    sell.setStatus(OrderStatus.EXECUTED);
                    book.getSellOrders().poll(); // O(logN)
                }
            } else {
                break;
            }
        }
    }
}

