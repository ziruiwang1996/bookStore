package com.cse687.zirui.bookstore.auth.domain.command;

public record RegisterAccount(
        String email,
        String Password,
        String passwordConfirmation
) implements Command {}
