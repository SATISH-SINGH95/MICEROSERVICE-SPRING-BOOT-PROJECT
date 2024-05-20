package com.user.configuration.feignConfig;

import java.util.Optional;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.StringUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.user.constants.SecurityConstants;

import feign.RequestInterceptor;
import jakarta.servlet.http.HttpServletRequest;

@Configuration
public class FeignSecurityConfig {

    public static Optional<HttpServletRequest> getCurrentHttpRequest() {
        return Optional.ofNullable(RequestContextHolder.getRequestAttributes())
                .filter(ServletRequestAttributes.class::isInstance)
                .map(ServletRequestAttributes.class::cast)
                .map(ServletRequestAttributes::getRequest);
    }

    @Bean
    public RequestInterceptor requestInterceptor() {

        return template -> {
            final Optional<HttpServletRequest> optionalRequest = getCurrentHttpRequest();
            if (optionalRequest.isPresent()) {

                HttpServletRequest request = optionalRequest.get();
                String token = request.getHeader(SecurityConstants.AUTHORIZATION_HEADER);
                String tokenSource = request.getHeader(SecurityConstants.SOURCE_HEADER);
                if (!StringUtils.isEmpty(token)) {
                    template.header(SecurityConstants.AUTHORIZATION_HEADER, token);
                }
                if (!StringUtils.isEmpty(tokenSource)) {
                    template.header(SecurityConstants.SOURCE_HEADER, tokenSource);
                }
            }
        };
    }
}