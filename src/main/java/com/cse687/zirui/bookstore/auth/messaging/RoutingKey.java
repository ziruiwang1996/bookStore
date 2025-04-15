package com.cse687.zirui.bookstore.auth.messaging;

public enum RoutingKey {
    REGISTER_ACCOUNT("auth.cmd.register"),
    DELETE_ACCOUNT("auth.cmd.delete"),
    LOGGED_IN("auth.evt.loggedIn"),
    LOGGED_OUT("auth.evt.loggedOut"),
    ACCOUNT_REGISTERED("auth.evt.registered"),
    ACCOUNT_DELETED("auth.evt.deleted");

    private final String key;

    RoutingKey(String key) {
        this.key = key;
    }

    public String getKey() {
        return key;
    }
}