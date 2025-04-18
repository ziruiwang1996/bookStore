package com.cse687.zirui.bookstore.order.domain.event;
import java.util.List;

public record OrderPlaced(
        Long userId,
        List<Long> items
) implements Event {}