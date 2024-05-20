package com.user.exception;

import org.springframework.http.HttpStatus;

import feign.Response;
import feign.codec.ErrorDecoder;

public class CustomFeignErrorDecoder implements ErrorDecoder {

    @Override
    public Exception decode(String methodKey, Response response) {
        switch (response.status()){
            case 400:
                return new BadRequestException(response.reason(), HttpStatus.BAD_REQUEST);
            case 404:
                return new EntityNotFoundException(response.reason(), HttpStatus.NOT_FOUND);
            case 503:
                return new ServiceUnavailableException(response.reason(), HttpStatus.SERVICE_UNAVAILABLE);
            default:
                return new ServiceException(response.reason(), HttpStatus.SERVICE_UNAVAILABLE);
        }
    }
}