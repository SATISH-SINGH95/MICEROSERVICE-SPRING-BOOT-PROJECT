package com.apigateway.APIGATEWAY.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
public class FallbackController {

    @GetMapping("/userServiceFallBack")
    public Mono<String> userServiceFallBackMethod(){
        return Mono.just("User service is down please try after sometime!!");
    }

    @GetMapping("/addressServiceFallBack")
    public Mono<String> addressServiceFallBackMethod(){
        return Mono.just("Address service is down please try after sometime!!");
    }

}
