package com.example.rabbitmqreceiver.service;

import com.example.rabbitmqreceiver.rate.RatesConfigurationProperties;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Service
@RequiredArgsConstructor
@Slf4j
public class MonthlyNetService implements SalaryCalculatorService {

    private static final int MONTHS_NUMBER = 12;
    private final RatesConfigurationProperties rate;

    @Override
    @Async
    public BigDecimal apply(BigDecimal grossMonthlySalary) {
        if (grossMonthlySalary.multiply(BigDecimal.valueOf(MONTHS_NUMBER)).compareTo(this.rate.getTaxGrossAmountThreshold()) < 0) {
            return getMonthlyNetBasedOnRate(grossMonthlySalary, this.rate.getTaxRate17Rate());
        } else {
            return getMonthlyNetBasedOnRate(grossMonthlySalary, this.rate.getTaxRate32Rate());
        }
    }

    private BigDecimal calculateTotalZus(BigDecimal grossMonthlySalary) {
        return grossMonthlySalary.subtract(grossMonthlySalary.multiply(this.rate.getTotalZusRate()));
    }

    private BigDecimal calculateHealth(BigDecimal grossMonthlySalary) {
        return grossMonthlySalary.subtract(grossMonthlySalary.multiply(this.rate.getHealthRate()));
    }

    private BigDecimal getMonthlyNetBasedOnRate(BigDecimal grossMonthlySalary, BigDecimal rate) {
        var salaryMinusTotalZus = calculateTotalZus(grossMonthlySalary);
        var salaryMinusHealth = calculateHealth(salaryMinusTotalZus);
        return salaryMinusHealth.subtract(salaryMinusHealth.multiply(rate)).setScale(2, RoundingMode.HALF_EVEN);

    }

}
