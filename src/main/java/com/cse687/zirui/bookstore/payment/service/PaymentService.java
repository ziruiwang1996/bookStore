package com.cse687.zirui.bookstore.payment.service;
import com.cse687.zirui.bookstore.payment.domain.command.*;
import com.cse687.zirui.bookstore.payment.domain.event.*;
import com.cse687.zirui.bookstore.payment.domain.model.Invoice;
import com.cse687.zirui.bookstore.payment.repository.InvoiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.cse687.zirui.bookstore.payment.messaging.*;
import java.util.Objects;
import java.util.Random;

@Service
public class PaymentService {
    private final PaymentProducer producer;
    private final InvoiceRepository invoiceRepo;
    
    @Autowired
    public PaymentService(PaymentProducer producer, InvoiceRepository invoiceRepo) {
        this.producer = producer;
        this.invoiceRepo = invoiceRepo;
    }

    public void processPayment(ProcessPayment cmd) {
        // Simulate payment success or failure
        Random random = new Random();
        boolean isSuccess = random.nextBoolean();
        Long paymentId = (long) Objects.hash(cmd.userId(), cmd.orderId(), cmd.amount());

        if (isSuccess) {
            producer.publishPaymentSucceededEvt(
                new PaymentSucceeded(cmd.orderId(), cmd.userId(), cmd.items())
            );
            producer.publishGenerateInvoiceCmd(
                    new GenerateInvoice(
                            cmd.orderId(), paymentId, cmd.userId(), cmd.items(), cmd.amount()
                    )
            );
        } else {
            // Send payment failure message
            producer.publishPaymentFailedEvt(
                new PaymentFailed(cmd.orderId(), "Insufficient funds")
            );
        }
    }

    public void generateInvoice(GenerateInvoice cmd) {
        Invoice invoice = new Invoice(cmd.orderId(), cmd.paymentId(), cmd.userId(), cmd.items(), cmd.amt());
        invoiceRepo.save(invoice);
    }
}