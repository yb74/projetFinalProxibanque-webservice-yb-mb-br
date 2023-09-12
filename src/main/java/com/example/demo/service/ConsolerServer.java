package com.example.demo.service;

import java.util.List;
import java.util.Set;

import com.example.demo.entity.Consoler;

public interface ConsolerServer {
	List<Consoler> getAllConsolers();

	Consoler getConsolerById(Long id);

	Set<Consoler> saveConsoler(Consoler consoler);

	Consoler updateConsoler(Consoler conseiller);

	void deleteConsoler(Long id);

}
