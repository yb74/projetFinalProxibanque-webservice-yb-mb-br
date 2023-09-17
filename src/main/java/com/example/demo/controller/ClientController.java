package com.example.demo.controller;

import java.util.HashMap;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import com.example.demo.dto.ConseillerDTO;
import com.example.demo.exception.GeneralException;
import com.example.demo.service.ConseillerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.origin.SystemEnvironmentOrigin;
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

import com.example.demo.dto.ClientDTO;
import com.example.demo.exception.GeneralException;
import com.example.demo.mapper.ClientMapper;
import com.example.demo.model.Client;
import com.example.demo.service.ClientService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/clients")
public class ClientController {

	private final ClientService clientService;

//	@Autowired
	private final ConseillerService conseillerService;

	@Autowired
	private ClientMapper mapper;

	public ClientController(ClientService clientService, ConseillerService conseillerService) {
		this.conseillerService = conseillerService;
		this.clientService = clientService;
	}

	@GetMapping
	ResponseEntity<List<ClientDTO>> getAllClients() throws GeneralException {
		return new ResponseEntity<>(clientService.getAllClients().stream().map(c -> mapper.toDto(c)).toList(),
				HttpStatus.OK);
	}

	@GetMapping("/{id}")
	ResponseEntity<ClientDTO> getClientById(@PathVariable Long id) throws GeneralException {
		Optional<Client> clientOptional = clientService.getClientById(id);

		if (clientOptional.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND); // Return 404 Not Found if the client doesn't exist
		}
		ClientDTO clientDTO = mapper.toDto(clientOptional.get());
		return new ResponseEntity<>(clientDTO, HttpStatus.OK);

	}

//	@PostMapping
//	public ResponseEntity<ClientDTO> createClient(@RequestBody ClientDTO clientDTO, @RequestParam Long conseillerId) {
//		clientDTO.setConseiller(conseillerService.getConseillerById(conseillerId).get());
//		ClientDTO savedClient = clientService.createClient(clientDTO);
//		return new ResponseEntity<>(savedClient, HttpStatus.CREATED);
//	}

	@GetMapping("/{conseillerId}/clients")
	public ResponseEntity<List<ClientDTO>> getClientsForConseiller(@PathVariable Long conseillerId) throws GeneralException {
		Optional<ConseillerDTO> conseiller = conseillerService.getConseillerById(conseillerId);

		List<ClientDTO> clients = clientService.getClientsByConseiller(conseiller);
		return new ResponseEntity<>(clients, HttpStatus.OK);
	}

	@PostMapping
	
	public ResponseEntity<ClientDTO> createClientWithConseiller(@Valid @RequestBody ClientDTO client,
			@RequestParam Long conseillerId) throws GeneralException {
		ClientDTO createdClient = clientService.createClientWithConseiller(client, conseillerId);
		return new ResponseEntity<>(createdClient, HttpStatus.CREATED);
	}
	
	@PostMapping("/create")
	public ResponseEntity<ClientDTO> createClientWithConseillerWithCompteCcCp(@Valid @RequestBody ClientDTO client,
			@RequestParam Long conseillerId) throws GeneralException {
		ClientDTO createdClient = clientService.createClientWithConseillerWithCompteCcCp(client, conseillerId);
		return new ResponseEntity<>(createdClient, HttpStatus.CREATED);
	}

	@PutMapping("/{clientId}")
	public ResponseEntity<ClientDTO> updateClient(@PathVariable Long clientId, @RequestBody ClientDTO clientDto)
			throws GeneralException {
		ClientDTO updatedClient = clientService.updateClient(clientId, clientDto);
		return new ResponseEntity<>(updatedClient, HttpStatus.OK);
	}

	@DeleteMapping("/{clientId}")
	public ResponseEntity<String> removeClient(@PathVariable Long clientId) throws GeneralException {
		Optional<Client> clientOptional = clientService.getClientById(clientId);
		if (clientOptional.isEmpty()) {
			return new ResponseEntity<>("Client with ID : " + clientId + " was not found!", HttpStatus.NOT_FOUND);
		}
		clientService.deleteClientById(clientId);
		return new ResponseEntity<>("Client with ID : " + clientId + " deleted !", HttpStatus.OK);

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
