package com.user.client;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.user.configuration.feignConfig.FeignLogConfig;
import com.user.configuration.feignConfig.FeignSecurityConfig;
import com.user.exception.CustomFeignErrorDecoder;
import com.user.model.response.AddressResponse;

@FeignClient(value = "ADDRESS-API", url = "${address.api.url}", configuration = {
    FeignSecurityConfig.class,
    CustomFeignErrorDecoder.class, FeignLogConfig.class })
public interface AddressClient {

    @GetMapping("/adddress/userId/{userId}")
    public ResponseEntity<List<AddressResponse>> getAddressByUserId(@PathVariable Long userId);

}
