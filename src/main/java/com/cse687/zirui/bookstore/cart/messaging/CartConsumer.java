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

    private static final String ADD_ITEM = "cart.queue.addItem";
    private static final String REMOVE_ITEM = "cart.queue.removeItem";
    private static final String PLACE_ORDER = "cart.queue.placeOrder";
    private static final String ITEM_ADDED = "cart.queue.itemAdded";
    private static final String ITEM_REMOVED = "cart.queue.itemRemoved";

    private static final String CREATE_CART = "auth.queue.createCart"; // from auth service
    private static final String DELETE_CART = "auth.queue.deleteCart"; // from auth service

    @Autowired
    public CartConsumer(CartService cartServ) {
        this.cartServ = cartServ;
    }

    @RabbitListener(queues = ADD_ITEM)
    public void handleAddItemCmd(AddItem cmd) {
        cartServ.addItem(cmd);
    }

    @RabbitListener(queues = ITEM_ADDED)
    public void handleItemAddedEvt(ItemAdded evt) {
        cartServ.itemAdded(evt);
    }

    @RabbitListener(queues = REMOVE_ITEM)
    public void handleRemoveItemCmd(RemoveItem cmd) {
        cartServ.removeItem(cmd);
    }

    @RabbitListener(queues = ITEM_REMOVED)
    public void handleItemRemovedEvt(ItemRemoved evt) {
        cartServ.itemRemoved(evt);
    }

    @RabbitListener(queues = PLACE_ORDER)
    public void handlePlaceOrderCmd(PlaceOrder cmd) {
        cartServ.placeOrder(cmd);
    }

    @RabbitListener(queues = CREATE_CART)
    public void handleCreateCartCmd(CreateCart cmd) {
        cartServ.createCart(cmd);
    }

    @RabbitListener(queues = DELETE_CART)
    public void handleDeleteCartCmd(DeleteCart cmd) {
        cartServ.deleteCart(cmd);
    }










}
