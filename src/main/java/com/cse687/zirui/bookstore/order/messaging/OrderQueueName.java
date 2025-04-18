package com.cse687.zirui.bookstore.order.messaging;

public enum OrderQueueName {
    BUY_BOOK("order.queue.buy"),
    SELL_BOOK("order.queue.sell"),
    RESERVE_BOOK("order.queue.reserve"),
    CANCEL_BOOK_RESERVE("order.queue.cancelReserve"),
    STOCK_BOOK("order.queue.stock"),

    BOOK_BOUGHT("order.queue.bought"),
    BOOK_SOLD("order.queue.sold"),
    BOOK_RESERVED("order.queue.reserved"),
    BOOK_RESERVE_CANCELLED("order.queue.reserveCancelled"),

    ADD_CREDIT("order.queue.addCredit"), // consumer in auth service
    PROCESS_PAYMENT("order.queue.processPayment"), // consumer in payment service
    ADD_ITEM("cart.queue.addItem"), // same queue from cart service
    REMOVE_ITEM("cart.queue.removeItem"); // same queue from cart service

    private final String name;

    OrderQueueName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}