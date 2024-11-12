package com.cse687.zirui.bookstore.domain.model;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import java.util.Date;

@Entity(name = "book_order")
public class Order {
    @Id @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;
    private Long bookid;
    private float amount;
    @Temporal(TemporalType.DATE)
    private Date date;
    @JsonProperty("order_type")
    @Enumerated(EnumType.STRING)
    private OrderType ordertype;
    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    public Order() {}

    public Order(Customer customer, Long bid, float amt, Date date, int orderType) {
        this.customer = customer;
        this.bookid = bid;
        this.amount = amt;
        this.date = date;
        if (orderType == 1) {
            this.ordertype = OrderType.STORE_BUY;
        } else {
            this.ordertype = OrderType.STORE_SELL;
        }
    }

    public Long getId() { return id; }

    public float getAmount() { return amount; }

    public Long getBookId() { return bookid; }

    public Date getDate() { return date; }
}

enum OrderType {STORE_BUY, STORE_SELL}