package com.cse687.zirui.bookstore.auth.domain.event;

public record AccountDeleted(
        Long userId
) implements Event {}
