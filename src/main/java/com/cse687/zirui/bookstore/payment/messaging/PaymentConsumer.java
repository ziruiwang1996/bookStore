package com.cse687.zirui.bookstore.payment.messaging;
import com.cse687.zirui.bookstore.payment.domain.command.*;
import com.cse687.zirui.bookstore.payment.service.*;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PaymentConsumer {
    private final PaymentService paymentServ;

    @Autowired
    public PaymentConsumer(PaymentService paymentService) {
        this.paymentServ = paymentService;
    }

    @RabbitListener(queues = "payment.process")
    public void handleProcessPaymentCmd(ProcessPayment cmd) {
        paymentServ.processPayment(cmd);
    }

    @RabbitListener(queues = "payment.process")
    public void handleGenerateInvoiceCmd(GenerateInvoice cmd) {
        paymentServ.generateInvoice(cmd);
    }
}