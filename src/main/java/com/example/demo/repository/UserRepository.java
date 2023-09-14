package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import com.example.demo.model.UserModel;

public interface UserRepository extends JpaRepository<UserModel, Long> {
	
	Boolean existsByUsernameAndPassword(@Param("username") String username, @Param("password") String password);
	UserModel findByUsernameAndPassword(@Param("username") String username, @Param("password") String password);
}
