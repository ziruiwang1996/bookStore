package com.cse687.zirui.bookstore.shared;

public enum RoutingKey {
    REGISTER_ACCOUNT("auth.user.register"),
    DELETE_ACCOUNT("auth.user.delete"),
    LOGGED_IN("auth.user.loggedIn"),
    LOGGED_OUT("auth.user.loggedOut"),
    ACCOUNT_REGISTERED("auth.user.registered"),
    ACCOUNT_DELETED("auth.user.deleted"),
    CREATE_CART("auth.user.create_cart"),
    DELETE_CART("auth.user.delete_cart"),

    ADD_ITEM("cart.item.add"),
    ITEM_ADDED("cart.item.added"),
    REMOVE_ITEM("cart.item.remove"),
    ITEM_REMOVED("cart.item.removed"),
    PLACE_ORDER("cart.user.place_order"),
    ORDER_PLACED("cart.user.order_placed"),

    BUY_BOOK("order.book.buy"),
    BOOK_BOUGHT("order.book.bought"),
    SELL_BOOK("order.book.sell"),
    BOOK_SOLD("order.book.sold"),
    RESERVE_BOOK("order.book.reserve"),
    BOOK_RESERVED("order.book.reserved"),
    CANCEL_BOOK_RESERVE("order.book.cancel_reserve"),
    BOOK_RESERVE_CANCELLED("order.book.reserve_cancelled"),
    STOCK_BOOK("order.book.stock"),
    PROCESS_PAYMENT("order.payment.process"),

    PAYMENT_SUCCEEDED("payment.payment.succeeded"),
    PAYMENT_FAILED("payment.payment.failed");

    private final String key;

    RoutingKey(String key) {
        this.key = key;
    }

    public String getKey() {
        return key;
    }
}