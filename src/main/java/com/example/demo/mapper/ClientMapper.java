package com.example.demo.mapper;

import org.springframework.stereotype.Component;

import com.example.demo.dto.ClientDTO;
import com.example.demo.model.Client;

@Component
public class ClientMapper {

	public ClientDTO toDto(Client c) {
		return new ClientDTO(c.getId(), c.getName(), c.getFirstName(), c.getAdress(), c.getZipCode(), c.getCity(),
				c.getPhoneNumber(), c.getConsoler());
	}

	public Client toClient(ClientDTO clientDTO) {
		return new Client(clientDTO.getId(), clientDTO.getName(), clientDTO.getFirstname(), clientDTO.getAdress(),
				clientDTO.getZipCode(), clientDTO.getCity(), clientDTO.getPhoneNumber(), clientDTO.getConsoler());
	}
}
