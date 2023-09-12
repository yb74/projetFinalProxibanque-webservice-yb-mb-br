package com.example.demo.service;

import java.util.List;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.demo.model.Consoler;
import com.example.demo.repository.ConsolerRepository;

@Service
public class ConsolerServiceImpl implements ConsolerService {

	private final ConsolerRepository consolerRepository;

	public ConsolerServiceImpl(ConsolerRepository consolerRepository) {
		this.consolerRepository = consolerRepository;
	}

	@Override
	public List<Consoler> getAllConsolers() {
		return (List<Consoler>) consolerRepository.findAll();
	}

	@Override
	public Optional<Consoler> getConsolerById(Long id) {
		return consolerRepository.findById(id);
	}

	@Override
	public Consoler saveConsoler(Consoler consoler) {
		return consolerRepository.save(consoler);
	}

	@Override
	public Consoler updateConsoler(Consoler conseiller) {
		return consolerRepository.save(conseiller);
	}

	@Override
	public void deleteConsoler(Long id) {
		consolerRepository.deleteById(id);
	}

}
