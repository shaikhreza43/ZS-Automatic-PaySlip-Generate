package com.zs.apsg.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zs.apsg.dto.LoginDto;
import com.zs.apsg.dto.UserDto;
import com.zs.apsg.repository.UserRepository;

@Service
public class ApsgService {

	@Autowired
	private UserRepository repository;

	// UserDto user=null;

	public UserDto saveUserData(UserDto user) {
		UserDto saved = repository.save(user);
		System.out.println("Db Response is " + saved);
		return saved;
	}

	public UserDto fetchUserData(LoginDto loginData) {

		UserDto fetchResult = repository.fetchUserCredentials(loginData);
		return fetchResult;
	}

}
