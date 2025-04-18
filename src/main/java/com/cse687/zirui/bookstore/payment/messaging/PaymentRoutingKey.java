package com.cse687.zirui.bookstore.payment.messaging;

public enum PaymentRoutingKey {
    PAYMENT_SUCCEEDED("payment.evt.succeeded"),
    PAYMENT_FAILED("payment.evt.failed"),
    GENERATE_INVOICE("payment.cmd.generateInvoice");

    private final String key;

    PaymentRoutingKey(String key) {
        this.key = key;
    }

    public String getKey() {
        return key;
    }
}
