package com.cse687.zirui.bookstore.cart.messaging;

public enum CartQueueName {
    ADD_ITEM("cart.queue.addItem"),
    REMOVE_ITEM("cart.queue.removeItem"),
    PLACE_ORDER("cart.queue.placeOrder"),

    ITEM_ADDED("cart.queue.itemAdded"),
    ITEM_REMOVED("cart.queue.itemRemoved"),
    ORDER_PLACED("cart.queue.orderPlaced"); // consumer in order service

    private final String name;

    CartQueueName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
