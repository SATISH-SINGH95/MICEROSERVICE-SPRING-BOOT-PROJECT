package com.address.exception;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;

import lombok.Data;

@Data
public class ErrorResponse {

    private String message;
    private LocalDateTime time;
    private HttpStatus status;

    public ErrorResponse(){
        super();
        this.time = LocalDateTime.now();
    }

    public ErrorResponse(String message, HttpStatus status, LocalDateTime time){
        this.message = message;
        this.status = status;
        this.time = time;
    }
}
