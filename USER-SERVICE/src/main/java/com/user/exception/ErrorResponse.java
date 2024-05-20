package com.user.exception;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

@Data
public class ErrorResponse {

    private String message;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
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
