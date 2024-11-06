package com.cse687.zirui.bookstore.service;
import com.cse687.zirui.bookstore.adapter.OrderRepository;
import com.cse687.zirui.bookstore.domain.model.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderService {
    private final OrderRepository orderRepo;

    @Autowired
    public OrderService(OrderRepository orderRepo) {
        this.orderRepo = orderRepo;
    }

    public void addNewOrder(Order order) {
        orderRepo.save(order);
    }
}
