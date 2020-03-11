package com.mapfap.spez.repository;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executors;

@Slf4j
@Repository
public class PricingRepository {

    public CompletableFuture<BigDecimal> getPrice() {
        log.info("Begin getPrice()");
        CompletableFuture<BigDecimal> future = new CompletableFuture<>();
        Executors.newSingleThreadExecutor().submit(() -> {
            Thread.sleep(1000);
            log.info("End getPrice()");
            future.complete(BigDecimal.valueOf(10));
            return null;
        });
        return future;
    }
}
