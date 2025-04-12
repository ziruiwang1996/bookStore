package com.cse687.zirui.bookstore.payment.model;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;

import java.util.Date;

@Entity(name = "invoice")
public class Invoice {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;
    private Long customerId;

    private double amount;
    @Temporal(TemporalType.DATE)
    private Date date;
    @JsonProperty("order_type")
    @Enumerated(EnumType.STRING)
    private OrderType ordertype;


    enum OrderType {STORE_BUY, STORE_SELL}

    public Invoice() {}

    public Invoice(Long customerid, double amt, Date date, int orderType) {
        this.customerId = customerid;
        this.amount = amt;
        this.date = date;
        if (orderType == 1) {
            this.ordertype = OrderType.STORE_BUY;
        } else {
            this.ordertype = OrderType.STORE_SELL;
        }
    }

    public Long getId() { return id; }

    public double getAmount() { return amount; }

//    public Long getBookId() { return bookid; }

    public Date getDate() { return date; }
}