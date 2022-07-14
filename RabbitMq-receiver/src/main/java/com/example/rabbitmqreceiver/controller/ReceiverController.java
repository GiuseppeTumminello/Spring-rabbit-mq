package com.example.rabbitmqreceiver.controller;

import com.example.rabbitmqreceiver.entity.UserData;
import com.example.rabbitmqreceiver.repository.UserRepository;
import com.example.rabbitmqreceiver.service.SalaryCalculatorService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.annotation.RabbitListenerConfigurer;
import org.springframework.amqp.rabbit.listener.RabbitListenerEndpointRegistrar;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/receiver")
@Slf4j
public class ReceiverController implements RabbitListenerConfigurer {

    private final UserRepository userRepository;
    private final SalaryCalculatorService salaryCalculatorService;


    @Override
    public void configureRabbitListeners(RabbitListenerEndpointRegistrar registrar) {

    }

    @RabbitListener(queues = "${rabbitmq.queue}")
    public void receivedMessage(UserData userData) {
        userData.setMonthlyNetSalary(salaryCalculatorService.apply(userData.getMonthlyGrossSalary()));
        userRepository.saveAndFlush(userData);
        log.info(userData + "saved in the database");

    }



    @GetMapping("/find-user/{userId}")
    public Optional<UserData> getUserById(@PathVariable int  userId){
        return userRepository.findById(userId);

    }




}
