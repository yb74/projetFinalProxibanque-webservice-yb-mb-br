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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Client;
import com.example.demo.service.ClientService;
import com.example.demo.service.ConsolerService;

@RestController
@RequestMapping("/clients")
public class ClientController {

	private final ClientService clientService;
	private final ConsolerService consolerServer;

	public ClientController(ClientService clientService, ConsolerService consolerServer) {
		this.clientService = clientService;
		this.consolerServer = consolerServer;
	}

	@GetMapping
	ResponseEntity<List<Client>> getAllClients() {
		try {
			return new ResponseEntity<>(clientService.getAllClients(), HttpStatus.OK);
		} catch (RuntimeException e) {
			return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}

	@GetMapping("/{id}")
	Optional<Client> getClientById(@PathVariable Long id) {
		return clientService.getClientById(id);
	}

	@PostMapping
	public ResponseEntity<Client> createClient(@RequestBody Client client, @RequestParam Long conseillerId) {
		client.setConsoler(consolerServer.getConsolerById(conseillerId).get());
		clientService.saveClient(client);
		return new ResponseEntity<>(client, HttpStatus.CREATED);
	}

	@PutMapping("/{clientId}")
	public ResponseEntity<Client> updateClient(@PathVariable Long clientId, @RequestBody Client client) {
		clientService.saveClient(client);
		Optional<Client> existingClientOptional = clientService.getClientById(clientId);
		if (existingClientOptional.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND); // Return 404 Not Found if the client doesn't exist
		}
		Client existingClient = existingClientOptional.get();

		// Update the properties of the existing client with the new values
		existingClient.setName(client.getName());
		existingClient.setFirstName(client.getFirstName());
		existingClient.setAdress(client.getAdress());
		existingClient.setZipCode(client.getZipCode());
		existingClient.setCity(client.getCity());
		existingClient.setPhoneNumber(client.getPhoneNumber());

		Client updatedClient = clientService.updateClient(existingClient);

	    return new ResponseEntity<>(updatedClient, HttpStatus.OK); // Return 200 OK with the updated client
	}
	
	@DeleteMapping
	public ResponseEntity<Client> removeClient(@RequestParam Long clientId) {
		Optional<Client> clientOptional = clientService.getClientById(clientId);

	    if (clientOptional.isEmpty()) {
	        return new ResponseEntity<>(HttpStatus.NOT_FOUND); // Return 404 Not Found if the client doesn't exist
	    }

	    // The client exists, so proceed with the deletion
	    clientService.deleteClientById(clientId);

	    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

}
