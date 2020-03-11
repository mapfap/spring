package com.mapfap.spez.repository;

import com.github.javafaker.Faker;
import com.mapfap.spez.model.Order;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executors;

@Slf4j
@Repository
@RequiredArgsConstructor
public class PricingRepository {

    private final OrderRepository orderRepository;

    public CompletableFuture<BigDecimal> getPrice() {
        log.info("Begin getPrice()");
        CompletableFuture<BigDecimal> future = new CompletableFuture<>();
        Executors.newSingleThreadExecutor().submit(() -> {
            try {
                Thread.sleep(1000);
                log.info("End getPrice()");
                future.complete(BigDecimal.valueOf(10));
            } catch (Exception exception) {
                log.error("getPrice() thread exception", exception);
                future.cancel(false);
            }
            return null;
        });
        return future;
    }

    public CompletableFuture<Void> race(Order order) {
        CompletableFuture<Void> future = new CompletableFuture<>();
        Executors.newSingleThreadExecutor().submit(() -> {
            try {
                Thread.sleep((new Faker()).number().numberBetween(0, 1) * 1000);
                int expectedScore = order.getScore() + 1;
                log.info(String.format("Expected score = %d", expectedScore));

                Thread.sleep((new Faker()).number().numberBetween(0, 1) * 1000);
                order.setScore(expectedScore);
                log.info(String.format("Set score = %d", expectedScore));
                future.complete(null);
            } catch (Exception exception) {
                exception.printStackTrace();
                future.cancel(false);
            }

            return null;
        });
        return future;
    }
}
