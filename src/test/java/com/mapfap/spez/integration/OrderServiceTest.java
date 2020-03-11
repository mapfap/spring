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
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
@ExtendWith(SpringExtension.class)
//@DataJpaTest(showSql = false)
@SpringBootTest
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class OrderServiceTest {

    private final OrderRepository orderRepository;
    private final OrderService orderService;

    @Test
    public void raceCondition() {
        // TODO: This need to be equalsTo 10
        assertThat(orderService.race(createOrder(10))).isGreaterThan(0);
    }

    @Test
    public void save() {
        Order order = orderService.createOrder(createOrder(10));
        assertThat(order).isNotNull();
        assertThat(order.getScore()).isEqualTo(0);
        log.info(order.toString());
    }

    @Test
    public void testPricing() {
        assertThat(orderService.getTotalPrice(createOrder(10))).isGreaterThan(BigDecimal.ZERO);
    }

    @Test
    @DisplayName("Check if @DataJpaTest works as expected")
    public void testContext() {
        assertThat(orderRepository).isNotNull();
        assertThat(orderRepository.findAll()).hasSize(1);
    }

    private Order createOrder(int numberOfItem) {
        Order order = Order.builder().build();
        IntStream.range(0, numberOfItem).forEach(i ->
                order.addItem(OrderItem.builder().build()));
        return order;
    }
}
