package com.address.exception;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;

import lombok.Data;

@Data
public class BadRequestException extends RuntimeException{

    private String message;
    private LocalDateTime time;
    private HttpStatus status;

    public BadRequestException(){
        super();
        this.time = LocalDateTime.now();
    }

    public BadRequestException(String message, HttpStatus status){
        this.message = message;
        this.status = status;
    }

}
