package com.cse687.zirui.bookstore.payment.messaging;
import com.cse687.zirui.bookstore.payment.domain.command.*;
import com.cse687.zirui.bookstore.payment.service.*;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PaymentConsumer {
    private final PaymentService paymentServ;
    public static final String PROCESS_PAYMENT = "order.queue.processPayment"; //from order service
    public static final String GENERATE_INVOICE = "payment.queue.generateInvoice";

    @Autowired
    public PaymentConsumer(PaymentService paymentService) {
        this.paymentServ = paymentService;
    }

    @RabbitListener(queues = PROCESS_PAYMENT)
    public void handleProcessPaymentCmd(ProcessPayment cmd) {
        paymentServ.processPayment(cmd);
    }

    @RabbitListener(queues = GENERATE_INVOICE)
    public void handleGenerateInvoiceCmd(GenerateInvoice cmd) {
        paymentServ.generateInvoice(cmd);
    }
}