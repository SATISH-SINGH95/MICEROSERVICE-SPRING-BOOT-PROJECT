package com.apigateway.APIGATEWAY.entity;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.util.StringUtils;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class UserInfo implements UserDetails{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userInfoId;

    private String username;

    private String password;

    private String authority;

    private String email;

    private String phoneNumber;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if(StringUtils.hasText(authority)){
            return Arrays.stream(authority.split(",")).map(SimpleGrantedAuthority::new).collect(Collectors.toList());
        }
        return Collections.EMPTY_LIST;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        // you can write logic that when last login is more tha 6 month then credential will be expired.
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public String getUsername(){
        return username;
    }

    @Override
    public String getPassword(){
        return password;
    }
}
