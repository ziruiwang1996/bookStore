package com.cse687.zirui.bookstore.services.order.model.bookstate;

public interface BookState {
    public void sell();
    public void buy();
    public void reserve();
    public void cancelReserve();
}
