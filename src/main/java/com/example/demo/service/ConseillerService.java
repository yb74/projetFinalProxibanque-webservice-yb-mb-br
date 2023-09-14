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

	String virementComptesCourants(double montant, Client clientEmetteur, Client clientRecepteur) throws GeneralException;

	String virementCourantEpargne(double montant, Client client) throws GeneralException;

	String virementEpargneCourant(double montant, Client client) throws GeneralException;

}
