package com.mapfap.spez;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;


@SpringBootApplication
public class SpezApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpezApplication.class, args);
    }

}
