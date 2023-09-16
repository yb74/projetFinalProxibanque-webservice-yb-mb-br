package com.example.demo.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.ConseillerDTO;
import com.example.demo.exception.GeneralException;
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
		List<ConseillerDTO> conseillersDTO = conseillerService.getAllConseillers().stream().map(c -> mapper.ToDto(c))
				.collect(Collectors.toList());
		return new ResponseEntity<>(conseillersDTO, HttpStatus.OK);
	}

	// Get a Conseiller by ID
	@GetMapping("/{id}")
	Optional<ConseillerDTO> getConseillerById(@PathVariable Long id) throws GeneralException {
		return conseillerService.getConseillerById(id);
	}
	
	@GetMapping("/login")
	public ResponseEntity<ConseillerDTO> login(@RequestParam("username") String username,
			@RequestParam("password") String password) throws GeneralException {
		return new ResponseEntity<>(conseillerService.login(username, password), HttpStatus.OK);
	}

	@PostMapping
	public ResponseEntity<ConseillerDTO> createConseiller(@RequestBody ConseillerDTO conseillerDTO)
			throws GeneralException {
		Conseiller conseiller = mapper.toConseiller(conseillerDTO);
		ConseillerDTO createdConseiller = conseillerService.createConseiller(conseillerDTO);
		return new ResponseEntity<>(createdConseiller, HttpStatus.CREATED);
	}

	// Update a Conseiller by ID
	@PutMapping("/{id}")
	public ResponseEntity<ConseillerDTO> updateConseiller(@PathVariable Long id,
			@RequestBody ConseillerDTO conseillerDTO) throws GeneralException {
		ConseillerDTO updatedConseiller = conseillerService.updateConseiller(id, conseillerDTO);
		return new ResponseEntity<>(updatedConseiller, HttpStatus.OK);

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
