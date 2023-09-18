package com.example.demo.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dto.CompteCourantDTO;
import com.example.demo.exception.GeneralException;
import com.example.demo.mapper.CompteCourantMapper;
import com.example.demo.model.Carte;
import com.example.demo.model.Client;
import com.example.demo.model.CompteCourant;
import com.example.demo.repository.ClientRepository;
import com.example.demo.repository.CompteCourantRepository;

import jakarta.transaction.Transactional;

@Service
public class CompteCourantServiceImpl implements CompteCourantService {
	private static final Logger LOG = LoggerFactory.getLogger(CompteCourant.class);

	@Autowired
	private CompteCourantRepository compteRepository;

	@Autowired
	private ClientRepository clientRepository;

	@Autowired
	private CompteCourantMapper compteCourantMapper;

	@Autowired
	private CompteCourantMapper mapper;

	public List<CompteCourantDTO> getAllCompte() {
		List<CompteCourant> compteCourants = compteRepository.findAll();
		return compteCourants.stream().map(compteCourant -> compteCourantMapper.toDto(compteCourant))
				.collect(Collectors.toList());
	}

	@Override
	public CompteCourantDTO getCompteById(Long compteId) throws GeneralException {
		CompteCourant compteCourant = compteRepository.findById(compteId)
				.orElseThrow(() -> new GeneralException("Compte not found with ID: " + compteId));

		return compteCourantMapper.toDto(compteCourant);
	}

	@Override
	public CompteCourantDTO createCompte(CompteCourantDTO compteDTO) {
		CompteCourant compteCourant = compteCourantMapper.toCompteCourant(compteDTO);
		compteCourant = compteRepository.save(compteCourant);
		return compteCourantMapper.toDto(compteCourant);
	}

	@Override
	public CompteCourantDTO updateCompte(Long compteId, CompteCourantDTO compteDto) throws GeneralException {
		CompteCourant existingCompte = compteRepository.findById(compteId)
				.orElseThrow(() -> new GeneralException("Compte Courant with ID " + compteId + " not found"));

		// Check if solde is provided in the request before updating (can't check if
		// solde is not null as it's a primitive double and not Double)
		if (compteDto.getBalance() != 0.0) {
			existingCompte.setBalance(compteDto.getBalance());
		}

		CompteCourant updatedCompte = compteRepository.save(existingCompte);
		return compteCourantMapper.toDto(updatedCompte);
	}

	@Override
	@Transactional
	public void deleteCompte(Long compteId) throws GeneralException {
		CompteCourant compteCourant = compteRepository.findById(compteId)
				.orElseThrow(() -> new GeneralException("CompteCourant not found with ID: " + compteId));

		// if the account to delete is found, we set the CompteCourant of the associated
		// Client to null before deletion to avoid constraint to block deletion in the
		// DB
		Client associatedClient = compteCourant.getClient();
		if (associatedClient != null)
			associatedClient.setCompteCourant(null);

		LOG.info("Deleting account with ID: " + compteId);
		compteRepository.deleteById(compteId);
		LOG.info("Account deleted successfully.");
	}

	@Override
	public void addCarte(CompteCourant comptec, Carte carte) {
		comptec.setCarte(carte);
		compteRepository.save(comptec);
	}

	@Override
	public void removeCarte(CompteCourant comptec, Carte carte) {
		comptec.setCarte(null);
		compteRepository.save(comptec);
	}

//    @Override
//    public AuditDTO doAudit() {
//        List<CompteCourantDTO> compteEpargneDTOs = compteRepository.findAll().stream()
//                .filter(compte -> compte.getClass() == CompteCourant.class && compte.getSolde() < 7100)
//                .map(compte -> entityToDto((CompteCourant) compte))
//                .collect(Collectors.toList());
//
//        double sommeAudit = compteEpargneDTOs.stream()
//                .mapToDouble(CompteCourantDTO::getSolde)
//                .sum();
//
//        return new AuditDTO(sommeAudit);
//    }

	@Override
	public CompteCourantDTO createCompteWithClientAndCarte(CompteCourantDTO CompteCourantDTOCreated)
			throws GeneralException {
		CompteCourantDTO compteDto = new CompteCourantDTO(0);
		compteDto.setTypeDeCarte(Carte.TypeDeCarte.VISA_ELECTRON);
		Long clientId = CompteCourantDTOCreated.getClientId();

		Client client = clientRepository.findById(clientId)
				.orElseThrow(() -> new GeneralException("Client with ID " + clientId + " not found"));

		CompteCourant compteCourant = compteCourantMapper.toCompteCourant(compteDto); // Convert DTO to entity
		compteCourant.setClient(client);
		client.setCompteCourant(compteCourant);

		Carte carte = new Carte(Carte.TypeDeCarte.VISA_ELECTRON);
		compteCourant.setCarte(carte);
		carte.setCompte(compteCourant);

		CompteCourant savedCompte = compteRepository.save(compteCourant); // Save the entity
		return compteCourantMapper.toDto(savedCompte); // Convert the saved entity to DTO
	}

	@Override
	public Optional<CompteCourant> getCompteCourantById(Long compteId) {
		if (compteId != null) {
			return compteRepository.findById(compteId);
		} else {
			return Optional.empty();
		}
	}

	@Override
	public Optional<CompteCourantDTO> getCompteByAccountNumber(String accountNumber) {
		CompteCourant existingCompteCourant = compteRepository.findByAccountNumber(accountNumber);
		return Optional.of(mapper.toDto(existingCompteCourant));
	}

	@Override
	public Optional<CompteCourantDTO> getCompteByClientId(Long clientId) {
		Optional<CompteCourant> compteCourantOptional = Optional.of(compteRepository.findByClient_Id(clientId));
		if (compteCourantOptional.isPresent()) {
			CompteCourant existingCompteCourant = compteCourantOptional.get();
			CompteCourantDTO compteCourantDTO = mapper.toDto(existingCompteCourant);
			return Optional.of(compteCourantDTO);
		} else {
			return Optional.empty();
		}
	}
}
