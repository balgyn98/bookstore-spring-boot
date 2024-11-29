package com.example.in_memory_db_starter;

import jakarta.annotation.PostConstruct;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class  InMemoryDbStarterAutoConfiguration {
    @Bean
    @ConditionalOnMissingBean
    public <T> InMemoryRepository<T> inMemoryRepository() {
        return new InMemoryRepository<>();
    }

    @PostConstruct
    public void init() {
        System.out.println("InMemoryDbStarterAutoConfiguration initialized!");
    }
}
