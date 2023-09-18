package com.example.demo.service;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dto.ClientDTO;
import com.example.demo.dto.ConseillerDTO;
import com.example.demo.exception.GeneralException;
import com.example.demo.mapper.ClientMapper;
import com.example.demo.model.Client;
import com.example.demo.model.CompteCourant;
import com.example.demo.model.CompteEpargne;
import com.example.demo.model.Conseiller;
import com.example.demo.repository.ClientRepository;
import com.example.demo.repository.ConseillerRepository;

import jakarta.validation.Valid;

@Service
public class ClientServiceImpl implements ClientService {

	private final ClientRepository clientRepository;
	private final ConseillerRepository conseillerRepository;

	@Autowired
	private ClientMapper mapper;

	public ClientServiceImpl(ClientRepository clientRepository, ConseillerRepository conseillerRepository) {
		this.clientRepository = clientRepository;
		this.conseillerRepository = conseillerRepository;
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
	public ClientDTO createClient(ClientDTO clientDTO) throws GeneralException {
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

	@Override
	public List<ClientDTO> getClientsByConseiller(Optional<ConseillerDTO> conseillerDTO) throws GeneralException {
		Long conseillerId = conseillerDTO.orElseThrow(() -> new GeneralException("Conseiller not provided")).getId();

		Conseiller conseiller = conseillerRepository.findById(conseillerId)
				.orElseThrow(() -> new GeneralException("Conseiller not found with ID: " + conseillerId));

		Set<Client> clients = conseiller.getClients();
		if (clients.isEmpty()) {
			throw new GeneralException("No clients found for Conseiller with ID: " + conseillerId);
		}

		// Using the mapping methods to convert Client entities to ClientDTO objects
		List<ClientDTO> clientDTOs = clients.stream().map(client -> mapper.toDto(client)).collect(Collectors.toList());

		return clientDTOs;
	}
	
	@Override
	public ClientDTO createClientWithConseiller(ClientDTO clientDto, Long conseillerId) throws GeneralException {
		Conseiller conseiller = conseillerRepository.findById(conseillerId)
				.orElseThrow(() -> new GeneralException("Conseiller with ID " + conseillerId + " not found"));

		// Check if the Conseiller already has 10 clients
		if (conseiller.getClients().size() > 9) {
			// Find an alternative Conseiller with fewer clients
			List<Conseiller> alternativeConseillers = conseillerRepository.findByClientsSizeLessThan(10);

			// If no alternative Conseiller found, throw a custom exception
			if (alternativeConseillers.isEmpty()) {
				throw new GeneralException("No free alternative Conseiller found with fewer clients.");
			}

			// Choose the first alternative Conseiller
			conseiller = alternativeConseillers.get(0);
		}

		Client client = mapper.toClient(clientDto);
		client.setConseiller(conseiller);
		client = clientRepository.save(client);

		return mapper.toDto(client);
	}

	@Override
	public ClientDTO createClientWithConseillerWithCompteCcCp(@Valid ClientDTO clientDto, Long conseillerId) throws GeneralException {
		Conseiller conseiller = conseillerRepository.findById(conseillerId)
				.orElseThrow(() -> new GeneralException("Conseiller with ID " + conseillerId + " not found"));
		;
		clientDto.setConseillerId(conseiller.getId());
		clientDto.setCompteCourant(new CompteCourant(0.));
		clientDto.setCompteEpargne(new CompteEpargne(0.));
		Client client = mapper.toClient(clientDto);
		client = clientRepository.save(client);
		clientDto = mapper.toDto(client);
		return clientDto;
	}

	@Override
	public Set<Long> getClientsIdsByConseillerId(Long conseillerId) {
		return clientRepository.findIdsByConseillerId(conseillerId);
	}

}
