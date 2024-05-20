package com.user.exception;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import org.springframework.beans.TypeMismatchException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import com.user.constants.UserConstants;

@RestControllerAdvice
public class GlobalExceptionhandler{

    @Autowired
    private MessageSource messageSource;

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<ErrorResponse> entityNotFoundExceptionHandlerMethod(EntityNotFoundException ex){
        ErrorResponse errorResponse = new ErrorResponse(ex.getMessage(), ex.getStatus(), LocalDateTime.now());
        return new ResponseEntity<>(errorResponse, ex.getStatus());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Object> methodArgumentNotValidExceptionMethod(MethodArgumentNotValidException ex){
        // Map<Object, String> errorMap = new HashMap<>();

        // ex.getBindingResult().getFieldErrors().forEach(error ->{
        //     errorMap.put(error.getField(), error.getDefaultMessage());
        // });

        ErrorResponse errorResponse = null;
        ObjectError errorMessage = ex.getBindingResult().getFieldError();
        List<String> errorMessageList = new ArrayList<>();
        for(ObjectError error : ex.getBindingResult().getAllErrors()){
            errorMessageList.add(error.getDefaultMessage());
        }

        if(errorMessageList.size() > 1){
            errorResponse = new ErrorResponse(errorMessageList.toString(), HttpStatus.BAD_REQUEST, LocalDateTime.now());
        }
        else if(errorMessageList != null){
            errorResponse = new ErrorResponse(errorMessage.getDefaultMessage(), HttpStatus.BAD_REQUEST, LocalDateTime.now());
        }
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ServiceUnavailableException.class)
    public ResponseEntity<ErrorResponse> serviceUnavailableExceptionHandlerMethod(ServiceUnavailableException ex){
        ErrorResponse errorResponse = new ErrorResponse(ex.getMessage(), ex.getStatus(), LocalDateTime.now());
        return new ResponseEntity<>(errorResponse, ex.getStatus());
    }

    @ExceptionHandler(ServiceException.class)
    public ResponseEntity<ErrorResponse> serviceExceptionHandlerMethod(ServiceException ex){
        ErrorResponse errorResponse = new ErrorResponse(ex.getMessage(), ex.getStatus(), LocalDateTime.now());
        return new ResponseEntity<>(errorResponse, ex.getStatus());
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleInternalServerError(Exception ex) {
        ErrorResponse errorResponse = new ErrorResponse(messageSource.getMessage(UserConstants.BAD_REQUEST, null, Locale.getDefault()), HttpStatus.BAD_REQUEST, LocalDateTime.now());
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<Object> handleTypeMismatch(TypeMismatchException ex, HttpHeaders headers,
            HttpStatus status, WebRequest request) {
        ErrorResponse errorResponse = new ErrorResponse("Invalid data format", HttpStatus.BAD_REQUEST, LocalDateTime.now());
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

}
