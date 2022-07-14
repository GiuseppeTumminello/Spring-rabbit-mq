package com.acoustic.rabbitmqvalues;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
@Getter
@Setter
@Configuration
@PropertySource("classpath:rabbitmq.properties")
@ConfigurationProperties(prefix = "rabbitmq")
public class RabbitMqValues {

    private String queue;
    private String routingKey;
    private String exchange;
    private boolean durable;
}
