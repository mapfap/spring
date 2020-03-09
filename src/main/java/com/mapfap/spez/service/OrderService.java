package com.mapfap.spez.service;

import com.mapfap.spez.model.Order;
import com.mapfap.spez.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;

    public Iterable<Order> getOrders() {
        return orderRepository.findAll();
    }

}
