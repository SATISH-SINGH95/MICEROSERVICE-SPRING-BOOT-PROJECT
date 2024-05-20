package com.address.aspect;

import java.util.Arrays;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;

@Aspect
@Component
@Slf4j
public class LoggingAspect {


    @Before("execution(* com.address.controller.*.*(..))")
    public void beforeControllerExecution(JoinPoint joinPoint){
        if(RequestContextHolder.getRequestAttributes() instanceof ServletRequestAttributes){
            HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
            String requestURI = request.getRequestURI();
            String httpMethod = request.getMethod();
            String parameters = Arrays.toString(joinPoint.getArgs());

            String methodname = joinPoint.getSignature().getName();
            String className = joinPoint.getTarget().getClass().getSimpleName();

            log.info("Before executing "+ className + "." + methodname);
            log.info("Request URI: "+ requestURI);
            log.info("HTTP Method: "+ httpMethod);
            log.info("Parameters: "+ parameters);
        } else{
            log.warn("request not found/not valid");
        }
    }

    @AfterReturning(pointcut = "execution(* com.address.controller.*.*(..))", returning = "result")
    public void afterControllerExecution(JoinPoint joinPoint, Object result) {
        String methodName = joinPoint.getSignature().getName();
        String className = joinPoint.getTarget().getClass().getSimpleName();
        log.info("After executing " + className + "." + methodName);
        log.info("Response: " + result);
    }

    @Around("execution(* com.address.controller.*.*(..))")
    public Object logExecutionTime(ProceedingJoinPoint joinPoint) throws Throwable {
        long startTime = System.currentTimeMillis();
        Object result = joinPoint.proceed();
        long endTime = System.currentTimeMillis();
        long executionTime = endTime - startTime;
        log.info(joinPoint.getSignature() + " executed in " + executionTime + "ms");
        return result;
    }

}
