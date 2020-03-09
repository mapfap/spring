package com.mapfap.spez.controller;

import com.mapfap.spez.model.Order;
import com.mapfap.spez.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @GetMapping
    Iterable<Order> getOrders() {
        return orderService.getOrders();
    }
}
