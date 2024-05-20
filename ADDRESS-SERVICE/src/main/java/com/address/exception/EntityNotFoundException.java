package com.address.exception;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;

import lombok.Data;

@Data
public class EntityNotFoundException extends RuntimeException{

    private String message;
    private LocalDateTime time;
    private HttpStatus status;

    public EntityNotFoundException(){
        super();
        this.time = LocalDateTime.now();
    }

    public EntityNotFoundException(String message, HttpStatus status){
        this.message = message;
        this.status = status;
    }
}
