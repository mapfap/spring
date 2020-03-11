package com.mapfap.spez.service;

import com.mapfap.spez.model.Order;
import com.mapfap.spez.repository.OrderRepository;
import com.mapfap.spez.repository.PricingRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Set;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final PricingRepository pricingRepository;

    public int race(Order order) {
        List<CompletableFuture<Void>> futures = order.getItems().stream()
                .map(item -> pricingRepository.race(order))
                .collect(Collectors.toList());
        try {
            CompletableFuture.allOf(futures.toArray(new CompletableFuture[0])).get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        return order.getScore();
    }


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

    public Order createOrder(Order order) {
        return orderRepository.save(order);
    }
}
