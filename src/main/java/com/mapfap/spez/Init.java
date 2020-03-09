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
import java.util.ArrayList;
import java.util.List;

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

        List<Product> productList = new ArrayList<>();
        for (int p = 0; p < 1000; p++) {
            Product product = Product.builder()
                    .name(faker.food().dish())
                    .build();
            productRepository.saveAndFlush(product);
            productList.add(product);
        }

        for (int u = 0; u < 20; u++) {
            log.info(String.format("User#%d", u));
            User user = User.builder()
                    .name(faker.animal().name())
                    .createdAt(Instant.now())
                    .build();
            userRepository.saveAndFlush(user);

            List<Address> addressList = new ArrayList<>();

            for (int a = 0; a < 3; a++) {
                Address address = Address.builder()
                        .user(user)
                        .street(faker.address().streetAddress())
                        .city(faker.address().city())
                        .postalCode(faker.address().zipCode())
                        .build();
                addressRepositry.saveAndFlush(address);
                addressList.add(address);
            }

            for (int o = 0; o < 50; o++) {
                Order order = Order.builder()
                        .address(addressList.get(faker.number().numberBetween(0, 2)))
                        .user(user)
                        .createdAt(Instant.now())
                        .build();

                for (int i = 0; i < 10; i++) {
                    OrderItem item = OrderItem.builder()
                            .product(productList.get(faker.number().numberBetween(0, 999)))
                            .quantity(faker.number().numberBetween(1, 10))
                            .build();
                    order.addItem(item);
                }

                orderRepository.saveAndFlush(order);
            }

        }


        log.info(".....................");
        log.info(".....................");
        log.info(".....................");
    }
}
