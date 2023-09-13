package com.example.demo.service;

import java.util.List;


import java.util.Optional;

import com.example.demo.dto.ClientDTO;
import com.example.demo.model.Client;


public interface ClientService {
	List<Client> getAllClients();

	Optional<Client> getClientById(Long id);

	ClientDTO saveClient(ClientDTO clientDto);

	ClientDTO updateClient(Long id, ClientDTO clientDTO);

	void deleteClientById(Long id);
}
