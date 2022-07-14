package com.acoustic.service;


import com.acoustic.model.UserData;
import org.springframework.stereotype.Service;

@Service
public interface RabbitMqSenderService {

     void send(UserData userData);
}
