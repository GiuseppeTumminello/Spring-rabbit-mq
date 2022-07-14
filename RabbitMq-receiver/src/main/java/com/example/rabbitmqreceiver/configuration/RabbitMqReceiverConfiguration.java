package com.example.rabbitmqreceiver.configuration;

import com.example.rabbitmqreceiver.rabbitmqvalues.RabbitMqValues;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class RabbitMqReceiverConfiguration {

    private final RabbitMqValues rabbitMqValues;

    @Bean
    public Queue queue() {
        return new Queue(rabbitMqValues.getQueue(), true);
    }

    @Bean
    public Exchange myExchange() {
        return ExchangeBuilder.directExchange(rabbitMqValues.getExchange()).durable(true).build();
    }

    @Bean
    public Binding binding() {
        return BindingBuilder
                .bind(queue())
                .to(myExchange())
                .with(rabbitMqValues.getRoutingKey())
                .noargs();
    }

    @Bean
    public MessageConverter jsonMessageConverter() {
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory) {
        final RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(jsonMessageConverter());
        return rabbitTemplate;
    }
}


