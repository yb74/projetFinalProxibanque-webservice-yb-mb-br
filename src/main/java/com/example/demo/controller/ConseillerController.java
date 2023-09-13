package com.example.demo.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import com.example.demo.exception.GeneralException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

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
	Optional<ConseillerDTO> getConseillerById(@PathVariable Long id) throws GeneralException {
		return conseillerService.getConseillerById(id);
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
	ResponseEntity<String> deleteConseiller(@PathVariable Long id) throws GeneralException {
		conseillerService.deleteConseiller(id);
		return new ResponseEntity<>("Conseiller with ID : " + id + " deleted !", HttpStatus.OK);
	}

	// Exception handler for GeneralException
	@ExceptionHandler(GeneralException.class)
	public ResponseEntity<String> handleGeneralException(GeneralException ex) {
		return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
	}

	// Method that handles validation exceptions
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public Map<String, String> handleValidationExceptions(MethodArgumentNotValidException ex) {
		HashMap<String, String> errors = new HashMap<>();
		ex.getBindingResult().getAllErrors().forEach(e -> {
			String fieldName = ((FieldError) e).getField();
			String errorMessage = e.getDefaultMessage();

			errors.put(fieldName, errorMessage);
		});

		return errors;
	}
}
