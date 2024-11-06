package com.cse687.zirui.bookstore.domain.model;
import jakarta.persistence.*;

import java.util.List;

@Entity(name = "customer")
public class Customer {
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String email;
    private float balance;
    @OneToMany(mappedBy = "customer")
    private List<Order> orderHistory;

    public Customer() {}

    public Customer(String email, float blc) {
        this.email = email;
        this.balance = blc;
    }

    public Long getId() { return id; }

    public String getEmail() { return email; }

    public float getBalance() { return balance; }

    public List<Order> getTransaction() { return orderHistory; }

    public void addOrder(Order o) { orderHistory.add(o); }
}
