package com.cse687.zirui.bookstore.auth.domain.event;

public record LoggedIn(
        Long userId
) implements Event {}
