package com.example.demo.mapper;

import org.springframework.stereotype.Component;

import com.example.demo.dto.UserDTO;
import com.example.demo.exception.GeneralException;
import com.example.demo.model.UserModel;
import com.example.demo.service.ConseillerService;

@Component
public class UserMapper {
	
	private ConseillerService conseillerService;

	public UserDTO toDTO(UserModel user) {
		return new UserDTO(user.getId(), user.getUsername(), user.getPassword(), user.getRole(), user.getConseiller().getId());

	}

	public UserModel toUserModel(UserDTO userDTO) throws GeneralException {
		return new UserModel(userDTO.getId(), userDTO.getUsername(), userDTO.getPassword(), userDTO.getRole(),
				conseillerService.getRealConseillerById(userDTO.getConseillerId()));

	}

}
