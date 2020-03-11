package com.mapfap.spez.service;

import com.mapfap.spez.repository.OrderRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
@ExtendWith(SpringExtension.class)
@DataJpaTest(showSql = false)
public class OrderServiceTest {

    @Autowired
    private OrderRepository orderRepository;

    @Test
    @DisplayName("Check if @DataJpaTest works as expected")
    public void testContext() {
        assertThat(orderRepository).isNotNull();
        assertThat(orderRepository.findAll()).hasSize(0);
    }
}
