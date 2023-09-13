package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import com.example.demo.model.Conseiller;

public interface ConseillerService {
	List<Conseiller> getAllConseillers();

	Optional<Conseiller> getConseillerById(Long id);

	Conseiller saveConseiller(Conseiller consoler);

	Conseiller updateConseiller(Conseiller conseiller);

	void deleteConseiller(Long id);

}
