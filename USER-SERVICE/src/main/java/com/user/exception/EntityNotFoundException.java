package com.user.exception;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

@Data
public class EntityNotFoundException extends RuntimeException{

    private String message;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
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
