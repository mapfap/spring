package com.mapfap.spez;

import com.github.javafaker.Faker;
import com.mapfap.spez.model.*;
import com.mapfap.spez.repository.AddressRepositry;
import com.mapfap.spez.repository.OrderRepository;
import com.mapfap.spez.repository.ProductRepository;
import com.mapfap.spez.repository.UserRepository;
import com.mapfap.spez.service.OrderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.Instant;

@Slf4j
@Component
@RequiredArgsConstructor
public class Init implements CommandLineRunner {

    private final OrderRepository orderRepository;

    private final UserRepository userRepository;

    private final AddressRepositry addressRepositry;

    private final ProductRepository productRepository;

    private final OrderService orderService;

    @Override
    public void run(String... args) throws Exception {
        log.info("Init data records");
        Faker faker = new Faker();

        User user = User.builder()
                .name(faker.animal().name())
                .createdAt(Instant.now())
                .build();
        userRepository.saveAndFlush(user);

        Address address = Address.builder()
                .user(user)
                .street(faker.address().streetAddress())
                .city(faker.address().city())
                .postalCode(faker.address().zipCode())
                .build();
        addressRepositry.saveAndFlush(address);

        Order order = Order.builder()
                .address(address)
                .user(user)
                .createdAt(Instant.now())
                .build();

        for (int i = 0; i < faker.number().numberBetween(2, 5); i++) {

            Product product = Product.builder()
                    .name(faker.food().dish())
                    .build();
            productRepository.saveAndFlush(product);

            OrderItem item = OrderItem.builder()
                    .product(product)
                    .quantity(faker.number().numberBetween(1, 10))
                    .build();
            order.addItem(item);
        }

        orderRepository.saveAndFlush(order);

        log.info(".....................");
        log.info(".....................");
        log.info(".....................");
    }
}
