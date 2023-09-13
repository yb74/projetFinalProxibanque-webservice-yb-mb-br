package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import com.example.demo.dto.ClientDTO;
import com.example.demo.dto.ConseillerDTO;
import com.example.demo.exception.GeneralException;
import com.example.demo.model.Client;

public interface ClientService {
	List<Client> getAllClients();

	List<ClientDTO> getClientsByConseiller(Optional<ConseillerDTO> conseiller) throws GeneralException;

	Optional<Client> getClientById(Long id);

	ClientDTO createClient(ClientDTO clientDto);

	ClientDTO updateClient(Long id, ClientDTO clientDTO);

	void deleteClientById(Long id);

	ClientDTO createClientWithConseiller(ClientDTO client, Long conseillerId) throws GeneralException;
}
