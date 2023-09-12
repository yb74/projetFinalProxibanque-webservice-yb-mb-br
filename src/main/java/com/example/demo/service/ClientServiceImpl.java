package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.demo.repository.ClientRepository;

import ch.qos.logback.core.net.server.Client;

@Service
public class ClientServiceImpl implements ClientService {
	private final ClientRepository clientRepository;

	public ClientServiceImpl(ClientRepository clientRepository) {
		this.clientRepository = clientRepository;
	}

	@Override
	public List<Client> getAllClients() {
		return clientRepository.findAll();
	}

	@Override
	public Optional<Client> getClientById(Long id) {
		return clientRepository.findById(id);
	}

	@Override
	public Client saveClient(Client client) {
		return clientRepository.save(client);
	}

	@Override
	public Client updateClient(Client client) {
		return clientRepository.save(client);
	}

	@Override
	public void deleteConsoler(Long id) {
		clientRepository.deleteById(id);
	}

}
