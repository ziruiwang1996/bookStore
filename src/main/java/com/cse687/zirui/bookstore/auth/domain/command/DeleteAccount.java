package com.cse687.zirui.bookstore.auth.domain.command;

public record DeleteAccount(
        Long userId
) implements Command {}
