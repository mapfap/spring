package com.mapfap.spez.service;

import com.mapfap.spez.model.Order;
import com.mapfap.spez.repository.OrderRepository;
import com.mapfap.spez.repository.PricingRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Set;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final PricingRepository pricingRepository;

    public BigDecimal getTotalPrice(Order order) {
        Set<CompletableFuture<BigDecimal>> futures = order.getItems().stream()
                .map(item -> pricingRepository.getPrice())
                .collect(Collectors.toSet());
        return futures.stream()
                .map(CompletableFuture::join)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public Iterable<Order> getOrders() {
        return orderRepository.findAll();
    }

}
