package com.cse687.zirui.bookstore.auth.domain.model;
import jakarta.persistence.*;

@Entity(name = "customer")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "customer_type", discriminatorType = DiscriminatorType.STRING)
public abstract class Customer {
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    protected Long id;
    protected String email;

    public Customer() {}

    public Customer(String email) {
        this.email = email;
    }

    public Long getId() { return id; }

    public String getEmail() { return email; }

    public abstract String toString();
}
