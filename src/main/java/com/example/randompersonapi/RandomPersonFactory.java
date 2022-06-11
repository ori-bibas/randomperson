package com.example.randompersonapi;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RandomPersonFactory implements PersonFactory {

    @Override
    @Bean
    public Person createRandomPerson() {

        

        return null;
    }
}
