package com.user.exception;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ServiceException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    private HttpStatus status;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
    private LocalDateTime timestamp;

    private String message;

    public ServiceException() {
        timestamp = LocalDateTime.now();
    }

    public ServiceException(HttpStatus status) {
        this();
        this.status = status;
    }

    public ServiceException(String message, HttpStatus status) {
        this();
        this.status = status;
        this.message = message;
    }

}