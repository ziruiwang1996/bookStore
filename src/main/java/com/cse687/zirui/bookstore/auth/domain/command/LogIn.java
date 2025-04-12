package com.cse687.zirui.bookstore.auth.domain.command;

public record LogIn(
        String email,
        String password
) implements Command {}
