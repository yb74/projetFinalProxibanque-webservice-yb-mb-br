package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import com.example.demo.model.Consoler;

public interface ConsolerService {
	List<Consoler> getAllConsolers();

	Optional<Consoler> getConsolerById(Long id);

	Consoler saveConsoler(Consoler consoler);

	Consoler updateConsoler(Consoler conseiller);

	void deleteConsoler(Long id);

}
