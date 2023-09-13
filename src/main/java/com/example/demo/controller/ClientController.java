package com.example.demo.controller;

import java.util.List;
import java.util.Optional;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.ClientDTO;
import com.example.demo.mapper.ClientMapper;
import com.example.demo.model.Client;
import com.example.demo.service.ClientService;
import com.example.demo.service.ConseillerService;

@RestController
@RequestMapping("/clients")
public class ClientController {

	private final ClientService clientService;
	private final ConseillerService conseillerService;

	@Autowired
	private ClientMapper mapper;

	public ClientController(ClientService clientService, ConseillerService conseillerService) {
		this.clientService = clientService;
		this.conseillerService = conseillerService;
	}

	@GetMapping
	ResponseEntity<List<ClientDTO>> getAllClients() {
		try {
			return new ResponseEntity<>(clientService.getAllClients().stream().map(c -> mapper.toDto(c)).toList(),
					HttpStatus.OK);
		} catch (RuntimeException e) {
			return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}

	@GetMapping("/{id}")
	ResponseEntity<ClientDTO> getClientById(@PathVariable Long id) {
		try {
			Optional<Client> clientOptional = clientService.getClientById(id);

			if (clientOptional.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND); // Return 404 Not Found if the client doesn't exist
			}

			ClientDTO clientDTO = mapper.toDto(clientOptional.get());
			return new ResponseEntity<>(clientDTO, HttpStatus.OK);
		} catch (RuntimeException e) {
			return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}

	@PostMapping
	public ResponseEntity<ClientDTO> createClient(@RequestBody ClientDTO clientDTO, @RequestParam Long conseillerId) {
		clientDTO.setConseiller(conseillerService.getConseillerById(conseillerId).get());
		ClientDTO savedClient = clientService.saveClient(clientDTO);
		return new ResponseEntity<>(savedClient, HttpStatus.CREATED);
	}

	@PutMapping("/{clientId}")
	public ResponseEntity<ClientDTO> updateClient(@PathVariable Long clientId, @RequestBody ClientDTO clientDto) {
		try {
			ClientDTO updatedClient = clientService.updateClient(clientId, clientDto);
			return new ResponseEntity<>(updatedClient, HttpStatus.OK);
		} catch (RuntimeException e) {
			return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}

	@DeleteMapping("/{clientId}")
	public ResponseEntity<String> removeClient(@PathVariable Long clientId) {
		try {
			Optional<Client> clientOptional = clientService.getClientById(clientId);
			if (clientOptional.isEmpty()) {
				return new ResponseEntity<>("Client with ID : " + clientId + " was not found!", HttpStatus.NOT_FOUND);
			}
			clientService.deleteClientById(clientId);
			return new ResponseEntity<>("Client with ID : " + clientId + " deleted !", HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
