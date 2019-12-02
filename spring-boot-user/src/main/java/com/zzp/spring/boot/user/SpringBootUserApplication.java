package com.zzp.spring.boot.user;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "com.zzp.spring.boot.user")
public class SpringBootUserApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootUserApplication.class, args);
    }

}
