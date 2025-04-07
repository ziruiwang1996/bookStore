package com.cse687.zirui.bookstore.services.customer.model;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("MEMBER")
public class Member extends Customer {
    private String password;
    private float credit;

    public Member() {}

    public Member(String email, String password, float balance) {
        super(email);
        this.password = password; //need hash function here
        this.credit = balance;
    }

    public String getPassword() {
        return password;
    }

    public float getCredit() { return credit; }

    public void addCredit(float amount) {
        credit += amount;
    }

    public void subtractCredit(float amount) {
        if (amount > credit) {
            throw new RuntimeException("Insufficient Credit");
        }
        credit -= amount;
    }

    @Override
    public String toString() {
        return String.format("(ID: %d, Email: %s, Balance: %.2f)", id, email, credit);
    }
}