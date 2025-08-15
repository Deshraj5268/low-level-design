package tradingsystem;

import tradingsystem.model.Order;
import tradingsystem.model.OrderType;
import tradingsystem.model.User;
import tradingsystem.service.OrderService;
import tradingsystem.store.InMemoryStore;


/*
* Asked in phonepay
* */
public class TradingDriver {

    public static void main(String[] args) {
        InMemoryStore store = InMemoryStore.getInstance();
        OrderService service = new OrderService();
        orderExecution(store, service);
    }

    private static void orderExecution(InMemoryStore store, OrderService service) {
        createUsers(store);
        // Place buy Orders
        Order buyOrder = service.placeOrder(store.getUsers().get("U1").getUserId(), OrderType.BUY, "RELIANCE", 100, 2500.00);
        System.out.println("Original Buy Order: " + buyOrder);

        /*// modify buy order
        buyOrder = service.modifyOrder(buyOrder.getOrderId(), 150, 2550.0);
        System.out.println("Modified Buy Order: " + buyOrder);*/

        // place sell order
        Order sellOrder = service.placeOrder(store.getUsers().get("U2").getUserId(), OrderType.SELL, "RELIANCE", 100, 2499.00);
        System.out.println("Placed Sell Order: " + sellOrder);

        System.out.println("Buy status (pre-execute): " + service.queryOrderStatus(buyOrder.getOrderId()));
        System.out.println("Sell status (pre-execute): " + service.queryOrderStatus(sellOrder.getOrderId()));

        // execute order
        service.executeOrders("RELIANCE");

        System.out.println("Buy status (post-execute): " + service.queryOrderStatus(buyOrder.getOrderId()));
        System.out.println("Sell status (post-execute): " + service.queryOrderStatus(sellOrder.getOrderId()));


        // Show Executed Trades
        System.out.println("trade entries ..");
        store.getTrades().forEach(System.out::println);
    }

    private static void createUsers(InMemoryStore store) {
        store.addUser(new User("U1", "Ram", "9826543930", "Ram@example.com"));
        store.addUser(new User("U2", "Shyam", "9826543930", "Shyam@example.com"));
    }
}

