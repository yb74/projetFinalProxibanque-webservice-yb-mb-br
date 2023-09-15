package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.demo.model.Carte;
import com.example.demo.repository.CarteRepository;

@Service
public class CarteServiceImpl implements CarteService {

	private CarteRepository carteRepository;

	public CarteServiceImpl(CarteRepository carteRepository) {
		this.carteRepository = carteRepository;
	}

	@Override
	public List<Carte> getAllCartes() {
		return carteRepository.findAll();
	}

	@Override
	public Optional<Carte> getCarteById(Long id) {
		return carteRepository.findById(id);
	}

	@Override
	public boolean carteExistById(Long id) {
		return carteRepository.existsById(id);
	}

}
