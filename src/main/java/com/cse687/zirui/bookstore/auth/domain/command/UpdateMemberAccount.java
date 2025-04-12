package com.cse687.zirui.bookstore.auth.domain.command;

public record UpdateMemberAccount(
        String email,
        String password,
        float creditChange
) implements Command {}
