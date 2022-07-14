package com.acoustic.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Component
public class UserData {


    private int id;
    private String name;
    private String surname;
    private String address;
    private String company;
    private BigDecimal monthlyGrossSalary;



}
