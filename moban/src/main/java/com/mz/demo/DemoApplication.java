package com.mz.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
@ComponentScan(basePackages = {
        "com.mz.demo.service",
        "com.mz.demo.service.impl",
        "com.mz.demo.config",
        "com.mz.demo.util",
        "com.mz.demo.controller",
        "com.mz.demo.exception",
        "com.mz.demo.realm",
        "com.mz.demo.mapper",
        "com.mz.demo.entity"})
public class DemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

}
