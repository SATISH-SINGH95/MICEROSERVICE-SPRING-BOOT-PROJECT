package com.apigateway.APIGATEWAY.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.jaas.JaasAuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;


@Component
public class AppAuthenticationProvider implements AuthenticationProvider{

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String username = authentication.getName();
        UserDetails userDetails = userDetailsService.loadUserByUsername(username);
        if(passwordEncoder.matches(authentication.getCredentials().toString(), userDetails.getPassword())){
            return new UsernamePasswordAuthenticationToken(userDetails.getUsername(), passwordEncoder.encode(userDetails.getPassword()), userDetails.getAuthorities());
        }else{
            throw new BadCredentialsException("Invalid Username or Password");
        }
    }

    @Override
    public boolean supports(Class<?> authentication) {
        if(UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication)){
            return true;
        }
        if(JaasAuthenticationProvider.class.isAssignableFrom(authentication)){
            return true;
        }
        return false;
    }



}
