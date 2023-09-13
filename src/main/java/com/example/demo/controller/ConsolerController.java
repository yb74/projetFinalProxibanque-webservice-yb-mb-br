package com.example.demo.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Conseiller;
import com.example.demo.service.ConseillerService;

@RestController
@RequestMapping("/consolers")
public class ConsolerController {

	private final ConseillerService conseillerService;

	public ConsolerController(ConseillerService conseillerService) {
		this.conseillerService = conseillerService;
	}

	// Get all Consolers
	@GetMapping
	public ResponseEntity<List<Conseiller>> getAllConsolers() {
		List<Conseiller> consolers = conseillerService.getAllConseillers();
		return new ResponseEntity<>(consolers, HttpStatus.OK);
	}

	// Get a Consoler by ID
	@GetMapping("/{id}")
	public ResponseEntity<Conseiller> getConsolerById(@PathVariable Long id) {
		Optional<Conseiller> consolerOptional = conseillerService.getConseillerById(id);

		if (consolerOptional.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<>(consolerOptional.get(), HttpStatus.OK);
	}

	// Create a new Consoler
	@PostMapping
	public ResponseEntity<Conseiller> createConsoler(@RequestBody Conseiller conseiller) {
		Conseiller createdConsoler = conseillerService.saveConseiller(conseiller);
		return new ResponseEntity<>(createdConsoler, HttpStatus.CREATED);
	}

	// Update a Consoler by ID
	@PutMapping("/{id}")
	public ResponseEntity<Conseiller> updateConsoler(@PathVariable Long id, @RequestBody Conseiller conseiller) {
		Optional<Conseiller> existingConsolerOptional = conseillerService.getConseillerById(id);

		if (existingConsolerOptional.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

		Conseiller existingConsoler = existingConsolerOptional.get();
		// Update existingConsoler properties with values from consoler
		existingConsoler.setName(conseiller.getName());
		existingConsoler.setFirstName(conseiller.getFirstName());

		Conseiller updatedConsoler = conseillerService.updateConseiller(existingConsoler);
		return new ResponseEntity<>(updatedConsoler, HttpStatus.OK);
	}

	// Delete a Consoler by ID
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteConsoler(@PathVariable Long id) {
		Optional<Conseiller> consolerOptional = conseillerService.getConseillerById(id);

		if (consolerOptional.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

		conseillerService.deleteConseiller(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
}
