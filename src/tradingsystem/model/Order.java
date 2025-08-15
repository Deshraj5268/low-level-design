package tradingsystem.model;

import java.time.LocalDateTime;
import java.util.concurrent.atomic.AtomicLong;

public class Order {
    private static final AtomicLong COUNTER = new AtomicLong();

    private final long orderId;
    private final String userId;
    private final OrderType type;
    private final String symbol;// stock name - RELIANCE
    private int quantity;
    private double price;
    private final LocalDateTime timestamp;
    private OrderStatus status;

    public Order(String userId, OrderType type, String symbol, int quantity, double price) {
        this.orderId = COUNTER.incrementAndGet();
        this.userId = userId;
        this.type = type;
        this.symbol = symbol;
        this.quantity = quantity;
        this.price = price;
        this.timestamp = LocalDateTime.now();
        this.status = OrderStatus.ACCEPTED;
    }

    public long getOrderId() { return orderId; }
    public String getUserId() { return userId; }
    public OrderType getType() { return type; }
    public String getSymbol() { return symbol; }
    public int getQuantity() { return quantity; }
    public double getPrice() { return price; }
    public LocalDateTime getTimestamp() { return timestamp; }
    public OrderStatus getStatus() { return status; }

    public void setQuantity(int quantity) { this.quantity = quantity; }
    public void setPrice(double price) { this.price = price; }
    public void setStatus(OrderStatus status) { this.status = status; }

    @Override
    public String toString() {
        return "Order{" +
                "orderId=" + orderId +
                ", type=" + type +
                ", symbol='" + symbol + '\'' +
                ", quantity=" + quantity +
                ", price=" + price +
                ", status=" + status +
                '}';
    }
}
