package com.cse687.zirui.bookstore.auth.domain.event;

public record LoggedOut(
        Long userId
) implements Event{}
