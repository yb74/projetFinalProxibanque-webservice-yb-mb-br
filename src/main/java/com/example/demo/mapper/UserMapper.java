package com.example.demo.mapper;

import com.example.demo.dto.UserDTO;
import com.example.demo.model.UserModel;

public class UserMapper {

	public UserDTO toDTO(UserModel user) {
		return new UserDTO(user.getId(), user.getUsername(), user.getPassword(), user.getRole(), user.getConseiller());

	}

	public UserModel toDTO(UserDTO userDTO) {
		return new UserModel(userDTO.getId(), userDTO.getUsername(), userDTO.getPassword(), userDTO.getRole(),
				userDTO.getConseiller());

	}

}
