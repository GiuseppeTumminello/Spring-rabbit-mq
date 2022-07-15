package com.example.rabbitmqreceiver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@EnableAsync
@SpringBootApplication
public class RabbitMqReceiverApplication {

    public static void main(String[] args) {
        SpringApplication.run(RabbitMqReceiverApplication.class, args);
    }

}
