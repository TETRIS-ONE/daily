package com.geek.commonjava;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication(scanBasePackages = {"com.geek.commonjava.*, com.geek.commonjava.threadpool.*"})
public class CommonjavaApplication {

    public static void main(String[] args) {
        SpringApplication.run(CommonjavaApplication.class, args);
    }
}
