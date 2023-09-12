package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.demo.entity.Consoler;
import com.example.demo.repository.ConsolerRepository;

@Service
public class ConsolerServerImpl implements ConsolerServer {

	private final ConsolerRepository consolerRepository;

	public ConsolerServerImpl(ConsolerRepository consolerRepository) {
		this.consolerRepository = consolerRepository;
	}

	@Override
	public List<Consoler> getAllConsolers() {
		return consolerRepository.findAll();
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
