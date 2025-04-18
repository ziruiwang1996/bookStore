package com.cse687.zirui.bookstore.order.messaging;

public enum OrderRoutingKey {
    BUY_BOOK("order.cmd.buy"),
    SELL_BOOK("order.cmd.sell"),
    RESERVE_BOOK("order.cmd.reserve"),
    CANCEL_BOOK_RESERVE("order.cmd.cancelReserve"),
    STOCK_BOOK("order.cmd.stock"),

    BOOK_BOUGHT("order.evt.bought"),
    BOOK_SOLD("order.evt.sold"),
    BOOK_RESERVED("order.evt.reserved"),
    BOOK_RESERVE_CANCELLED("order.evt.reserveCancelled"),

    ADD_CREDIT("order.cmd.addCredit"),
    PROCESS_PAYMENT("order.cmd.processPayment"),
    ADD_ITEM("order.cmd.addItem"),
    REMOVE_ITEM("order.cmd.removeItem");

    private final String key;

    OrderRoutingKey(String key) {
        this.key = key;
    }

    public String getKey() {
        return key;
    }
}
