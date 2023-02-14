package com.microservice.architecture.orderService.external.decoder;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.microservice.architecture.orderService.exception.CustomException;
import com.microservice.architecture.orderService.model.ErrorMessage;
import feign.Response;
import feign.codec.ErrorDecoder;
import lombok.extern.log4j.Log4j2;

import java.io.IOException;

@Log4j2
public class CustomErrorException implements ErrorDecoder {
    @Override
    public Exception decode(String s, Response response) {
        log.info("::{}",response.request());
        log.info("::{}",response.headers());
        try {
            log.info(" response body {}",response.body().asInputStream());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        ObjectMapper objectMapper=new ObjectMapper();
        try {
            ErrorMessage errorMessage=objectMapper.readValue(response.body().asInputStream(),ErrorMessage.class);
            throw new CustomException(errorMessage.getMessage(),errorMessage.getCode(),response.status());
        } catch (IOException e) {
            throw new CustomException("internal server error","INTERNAL_SERVER_ERROR",500);
        }
    }
}
