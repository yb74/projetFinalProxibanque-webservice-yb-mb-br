package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import com.example.demo.model.Carte;

public interface CarteService {

	List<Carte> getAllCartes();

	Optional<Carte> getCarteById(Long id);
	
	boolean carteExistById(Long id);

}
