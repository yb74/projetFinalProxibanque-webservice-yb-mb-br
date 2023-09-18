package com.example.demo.service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import com.example.demo.dto.ClientDTO;
import com.example.demo.dto.ConseillerDTO;
import com.example.demo.exception.GeneralException;
import com.example.demo.model.Client;

import jakarta.validation.Valid;

public interface ClientService {
	List<Client> getAllClients();

	List<ClientDTO> getClientsByConseiller(Optional<ConseillerDTO> conseiller) throws GeneralException;
	
	Set<Long> getClientsIdsByConseillerId(Long conseillerId);

	Optional<Client> getClientById(Long id);

	ClientDTO createClient(ClientDTO clientDto) throws GeneralException;

	ClientDTO updateClient(Long id, ClientDTO clientDTO);

	void deleteClientById(Long id);

	ClientDTO createClientWithConseiller(ClientDTO client, Long conseillerId) throws GeneralException;

	ClientDTO createClientWithConseillerWithCompteCcCp(@Valid ClientDTO client, Long conseillerId) throws GeneralException;

}
