package com.cse687.zirui.bookstore.order.domain.model.bookstate;

public interface BookState {
    public void userSell();
    public void userBuy();
    public void reserve();
    public void cancelReserve();
    public String getCode();
}
