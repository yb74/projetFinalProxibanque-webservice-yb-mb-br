package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import com.example.demo.dto.ConseillerDTO;
import com.example.demo.exception.GeneralException;

import com.example.demo.model.Client;
import com.example.demo.model.Conseiller;

public interface ConseillerService {
	List<Conseiller> getAllConseillers();

	Optional<ConseillerDTO> getConseillerById(Long id) throws GeneralException;
	
	ConseillerDTO createConseiller(ConseillerDTO conseillerDTO);

	ConseillerDTO updateConseiller(Long id, ConseillerDTO conseillerDTO) throws GeneralException;

	void deleteConseiller(Long id) throws GeneralException;

	Optional<Conseiller> getRealConseillerById(Long conseillerId) throws GeneralException;
	
	ConseillerDTO login(String username, String password) throws GeneralException;

//	ConseillerDTO getInfoForLogged(String username, String password) throws GeneralException;

}
