package com.cse687.zirui.bookstore.services.customer.model;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("MEMBER")
public class Member extends Customer {
    private String password;
    private float balance;

    public Member() {}

    public Member(String email, String password, float balance) {
        super(email);
        this.password = PasswordUtil.hashPassword(password);
        this.balance = balance;
    }

    public String getPassword() {
        return password;
    }

    public float getBalance() { return balance; }

    public void increaseFund(float amount) {
        balance += amount;
    }

    public void decreaseFund(float amount) {
        if (amount > balance) {
            throw new RuntimeException("Insufficient fund");
        }
        balance -= amount;
    }

    @Override
    public String toString() {
        return String.format("(ID: %d, Email: %s, Balance: %.2f)", id, email, balance);
    }
}