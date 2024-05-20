package com.apigateway.APIGATEWAY.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.apigateway.APIGATEWAY.entity.UserInfo;
import com.apigateway.APIGATEWAY.repository.UserInfoRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService{

    @Autowired
    private UserInfoRepository userInfoRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserInfo userInfo = userInfoRepository.findByUsername(username);

        if(userInfo == null){
            throw new UsernameNotFoundException("User not found");
        }
        return userInfo;
    }

}
