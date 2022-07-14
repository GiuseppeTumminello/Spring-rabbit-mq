package com.acoustic.service;


import com.acoustic.model.UserData;
import com.acoustic.rabbitmqvalues.RabbitMqValues;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RabbitMqSenderServiceImplementation implements RabbitMqSenderService {


    private final RabbitTemplate rabbitTemplate;
    private final RabbitMqValues rabbitMqValues;


    @Override
    public void send(UserData userData) {
        this.rabbitTemplate.convertAndSend(this.rabbitMqValues.getExchange(), this.rabbitMqValues.getRoutingKey(), userData);
    }
}
