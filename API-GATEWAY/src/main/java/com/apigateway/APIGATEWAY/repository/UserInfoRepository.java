package com.apigateway.APIGATEWAY.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.apigateway.APIGATEWAY.entity.UserInfo;

public interface UserInfoRepository extends JpaRepository<UserInfo, Long>{

    UserInfo findByUsername(String username);

    Optional<UserInfo> findByAuthority(String authority);

}
