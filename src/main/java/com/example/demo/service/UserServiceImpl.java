package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dto.ConseillerDTO;
import com.example.demo.exception.GeneralException;
import com.example.demo.mapper.ConseillerMapper;
import com.example.demo.model.Conseiller;
import com.example.demo.model.UserModel;
import com.example.demo.repository.ConseillerRepository;
import com.example.demo.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {

	private final UserRepository userRepository;
	private final ConseillerRepository conseillerRepository;
	
	@Autowired
	private ConseillerMapper mapper;
	
	public UserServiceImpl(UserRepository userRepository, ConseillerRepository conseillerRepository) {
		this.userRepository = userRepository;
		this.conseillerRepository = conseillerRepository;
	}
	
	@Override
	public UserModel createUser(UserModel user) {
		return userRepository.save(user);
	}

	@Override
	public List<UserModel> getAllUsers() {
		return userRepository.findAll();
	}

	@Override
	public Optional<UserModel> getUserById(Long id) {
		return userRepository.findById(id);
	}

	@Override
	public Boolean login(String username, String password) throws GeneralException {
		Boolean isUserExist = userRepository.existsByUsernameAndPassword(username, password);
		if (isUserExist != true) {
			throw new GeneralException("User not found");
		}
		return isUserExist;
	}

	@Override
    public UserModel getInfoForLogged(String username, String password) throws GeneralException {
		UserModel user = userRepository.findByUsernameAndPassword(username, password);
		if (user == null) {
			throw new GeneralException("User not found");
		}
        return user;
    }
	
	@Override
    public ConseillerDTO getConseillerAssociatedToUser(String username, String password) throws GeneralException {
		UserModel existingUser = getInfoForLogged(username, password);
        Conseiller conseiller = conseillerRepository.findConseillerByUserId(existingUser.getId());
        ConseillerDTO conseillerDto = mapper.ToDto(conseiller);
        return conseillerDto;
    }
}
