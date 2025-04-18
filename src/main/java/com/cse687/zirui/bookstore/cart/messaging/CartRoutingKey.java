package com.cse687.zirui.bookstore.cart.messaging;

public enum CartRoutingKey {
    ADD_ITEM("cart.cmd.add"),
    REMOVE_ITEM("cart.cmd.remove"),
    PLACE_ORDER("cart.cmd.placeOrder"),

    ITEM_ADDED("cart.evt.added"),
    ITEM_REMOVED("cart.evt.removed"),
    ORDER_PLACED("cart.evt.orderPlaced");

    private final String key;

    CartRoutingKey(String key) {
        this.key = key;
    }

    public String getKey() {
        return key;
    }
}
