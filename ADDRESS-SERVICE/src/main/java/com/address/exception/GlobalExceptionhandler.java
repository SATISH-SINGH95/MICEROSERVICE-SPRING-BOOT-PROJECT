package com.address.exception;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionhandler {

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<ErrorResponse> entityNotFoundExceptionHandlerMethod(EntityNotFoundException ex){
        ErrorResponse errorResponse = new ErrorResponse(ex.getMessage(), ex.getStatus(), LocalDateTime.now());
        return new ResponseEntity<>(errorResponse, ex.getStatus());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> entityNotFoundExceptionHandlerMethod(MethodArgumentNotValidException ex){
        Map<Object, String> errorMap = new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(error ->{
            errorMap.put(error.getField(), error.getDefaultMessage());
        });
        return new ResponseEntity<>(errorMap, HttpStatus.BAD_REQUEST);
    }

}
