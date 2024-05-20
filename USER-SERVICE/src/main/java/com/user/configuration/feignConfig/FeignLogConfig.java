package com.user.configuration.feignConfig;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import feign.Logger;


@Configuration
public class FeignLogConfig {

    @Bean
    Logger.Level feignLoggerLevel(){
        return Logger.Level.BASIC;
    }

}