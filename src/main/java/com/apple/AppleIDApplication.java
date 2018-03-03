package com.apple;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableAutoConfiguration
public class AppleIDApplication {
    public static void main(String[] args) {
        SpringApplication.run(AppleIDApplication.class, args);
    }
}
