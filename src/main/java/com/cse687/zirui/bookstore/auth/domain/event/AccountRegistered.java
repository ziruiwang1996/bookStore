package com.cse687.zirui.bookstore.auth.domain.event;

public record AccountRegistered(
        String email,
        String Password
) implements Event {}
