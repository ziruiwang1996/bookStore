package com.cse687.zirui.bookstore.payment.domain.model;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import java.util.Date;
import java.util.List;

@Entity(name = "invoice")
public class Invoice {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;
    private Long orderId;
    private Long paymentId;
    private Long userId;
    @ElementCollection
    private List<Long> items;
    private float amount;
    @Temporal(TemporalType.DATE)
    private Date date;

    public Invoice() {}

    public Invoice(Long orderId, Long paymentId, Long userid, List<Long> items, float amt) {
        this.orderId = orderId;
        this.paymentId = paymentId;
        this.userId = userid;
        this.amount = amt;
        this.date = new Date();
    }

    public Long getOrderId() { return orderId; }

    public Long getPaymentId() { return paymentId; }

    public Long getUserId() { return userId; }

    public double getAmount() { return amount; }

    public Date getDate() { return date; }
}