package com.cse687.zirui.bookstore.cart.messaging;
import com.cse687.zirui.bookstore.cart.domain.command.*;
import com.cse687.zirui.bookstore.cart.domain.event.*;
import com.cse687.zirui.bookstore.shared.RoutingKey;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

@Component
public class CartProducer {
    private final RabbitTemplate rabbitTemplate;
    public static final String EXCHANGE = "cart.exchange";

    public CartProducer(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void publishAddItemCmd(AddItem cmd) {
        rabbitTemplate.convertAndSend(EXCHANGE, RoutingKey.ADD_ITEM.getKey(), cmd);
    }

    public void publishItemAddedEvt(ItemAdded evt) {
        rabbitTemplate.convertAndSend(EXCHANGE, RoutingKey.ITEM_ADDED.getKey(), evt);
    }

    public void publishRemoveItemCmd(RemoveItem cmd) {
        rabbitTemplate.convertAndSend(EXCHANGE, RoutingKey.REMOVE_ITEM.getKey(), cmd);
    }

    public void publishItemRemovedEvt(ItemRemoved cmd) {
        rabbitTemplate.convertAndSend(EXCHANGE, RoutingKey.ITEM_REMOVED.getKey(), cmd);
    }

    public void publishPlaceOrderCmd(PlaceOrder cmd) {
        rabbitTemplate.convertAndSend(EXCHANGE, RoutingKey.PLACE_ORDER.getKey(), cmd);
    }

    public void publishOrderPlacedEvt(OrderPlaced evt) {
        rabbitTemplate.convertAndSend(EXCHANGE, RoutingKey.ORDER_PLACED.getKey(), evt);
        // to order service
    }
}
