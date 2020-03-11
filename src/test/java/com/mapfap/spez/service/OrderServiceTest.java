package com.mapfap.spez.service;

import com.mapfap.spez.repository.OrderRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertTrue;

@Slf4j
@ExtendWith(SpringExtension.class)
@DataJpaTest
public class OrderServiceTest {

    @Autowired
    private OrderRepository orderRepo;

    @BeforeEach
    public void beforeEach() {
        log.info("Testtttt!!");
    }

    @Test
    @DisplayName("1 is 1")
    public void test() {
        assertTrue(1 == 1);
    }

    @Test
    @DisplayName("Repo is loaded")
    public void test2() {
        assertTrue(orderRepo != null);
    }
}
