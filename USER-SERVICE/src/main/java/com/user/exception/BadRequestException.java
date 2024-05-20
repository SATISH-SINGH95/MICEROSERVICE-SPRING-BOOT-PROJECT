package com.user.exception;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;

import org.springframework.http.HttpStatus;

import lombok.Data;

@Data
public class BadRequestException extends RuntimeException{

    private String message;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
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
