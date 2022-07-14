package com.example.rabbitmqreceiver.rate;


import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import java.math.BigDecimal;

@Getter
@Setter
@ConfigurationProperties(prefix = "rate")
@PropertySource(value = "classpath:rates.properties")
@Configuration
public class RatesConfigurationProperties {

    private BigDecimal taxRate32Rate;

    private BigDecimal taxRate17Rate;

    private BigDecimal taxGrossAmountThreshold;

    private BigDecimal totalZusRate;

    private BigDecimal healthRate;

}
