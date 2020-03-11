package com.mapfap.spez;

import com.mapfap.spez.config.ConfigProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;


@SpringBootApplication
@EnableConfigurationProperties(ConfigProperties.class)
public class SpezApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpezApplication.class, args);
    }

}
