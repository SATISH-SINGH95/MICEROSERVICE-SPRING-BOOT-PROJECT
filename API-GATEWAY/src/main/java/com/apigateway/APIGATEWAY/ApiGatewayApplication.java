package com.apigateway.APIGATEWAY;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.apigateway.APIGATEWAY.entity.UserInfo;
import com.apigateway.APIGATEWAY.repository.UserInfoRepository;

@SpringBootApplication
@EnableDiscoveryClient
public class ApiGatewayApplication implements CommandLineRunner{

	public static void main(String[] args) {
		SpringApplication.run(ApiGatewayApplication.class, args);
	}

	@Autowired
	private UserInfoRepository userInfoRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Override
	public void run(String... args) throws Exception {
		Optional<UserInfo> admin = userInfoRepository.findByAuthority("ADMIN");
		if(admin.isPresent()){
			throw new RuntimeException("Admin already exist");
		}
		UserInfo user = new UserInfo();
		user.setUsername("admin");
		user.setPassword(passwordEncoder.encode("admin"));
		user.setEmail("admin@gmail.com");
		user.setPhoneNumber("999999999");
		user.setAuthority("ADMIN");
		userInfoRepository.save(user);
	}



}
