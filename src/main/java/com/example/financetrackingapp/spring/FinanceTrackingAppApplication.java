package com.example.financetrackingapp.spring;

import com.example.financetrackingapp.security.SecurityConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackageClasses = SecurityConfig.class)
public class FinanceTrackingAppApplication {

    public static void main(String[] args) {
        SpringApplication.run(FinanceTrackingAppApplication.class, args);
    }
}
