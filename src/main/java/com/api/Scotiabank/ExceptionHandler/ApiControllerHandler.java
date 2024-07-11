package com.api.Scotiabank.ExceptionHandler;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;

@RestControllerAdvice
@Slf4j
public class ApiControllerHandler {

    @ExceptionHandler(value = ErrorCodedException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public Mono<ModelError> handleErrorCodedException(ErrorCodedException exception){
        log.error("ErrorCodedException : "+ exception.getMessage());
        return Mono.just(new ModelError(exception.getStatus(),exception.getCode(), exception.getMessage()));
    }
}
