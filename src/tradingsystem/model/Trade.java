package tradingsystem.model;

import java.time.LocalDateTime;
import java.util.concurrent.atomic.AtomicLong;

public class Trade {
    private static final AtomicLong COUNTER = new AtomicLong();

    private final long tradeId;
    private final long buyerOrderId;
    private final long sellerOrderId;
    private final String symbol;
    private final int quantity;
    private final double price;
    private final LocalDateTime timestamp;

    public Trade(long buyerOrderId, long sellerOrderId, String symbol, int quantity, double price) {
        this.tradeId = COUNTER.incrementAndGet();
        this.buyerOrderId = buyerOrderId;
        this.sellerOrderId = sellerOrderId;
        this.symbol = symbol;
        this.quantity = quantity;
        this.price = price;
        this.timestamp = LocalDateTime.now();
    }

    @Override
    public String toString() {
        return "Trade{" +
                "tradeId=" + tradeId +
                ", buyerOrderId=" + buyerOrderId +
                ", sellerOrderId=" + sellerOrderId +
                ", symbol='" + symbol + '\'' +
                ", quantity=" + quantity +
                ", price=" + price +
                ", timestamp=" + timestamp +
                '}';
    }
}
