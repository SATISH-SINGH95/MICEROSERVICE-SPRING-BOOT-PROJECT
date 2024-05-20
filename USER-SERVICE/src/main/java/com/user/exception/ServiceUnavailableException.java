package com.user.exception;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ServiceUnavailableException extends RuntimeException{

    private String message;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
    private LocalDateTime time;
    private HttpStatus status;

    public ServiceUnavailableException(){
        super();
        this.time = LocalDateTime.now();
    }

    public ServiceUnavailableException(String message, HttpStatus status){
        this();
        this.message = message;
        this.status = status;
    }

}
