package com.cse687.zirui.bookstore.payment.messaging;

public enum PaymentQueueName {
    PAYMENT_SUCCEEDED("payment.queue.succeeded"), // consumer in order service
    PAYMENT_FAILED("payment.queue.failed"), // consumer in order service
    GENERATE_INVOICE("payment.queue.generateInvoice");

    private final String name;

    PaymentQueueName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
