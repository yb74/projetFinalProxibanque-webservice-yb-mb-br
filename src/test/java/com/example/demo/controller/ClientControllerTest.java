package com.example.demo.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.example.demo.dto.ClientDTO;
import com.example.demo.exception.GeneralException;
import com.example.demo.model.CompteCourant;
import com.example.demo.model.CompteEpargne;
import com.example.demo.service.ClientService;
import com.example.demo.service.ConseillerService;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

public class ClientControllerTest {
	@BeforeEach
	void setUp() {
	    MockitoAnnotations.initMocks(this);
	}
	
	@Mock
	private ClientService clientService;

	@Mock
	private ConseillerService conseillerService;

	@InjectMocks
	private ClientController clientController;

	@Test
	void testCreateClientWithConseillerWithCompteCcCp() throws GeneralException {
	    Long conseillerId = 1L;
	    
	    // Créez un clientDTO fictif avec des données spécifiques pour votre test
	    ClientDTO clientDTO = new ClientDTO();
	    clientDTO.setName("John Doe");
	    clientDTO.setFirstName("Jane");
	    clientDTO.setAdress("123 Rue de Test");
	    clientDTO.setZipCode(12345);
	    clientDTO.setCity("TestCity");
	    clientDTO.setPhoneNumber("123-456-7890");
	    clientDTO.setConseillerId(conseillerId);
	    clientDTO.setCompteCourant(new CompteCourant(0));
	    clientDTO.setCompteEpargne(new CompteEpargne(0));
	    
	    // Affichez le clientDTO dans la console
	    System.out.println("ClientDTO: ");

	    System.out.println("ClientDTO: " + clientDTO);
	    
	    // Créez également un objet fictif pour la réponse attendue du service
	    ClientDTO createdClient = new ClientDTO();
	    createdClient.setId(1L); // Vous pouvez attribuer d'autres valeurs aux champs en fonction de votre logique
	    
	    // Configurez le mock du service pour retourner la réponse fictive
	    when(clientService.createClientWithConseillerWithCompteCcCp(clientDTO, conseillerId)).thenReturn(createdClient);

	    // Appelez la méthode du contrôleur que vous testez
	    ResponseEntity<ClientDTO> response = clientController.createClientWithConseillerWithCompteCcCp(clientDTO, conseillerId);

	    // Vérifiez que la réponse est correcte
	    assertEquals(HttpStatus.CREATED, response.getStatusCode());
	    assertNotNull(response.getBody());
	    // Vous pouvez également ajouter d'autres assertions pour vérifier que les données de la réponse sont correctes
	}


}
