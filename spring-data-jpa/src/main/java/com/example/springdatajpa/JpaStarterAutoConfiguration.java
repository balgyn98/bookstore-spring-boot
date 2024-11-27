package com.example.springdatajpa;

import jakarta.annotation.PostConstruct;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@ConditionalOnClass(name = "org.springframework.data.jpa.repository.JpaRepository")
@EnableJpaRepositories(basePackages = "com.example.repository")
@EntityScan(basePackages = "com.example.entity")
public class JpaStarterAutoConfiguration {

    @PostConstruct
    public void init() {
        System.out.println("JpaStarterAutoConfiguration initialized!");
    }

}