package com.mapfap.spez.integration;

import com.mapfap.spez.model.Order;
import com.mapfap.spez.model.OrderItem;
import com.mapfap.spez.repository.OrderRepository;
import com.mapfap.spez.service.OrderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.math.BigDecimal;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
@ExtendWith(SpringExtension.class)
//@DataJpaTest(showSql = false)
@SpringBootTest
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class OrderServiceTest {

    private final OrderRepository orderRepository;

//    private final PricingRepository pricingRepository;

    private final OrderService orderService;

    @Test
    public void testPricing() {
        Order order = Order.builder().build();
        Stream.of(
                OrderItem.builder().build(),
                OrderItem.builder().build(),
                OrderItem.builder().build(),
                OrderItem.builder().build(),
                OrderItem.builder().build(),
                OrderItem.builder().build()
                ).forEach(order::addItem);
        assertThat(orderService.getTotalPrice(order)).isGreaterThan(BigDecimal.ZERO);
    }

    @Test
    @DisplayName("Check if @DataJpaTest works as expected")
    public void testContext() {
        assertThat(orderRepository).isNotNull();
        assertThat(orderRepository.findAll()).hasSize(1);
    }
}
