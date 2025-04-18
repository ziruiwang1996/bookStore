package com.cse687.zirui.bookstore.cart.messaging;
import com.cse687.zirui.bookstore.cart.domain.command.*;
import com.cse687.zirui.bookstore.cart.domain.event.*;
import com.cse687.zirui.bookstore.cart.service.CartService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CartConsumer {
    private final CartService cartServ;

    @Autowired
    public CartConsumer(CartService cartServ) {
        this.cartServ = cartServ;
    }

    @RabbitListener(queues = "")
    public void handleAddItemCmd(AddItem cmd) {
        cartServ.addItem(cmd);
    }

    @RabbitListener(queues = "")
    public void handleItemAddedEvt(ItemAdded evt) {
        cartServ.itemAdded(evt);
    }

    @RabbitListener(queues = "")
    public void handleRemoveItemCmd(RemoveItem cmd) {
        cartServ.removeItem(cmd);
    }

    @RabbitListener(queues = "")
    public void handleItemRemovedEvt(ItemRemoved evt) {
        cartServ.itemRemoved(evt);
    }

    @RabbitListener(queues = "")
    public void handlePlaceOrderCmd(PlaceOrder cmd) {
        cartServ.placeOrder(cmd);
    }

    @RabbitListener(queues = "auth.cmd.create_cart")
    public void handleCreateCartCmd(CreateCart cmd) {
        cartServ.createCart(cmd);
    }

    @RabbitListener(queues = "auth.cmd.delete_cart")
    public void handleDeleteCartCmd(DeleteCart cmd) {
        cartServ.deleteCart(cmd);
    }










}
