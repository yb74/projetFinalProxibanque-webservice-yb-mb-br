package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import ch.qos.logback.core.net.server.Client;

public interface ClientService {
	List<Client> getAllClients();

	Optional<Client> getClientById(Long id);

	Client saveClient(Client client);

	Client updateClient(Client client);

	void deleteConsoler(Long id);
}
