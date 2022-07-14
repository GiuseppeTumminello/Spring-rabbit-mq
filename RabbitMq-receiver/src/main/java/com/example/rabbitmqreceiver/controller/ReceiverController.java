package com.example.rabbitmqreceiver.controller;

import com.example.rabbitmqreceiver.entity.UserData;
import com.example.rabbitmqreceiver.repository.UserRepository;
import com.example.rabbitmqreceiver.service.SalaryCalculatorService;
import io.netty.handler.codec.http.HttpResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.annotation.RabbitListenerConfigurer;
import org.springframework.amqp.rabbit.listener.RabbitListenerEndpointRegistrar;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
        userData.setMonthlyNetSalary(this.salaryCalculatorService.apply(userData.getMonthlyGrossSalary()));
        this.userRepository.saveAndFlush(userData);
        log.info(userData + "saved in the database");

    }



    @GetMapping("/find-user/{userId}")
    public ResponseEntity<Optional<UserData>> getUserById(@PathVariable int  userId){
        var user = this.userRepository.findById(userId);
        return ResponseEntity.ok().header(HttpHeaders.ALLOW).body(user);
    }

    @DeleteMapping("/delete-user/{userId}")
    public  ResponseEntity<String> deleteUserById(@PathVariable int userId){
        var user = this.userRepository.findById(userId);
        if (user.isPresent()) {
            this.userRepository.deleteById(userId);
            return ResponseEntity.ok().header(HttpHeaders.ALLOW).body("User with id: " + user.get().getId() + ", name: " + user.get().getName() + " deleted successfully" );
        }
            return ResponseEntity.badRequest().header(HttpHeaders.WARNING).body("User with id: " + userId + "is not present in the database");

    }




}
