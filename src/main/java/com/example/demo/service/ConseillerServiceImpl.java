package com.example.demo.service;

import java.util.List;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.demo.model.Conseiller;
import com.example.demo.repository.ConseillerRepository;

@Service
public class ConseillerServiceImpl implements ConseillerService {

	private final ConseillerRepository consillerRepository;

	public ConseillerServiceImpl(ConseillerRepository consillerRepository) {
		this.consillerRepository = consillerRepository;
	}

	@Override
	public List<Conseiller> getAllConseillers() {
		return (List<Conseiller>) consillerRepository.findAll();
	}

	@Override
	public Optional<Conseiller> getConseillerById(Long id) {
		return consillerRepository.findById(id);
	}

	@Override
	public Conseiller saveConseiller(Conseiller conseiller) {
		return consillerRepository.save(conseiller);
	}

	@Override
	public Conseiller updateConseiller(Conseiller conseiller) {
		return consillerRepository.save(conseiller);
	}

	@Override
	public void deleteConseiller(Long id) {
		consillerRepository.deleteById(id);
	}

}
