package com.cse687.zirui.bookstore.auth.messaging;

public enum AuthQueueName {
    REGISTER_ACCOUNT("auth.queue.registerAccount"),
    DELETE_ACCOUNT("auth.queue.deleteAccount"),
    CREATE_CART("auth.queue.createCart"), // consumer in cart service
    DELETE_CART("auth.queue.deleteCart"), // consumer in cart service

    LOGGED_IN("auth.queue.loggedIn"),
    LOGGED_OUT("auth.queue.loggedOut"),
    ACCOUNT_REGISTERED("auth.queue.accountRegistered"),
    ACCOUNT_DELETED("auth.queue.accountDeleted");

    private final String name;

    AuthQueueName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
