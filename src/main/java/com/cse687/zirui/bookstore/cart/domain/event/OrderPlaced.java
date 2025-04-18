package com.cse687.zirui.bookstore.cart.domain.event;
import java.util.List;

public record OrderPlaced(
    Long userId,
    List<Long> items
) implements Event {}
