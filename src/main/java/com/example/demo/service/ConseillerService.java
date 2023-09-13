package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import com.example.demo.dto.ConseillerDTO;
import com.example.demo.model.Conseiller;

public interface ConseillerService {
	List<Conseiller> getAllConseillers();

	Optional<Conseiller> getConseillerById(Long id);

	ConseillerDTO createConseiller(ConseillerDTO conseillerDTO);

	ConseillerDTO updateConseiller(Long id ,ConseillerDTO conseillerDTO);

	void deleteConseiller(Long id);

}
