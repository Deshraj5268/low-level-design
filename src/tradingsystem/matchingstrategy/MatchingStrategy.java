package tradingsystem.matchingstrategy;

import tradingsystem.core.OrderBook;

public interface MatchingStrategy {

    void match(OrderBook orderBook);
}
