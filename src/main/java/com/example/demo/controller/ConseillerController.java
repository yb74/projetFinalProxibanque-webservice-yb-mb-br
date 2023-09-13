package com.example.demo.controller;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
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

import com.example.demo.dto.ClientDTO;
import com.example.demo.dto.ConseillerDTO;
import com.example.demo.mapper.ConseillerMapper;
import com.example.demo.model.Conseiller;
import com.example.demo.service.ConseillerService;

@RestController
@RequestMapping("/conseillers")
public class ConseillerController {

	private final ConseillerService conseillerService;

	@Autowired
	private ConseillerMapper mapper;

	public ConseillerController(ConseillerService conseillerService) {
		this.conseillerService = conseillerService;
	}

	// Get all Conseillers
	@GetMapping
	public ResponseEntity<List<ConseillerDTO>> getAllConseillers() {
		try {
			return new ResponseEntity<>(conseillerService.getAllConseillers().stream().map(c -> mapper.ToDto(c))
					.collect(Collectors.toList()), HttpStatus.OK);
		} catch (RuntimeException e) {
			return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}

	// Get a Conseiller by ID
	@GetMapping("/{id}")
	public ResponseEntity<ConseillerDTO> getConseillerById(@PathVariable Long id) {
		try {
			Optional<Conseiller> conseillerOptional = conseillerService.getConseillerById(id);
			if (conseillerOptional.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
			Conseiller conseiller = conseillerOptional.get();
			ConseillerDTO conseillerDTO = mapper.ToDto(conseiller);
			return new ResponseEntity<>(conseillerDTO, HttpStatus.OK);
		} catch (RuntimeException e) {
			return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}

	// Create a new Conseiller
	@PostMapping
	public ResponseEntity<ConseillerDTO> createConseiller(@RequestBody ConseillerDTO conseillerDTO) {
		Conseiller conseiller = mapper.toConseiller(conseillerDTO);
		ConseillerDTO createdConseiller = conseillerService.createConseiller(conseillerDTO);
		return new ResponseEntity<>(createdConseiller, HttpStatus.CREATED);
	}

	// Update a Conseiller by ID
	@PutMapping("/{id}")
	public ResponseEntity<ConseillerDTO> updateConseiller(@PathVariable Long id, @RequestBody ConseillerDTO conseillerDTO) {
		try {
			ConseillerDTO updatedConseiller = conseillerService.updateConseiller(id ,conseillerDTO);
			return new ResponseEntity<>(updatedConseiller, HttpStatus.OK);
		} catch (RuntimeException e) {
			return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
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
