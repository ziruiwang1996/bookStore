package com.cse687.zirui.bookstore.authservice.command;

public record RegisterAccount(String email, String Password) implements Command {
}
