package com.microservice.architecture.orderService.exception;

import com.microservice.architecture.orderService.model.ErrorMessage;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(CustomException.class)
    public ResponseEntity<ErrorMessage> handleExceptionProduct(CustomException customException){

        return new ResponseEntity<>(new ErrorMessage().builder()
                .code(customException.getErrorCode())
                .message(customException.getMessage())
                .build(), HttpStatus.valueOf(customException.getStatus()));
    }

}
