package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import com.example.demo.dto.ConseillerDTO;
import com.example.demo.dto.UserDTO;
import com.example.demo.exception.GeneralException;
import com.example.demo.model.UserModel;

public interface UserService {

	List<UserModel> getAllUsers();

	Optional<UserModel> getUserById(Long id);

	UserModel createUser(UserModel user);
	
	Boolean login(String username, String password) throws GeneralException;

	UserModel getInfoForLogged(String username, String password) throws GeneralException;

	ConseillerDTO getConseillerAssociatedToUser(String username, String password) throws GeneralException;
	
}
