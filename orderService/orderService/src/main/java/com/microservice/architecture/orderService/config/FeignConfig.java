package com.microservice.architecture.orderService.config;

import com.microservice.architecture.orderService.exception.CustomException;
import com.microservice.architecture.orderService.external.decoder.CustomErrorException;
import feign.codec.ErrorDecoder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FeignConfig {

    @Bean
    ErrorDecoder errorDecoder(){
        return new CustomErrorException();
    }
}
