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
@RequestMapping("/conseillers")
public class ConseillerController {

	private final ConseillerService conseillerService;

	public ConseillerController(ConseillerService conseillerService) {
		this.conseillerService = conseillerService;
	}

	// Get all Conseillers
	@GetMapping
	public ResponseEntity<List<Conseiller>> getAllConseillers() {
		List<Conseiller> conseillers = conseillerService.getAllConseillers();
		return new ResponseEntity<>(conseillers, HttpStatus.OK);
	}

	// Get a Conseiller by ID
	@GetMapping("/{id}")
	public ResponseEntity<Conseiller> getConseillerById(@PathVariable Long id) {
		Optional<Conseiller> conseillerOptional = conseillerService.getConseillerById(id);

		if (conseillerOptional.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<>(conseillerOptional.get(), HttpStatus.OK);
	}

	// Create a new Conseiller
	@PostMapping
	public ResponseEntity<Conseiller> createConseiller(@RequestBody Conseiller conseiller) {
		Conseiller createdConsoler = conseillerService.saveConseiller(conseiller);
		return new ResponseEntity<>(createdConsoler, HttpStatus.CREATED);
	}

	// Update a Conseiller by ID
	@PutMapping("/{id}")
	public ResponseEntity<Conseiller> updateConseiller(@PathVariable Long id, @RequestBody Conseiller conseiller) {
		Optional<Conseiller> existingConseillerOptional = conseillerService.getConseillerById(id);

		if (existingConseillerOptional.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

		Conseiller existingConseiller = existingConseillerOptional.get();
		// Update existingConseiller properties with values from consoler
		existingConseiller.setName(conseiller.getName());
		existingConseiller.setFirstName(conseiller.getFirstName());

		Conseiller updatedConseiller = conseillerService.updateConseiller(existingConseiller);
		return new ResponseEntity<>(updatedConseiller, HttpStatus.OK);
	}

	// Delete a Conseiller by ID
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteConseiller(@PathVariable Long id) {
		Optional<Conseiller> ConseillerOptional = conseillerService.getConseillerById(id);

		if (ConseillerOptional.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

		conseillerService.deleteConseiller(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
}
