package com.acoustic.controller;


import com.acoustic.model.UserData;
import com.acoustic.service.RabbitMqSenderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/producer")
@Slf4j
public class ProducerController {

    private final RabbitMqSenderService rabbitMqSenderService;

    @PostMapping("/user")
    public String publishUserDetails(@RequestBody UserData userData){
        this.rabbitMqSenderService.send(userData);
        log.info("User published successfully " + userData);
        return "Message sent successfully" ;

    }


}
