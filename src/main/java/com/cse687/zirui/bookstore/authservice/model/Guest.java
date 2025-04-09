package com.cse687.zirui.bookstore.authservice.model;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("GUEST")
public class Guest extends Customer {
    public Guest() {}

    public Guest(String email) {
        super(email);
    }

    @Override
    public String toString() {
        return String.format("(ID: %d, Email: %s)", id, email);
    }
}