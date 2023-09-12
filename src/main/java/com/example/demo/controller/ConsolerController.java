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

import com.example.demo.model.Consoler;
import com.example.demo.service.ConsolerService;

@RestController
@RequestMapping("/consolers")
public class ConsolerController {

	private final ConsolerService consolerService;

	public ConsolerController(ConsolerService consolerService) {
		this.consolerService = consolerService;
	}

	// Get all Consolers
	@GetMapping
	public ResponseEntity<List<Consoler>> getAllConsolers() {
		List<Consoler> consolers = consolerService.getAllConsolers();
		return new ResponseEntity<>(consolers, HttpStatus.OK);
	}

	// Get a Consoler by ID
	@GetMapping("/{consolerId}")
	public ResponseEntity<Consoler> getConsolerById(@PathVariable Long consolerId) {
		Optional<Consoler> consolerOptional = consolerService.getConsolerById(consolerId);

		if (consolerOptional.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<>(consolerOptional.get(), HttpStatus.OK);
	}

	// Create a new Consoler
	@PostMapping
	public ResponseEntity<Consoler> createConsoler(@RequestBody Consoler consoler) {
		Consoler createdConsoler = consolerService.saveConsoler(consoler);
		return new ResponseEntity<>(createdConsoler, HttpStatus.CREATED);
	}

	// Update a Consoler by ID
	@PutMapping("/{consolerId}")
	public ResponseEntity<Consoler> updateConsoler(@PathVariable Long consolerId, @RequestBody Consoler consoler) {
		Optional<Consoler> existingConsolerOptional = consolerService.getConsolerById(consolerId);

		if (existingConsolerOptional.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

		Consoler existingConsoler = existingConsolerOptional.get();
		// Update existingConsoler properties with values from consoler
		existingConsoler.setName(consoler.getName());
		existingConsoler.setFirstName(consoler.getFirstName());

		Consoler updatedConsoler = consolerService.updateConsoler(existingConsoler);
		return new ResponseEntity<>(updatedConsoler, HttpStatus.OK);
	}

	// Delete a Consoler by ID
	@DeleteMapping("/{consolerId}")
	public ResponseEntity<Void> deleteConsoler(@PathVariable Long consolerId) {
		Optional<Consoler> consolerOptional = consolerService.getConsolerById(consolerId);

		if (consolerOptional.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

		consolerService.deleteConsoler(consolerId);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
}
