package com.example.demo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.example.demo.model.Client;
import com.example.demo.model.Conseiller;
import com.example.demo.repository.ClientRepository;
import com.example.demo.service.ClientServiceImpl;

public class testGetAllClients_Success {
	
	
	@InjectMocks
	private ClientServiceImpl clientService;
	
	@Mock
	private ClientRepository clientRepository;
	
	
	@BeforeEach
	public void setUp() {
		MockitoAnnotations.openMocks(this);
	}
	 
	@Test
    public void testGetAllClients() {
		List<Client> mockClients = new ArrayList<>();
		mockClients.add(new Client(1L, "Nom", "Prénom", "Adresse", 12345, "Ville", "1234567890", new Conseiller()));		
			
		
		when(clientRepository.findAll()).thenReturn(mockClients);
		
		List<Client> clients = clientService.getAllClients();
		
		
		assertEquals(mockClients, clients);
	}}
