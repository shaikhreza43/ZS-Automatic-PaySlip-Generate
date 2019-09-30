package com.zs.apsg.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.zs.apsg.dto.LoginDto;
import com.zs.apsg.dto.UserDto;

@Repository
public interface UserRepository extends JpaRepository<UserDto, Long> {

	@Query(value = "select user from UserDto user where user.id=1", nativeQuery = false)
	public UserDto fetchUserCredentials(LoginDto login);
}
