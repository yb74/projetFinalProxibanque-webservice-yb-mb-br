package com.example.demo;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import com.example.demo.dto.ClientDTO;
import com.example.demo.mapper.ClientMapper;
import com.example.demo.model.Client;
import com.example.demo.model.Conseiller;

public class clientMapper {
	
	private final ClientMapper clientMapper = new ClientMapper();
	
	 @Test
	    public void testToDto() {
	        // Préparation des données de test
	        Client client = new Client(1L, "Nom", "Prénom", "Adresse", 12345, "Ville", "1234567890", new Conseiller());

	        // Exécution de la méthode à tester
	        ClientDTO clientDTO = clientMapper.toDto(client);

	        // Vérification des résultats
	        assertEquals(client.getId(), clientDTO.getId());
	        assertEquals(client.getName(), clientDTO.getName());
	        assertEquals(client.getFirstName(), clientDTO.getFirstName());
	        assertEquals(client.getAdress(), clientDTO.getAdress());
	        assertEquals(client.getZipCode(), clientDTO.getZipCode());
	        assertEquals(client.getCity(), clientDTO.getCity());
	        assertEquals(client.getPhoneNumber(), clientDTO.getPhoneNumber());
	        assertEquals(client.getConseiller(), clientDTO.getConseiller());
	    }

}
