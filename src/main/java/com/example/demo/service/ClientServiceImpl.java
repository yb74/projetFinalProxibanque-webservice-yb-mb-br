package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dto.ClientDTO;
import com.example.demo.mapper.ClientMapper;
import com.example.demo.model.Client;
import com.example.demo.repository.ClientRepository;

@Service
public class ClientServiceImpl implements ClientService {

	private final ClientRepository clientRepository;

	@Autowired
	private ClientMapper mapper;

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
	public ClientDTO saveClient(ClientDTO clientDTO) {
		Client client = mapper.toClient(clientDTO);
		Client savedClient = clientRepository.save(client);
		return mapper.toDto(savedClient);
	}

	@Override
	public ClientDTO updateClient(Long id, ClientDTO clientDTO) {
		Optional<Client> existingClientOptional = clientRepository.findById(id);
		if (existingClientOptional.isEmpty()) {
//            throw new ResourceNotFoundException("Client not found with id: " + id);
			throw new RuntimeException("Client not found with id: " + id);
		}
		// Update the properties of the existing client with the new values
		Client existingClient = existingClientOptional.get();
		existingClient.setName(clientDTO.getName());
		existingClient.setFirstName(clientDTO.getFirstName());
		existingClient.setAdress(clientDTO.getAdress());
		existingClient.setZipCode(clientDTO.getZipCode());
		existingClient.setCity(clientDTO.getCity());
		existingClient.setPhoneNumber(clientDTO.getPhoneNumber());

		Client updatedClient = clientRepository.save(existingClient);
		return mapper.toDto(updatedClient);
	}

	@Override
	public void deleteClientById(Long id) {

		Optional<Client> clientOptional = clientRepository.findById(id);
		if (clientOptional.isEmpty()) {
			throw new RuntimeException("Client not found with id: " + id);
		}
		clientRepository.deleteById(id);
	}

}
