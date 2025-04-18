package com.cse687.zirui.bookstore.auth.messaging;

public enum AuthRoutingKey {
    REGISTER_ACCOUNT("auth.cmd.register"),
    DELETE_ACCOUNT("auth.cmd.delete"),
    CREATE_CART("auth.cmd.create_cart"),
    DELETE_CART("auth.cmd.delete_cart"),

    LOGGED_IN("auth.evt.loggedIn"),
    LOGGED_OUT("auth.evt.loggedOut"),
    ACCOUNT_REGISTERED("auth.evt.registered"),
    ACCOUNT_DELETED("auth.evt.deleted");

    private final String key;

    AuthRoutingKey(String key) {
        this.key = key;
    }

    public String getKey() {
        return key;
    }
}
