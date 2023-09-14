package com.example.demo.service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import com.example.demo.exception.GeneralException;
import com.example.demo.model.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dto.ConseillerDTO;
import com.example.demo.mapper.ConseillerMapper;
import com.example.demo.model.CompteCourant;
import com.example.demo.model.Conseiller;
import com.example.demo.repository.ClientRepository;
import com.example.demo.repository.ConseillerRepository;

@Service
public class ConseillerServiceImpl implements ConseillerService {
	private final ConseillerRepository conseillerRepository;
	private final ClientRepository clientRepository;

	@Autowired
	private ConseillerMapper mapper;

	public ConseillerServiceImpl(ConseillerRepository conseillerRepository, ClientRepository clientRepository) {
		this.conseillerRepository = conseillerRepository;
		this.clientRepository = clientRepository;
	}

	@Override
	public List<Conseiller> getAllConseillers() {
		return (List<Conseiller>) conseillerRepository.findAll();
	}

	@Override
	public Optional<ConseillerDTO> getConseillerById(Long id) throws GeneralException {
		Optional<Conseiller> optionalConseiller = conseillerRepository.findById(id);

		if (optionalConseiller.isPresent()) {
			ConseillerDTO conseillerDto = mapper.ToDto(optionalConseiller.get());
			return Optional.of(conseillerDto);
		} else {
			throw new GeneralException("Conseiller not found with ID: " + id);
		}
	}

	@Override
	public ConseillerDTO createConseiller(ConseillerDTO conseillerDTO) {
		Conseiller conseiller = mapper.toConseiller(conseillerDTO);
		Conseiller savedConseiller = conseillerRepository.save(conseiller);
		return mapper.ToDto(savedConseiller);
	}

	@Override
	public ConseillerDTO updateConseiller(Long id, ConseillerDTO conseillerDTO) throws GeneralException {
		Optional<Conseiller> existingConseillerOptional = conseillerRepository.findById(id);

		if (existingConseillerOptional.isEmpty()) {
//            throw new ResourceNotFoundException("Conseiller not found with id: " + id);
			throw new GeneralException("Conseiller not found with id: " + id);
		}
		Conseiller existingConseiller = existingConseillerOptional.get();
		existingConseiller.setName(conseillerDTO.getName());
		existingConseiller.setFirstName(conseillerDTO.getFirstName());
		Conseiller updatedConseiller = conseillerRepository.save(existingConseiller);
		return mapper.ToDto(updatedConseiller);

	}

	@Override
	public void deleteConseiller(Long conseillerId) throws GeneralException {
		Conseiller conseiller = conseillerRepository.findById(conseillerId)
				.orElseThrow(() -> new GeneralException("Conseiller not found with ID: " + conseillerId));

		Set<Client> clients = conseiller.getClients();

		// Disassociate each Client from the Conseiller
		for (Client client : clients) {
			client.setConseiller(null);
		}

		// Delete the Conseiller
		conseillerRepository.delete(conseiller);
	}

	@Override
	public String virementComptesCourants(double montant, Client clientEmetteur, Client clientRecepteur) throws GeneralException {
		String messageReponse;
		if (montant > 0) {
			CompteCourant compteCourantEmetteur = clientEmetteur.getCompteCourant();
			CompteCourant compteCourantRecepteur = clientRecepteur.getCompteCourant();
			double soldeEmetteur = compteCourantEmetteur.getBalance();
			if (montant <= soldeEmetteur || soldeEmetteur + compteCourantEmetteur.getOverdraft() >= montant) {
				double nouveauSoldeEmetteur = soldeEmetteur - montant;
				compteCourantEmetteur.setBalance(nouveauSoldeEmetteur);
				double nouveauSoldeRecepteur = compteCourantRecepteur.getBalance() + montant;
				compteCourantRecepteur.setBalance(nouveauSoldeRecepteur);
				messageReponse = "Virement effectué avec succès !";
				clientRepository.save(clientEmetteur);
				clientRepository.save(clientRecepteur);
				return messageReponse;
			} else {
				messageReponse = "solde insuffisant";
				throw new GeneralException(messageReponse);
			}
		} else {
			messageReponse = "Le montant du virement doit être positif";
			throw new GeneralException(messageReponse);
		}
	}

	@Override
	public String virementCourantEpargne(double montant, Client client) throws GeneralException {
		String messageReponse;
		if (montant > 0) {
			double soldeEmetteur = client.getCompteCourant().getBalance();
			double nouveauSoldeEmetteur;
			double nouveauSoldeRecepteur;
			if (soldeEmetteur >= montant || soldeEmetteur + client.getCompteCourant().getOverdraft() >= montant) {
				nouveauSoldeEmetteur = soldeEmetteur - montant;
				client.getCompteCourant().setBalance(nouveauSoldeEmetteur);
				nouveauSoldeRecepteur = client.getCompteEpargne().getBalance() + montant;
				client.getCompteEpargne().setBalance(nouveauSoldeRecepteur);
				clientRepository.save(client);
				messageReponse = "Virement effectué avec succès !";
				return messageReponse;
			} else {
				messageReponse = "Solde insuffisant";
				throw new GeneralException(messageReponse);
			}

		} else {
			messageReponse = "Le montant du virement doit être positif";
			throw new GeneralException(messageReponse);
		}
	}

	@Override
	public String virementEpargneCourant(double montant, Client client) throws GeneralException {
		String messageReponse;
		if (montant > 0) {
			double soldeEpargne = client.getCompteEpargne().getBalance();
			if (montant <= soldeEpargne) {
				double nouveauSoldeEpargne = soldeEpargne - montant;
				client.getCompteEpargne().setBalance(nouveauSoldeEpargne);
				double nouveauSoldeCourant = client.getCompteCourant().getBalance() + montant;
				client.getCompteCourant().setBalance(nouveauSoldeCourant);
				clientRepository.save(client);
				messageReponse = "Virement effectué avec succès !";
				return messageReponse;
			} else {
				messageReponse = "Solde épargne insuffisant";
				throw new GeneralException(messageReponse);
			}
		} else {
			messageReponse = "Le montant du virement doit être positif";
			throw new GeneralException(messageReponse);
		}

	}

}
