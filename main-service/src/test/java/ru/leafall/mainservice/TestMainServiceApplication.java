package ru.leafall.mainservice;

import org.springframework.boot.SpringApplication;

public class TestMainServiceApplication {

    public static void main(String[] args) {
        SpringApplication.from(MainServiceApplication::main).with(TestcontainersConfiguration.class).run(args);
    }

}
