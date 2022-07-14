package com.example.rabbitmqreceiver.entity;

import lombok.*;
import org.springframework.stereotype.Component;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.math.BigDecimal;

//@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Component
@Entity
@ToString
public class UserData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String surname;
    private String address;
    private String company;
    private BigDecimal monthlyNetSalary;
    private BigDecimal monthlyGrossSalary;


}
