package com.cse687.zirui.bookstore.services.customer.model;
import jakarta.persistence.*;
import java.util.ArrayList;

@Entity(name = "customer")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "customer_type", discriminatorType = DiscriminatorType.STRING)
public abstract class Customer {
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    protected Long id;
    protected String email;
    @OneToMany(mappedBy = "customer", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    protected List<Order> orderHistory;

    public Customer() {}

    public Customer(String email) {
        this.email = email;
        this.orderHistory = new ArrayList<>();
    }

    public Long getId() { return id; }

    public String getEmail() { return email; }

    public List<Order> getOrderHistory() { return orderHistory; }

    public void addOrder(Order o) { orderHistory.add(o); }

    public abstract String toString();
}
