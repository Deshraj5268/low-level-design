package tradingsystem.core;

import tradingsystem.matchingstrategy.MatchingStrategy;
import tradingsystem.store.InMemoryStore;

public class TradingEngine {
    private final InMemoryStore store = InMemoryStore.getInstance();
    private final MatchingStrategy strategy;

    public TradingEngine(MatchingStrategy strategy) {
        this.strategy = strategy;
    }

    public synchronized void match(String symbol) {
        strategy.match(store.getOrderBook(symbol));
    }
}
