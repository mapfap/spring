package com.mapfap.spez;

import com.github.javafaker.Faker;
import com.mapfap.spez.model.*;
import com.mapfap.spez.repository.AddressRepositry;
import com.mapfap.spez.repository.OrderRepository;
import com.mapfap.spez.repository.ProductRepository;
import com.mapfap.spez.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
@ConditionalOnProperty(value = "app.init.enabled")
public class Init implements CommandLineRunner {

    private final OrderRepository orderRepository;

    private final UserRepository userRepository;

    private final AddressRepositry addressRepositry;

    private final ProductRepository productRepository;

    @Override
    public void run(String... args) throws Exception {
        init(5, 1, 1, 1, 2);
    }

    public void init(int numberOfProduct, int numberOfUser, int numberOfAddress, int numberOfOrder, int numberOfOrderItem) {
        log.debug("Initializing data");
        Faker faker = new Faker();

        List<Product> productList = new ArrayList<>();
        for (int p = 0; p < numberOfProduct; p++) {
            Product product = Product.builder()
                    .name(faker.food().dish())
                    .build();
            productRepository.save(product);
            productList.add(product);
        }

        for (int u = 0; u < numberOfUser; u++) {
            User user = User.builder()
                    .name(faker.animal().name())
                    .createdAt(Instant.now())
                    .build();
            userRepository.save(user);

            List<Address> addressList = new ArrayList<>();

            for (int a = 0; a < numberOfAddress; a++) {
                Address address = Address.builder()
                        .user(user)
                        .street(faker.address().streetAddress())
                        .city(faker.address().city())
                        .postalCode(faker.address().zipCode())
                        .build();
                addressRepositry.save(address);
                addressList.add(address);
            }

            for (int o = 0; o < numberOfOrder; o++) {
                Order order = Order.builder()
                        .address(addressList.get(faker.number().numberBetween(0, addressList.size() - 1)))
                        .user(user)
                        .createdAt(Instant.now())
                        .build();

                for (int i = 0; i < numberOfOrderItem; i++) {
                    OrderItem item = OrderItem.builder()
                            .product(productList.get(faker.number().numberBetween(0, productList.size() - 1)))
                            .quantity(faker.number().numberBetween(1, 10))
                            .build();
                    order.addItem(item);
                }

                orderRepository.save(order);
            }
        }

        orderRepository.flush();
        log.info("Initialized data");
    }
}
