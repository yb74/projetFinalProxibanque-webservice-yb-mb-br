package com.example.demo.service;

import java.util.List;
import java.util.Set;

import ch.qos.logback.core.net.server.Client;

public interface ClientService {
	List<Client> getAllClients();

	Client getClientById(Long id);

	Set<Client> saveClient(Client client);

	Client updateClient(Client client);

	void deleteConsoler(Long id);
}
