package com.cse687.zirui.bookstore.auth.domain.command;

public record LogOut(
        String email
) implements Command {}
