package com.cse687.zirui.bookstore.payment.messaging;
import com.cse687.zirui.bookstore.payment.domain.command.GenerateInvoice;
import com.cse687.zirui.bookstore.payment.domain.event.PaymentFailed;
import com.cse687.zirui.bookstore.payment.domain.event.PaymentSucceeded;
import com.cse687.zirui.bookstore.shared.RoutingKey;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

@Component
public class PaymentProducer {
    private final RabbitTemplate rabbitTemplate;
    public static final String EXCHANGE = "payment.exchange";

    public PaymentProducer(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void publishPaymentSucceededEvt(PaymentSucceeded evt) {
        rabbitTemplate.convertAndSend(EXCHANGE, RoutingKey.PAYMENT_SUCCEEDED.getKey(), evt);
    }

    public void publishPaymentFailedEvt(PaymentFailed evt) {
        rabbitTemplate.convertAndSend(EXCHANGE, RoutingKey.PAYMENT_FAILED.getKey(), evt);
    }

    public void publishGenerateInvoiceCmd(GenerateInvoice cmd){
        rabbitTemplate.convertAndSend(EXCHANGE, "", cmd);
    }
}