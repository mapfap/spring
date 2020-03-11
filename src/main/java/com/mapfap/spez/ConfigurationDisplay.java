package com.mapfap.spez;

import com.mapfap.spez.config.ConfigProperties;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class ConfigurationDisplay implements CommandLineRunner {

    private final ConfigProperties properties;

    @Override
    public void run(String... args) throws Exception {
        // TODO: Not working
        log.info(properties.getName());
    }
}
