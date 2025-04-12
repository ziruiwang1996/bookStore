package com.cse687.zirui.bookstore.order.messaging;

public enum RoutingKey {
    BUY_BOOK("order.cmd.buy"),
    BOOK_BOUGHT("order.evt.bought"),
    SELL_BOOK("order.cmd.sell"),
    BOOK_SOLD("order.evt.sold"),
    RESERVE_BOOK("order.cmd.reserve"),
    BOOK_RESERVED("order.evt.reserved"),
    CANCEL_BOOK_RESERVE("order.cmd.cancel-reserve"),
    BOOK_RESERVE_CANCELLED("order.evt.reserve-cancelled"),
    STOCK_BOOK("order.cmd.stock");

    private final String key;

    RoutingKey(String key) {
        this.key = key;
    }

    public String getKey() {
        return key;
    }
}
