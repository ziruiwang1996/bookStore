package com.cse687.zirui.bookstore.services.customer.command;

public record RegisterAccount(String email, String Password) implements Command {
}
