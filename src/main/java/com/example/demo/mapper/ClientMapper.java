package com.example.demo.mapper;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.demo.dto.ClientDTO;
import com.example.demo.exception.GeneralException;
import com.example.demo.model.Client;
import com.example.demo.model.CompteCourant;
import com.example.demo.model.CompteEpargne;
import com.example.demo.model.Conseiller;
import com.example.demo.service.ConseillerService;

@Component
public class ClientMapper {

	@Autowired
	private ConseillerService conseillerService;

	public ClientDTO toDto(Client client) {
		if (client == null) {
			return null;
		}

		ClientDTO clientDTO = new ClientDTO();
		clientDTO.setId(client.getId());
		clientDTO.setName(client.getName());
		clientDTO.setFirstName(client.getFirstName());
		clientDTO.setAdress(client.getAdress());
		clientDTO.setZipCode(client.getZipCode());
		clientDTO.setCity(client.getCity());
		clientDTO.setPhoneNumber(client.getPhoneNumber());
		clientDTO.setConseillerId(client.getConseiller().getId());

		CompteCourant compteCourant = client.getCompteCourant();
		if (compteCourant != null) {
			clientDTO.setCompteCourant(compteCourant);
		}
		CompteEpargne compteEpargne = client.getCompteEpargne();
		if (compteEpargne != null) {
			clientDTO.setCompteEpargne(compteEpargne);
		}
		return clientDTO;
	}

	public Client toClient(ClientDTO clientDTO) throws GeneralException {
		Optional<Conseiller> conseiller = conseillerService.getRealConseillerById(clientDTO.getConseillerId());
		Optional<CompteCourant> compteCourant = Optional.of(clientDTO.getCompteCourant());
		Optional<CompteEpargne> compteEpargne = Optional.of(clientDTO.getCompteEpargne());

		// Vérifiez si les objets sont présents dans les Optionals
		if (!conseiller.isPresent()) {
			throw new GeneralException("Le conseiller des objets associé est null.");
		}
		if (!compteCourant.isPresent()) {
			throw new GeneralException(" Le compte courant associé est null.");
		}
		if (!compteEpargne.isPresent()) {
			throw new GeneralException("Le compte épargne associé est null.");
		}

		return new Client(clientDTO.getId(), clientDTO.getName(), clientDTO.getFirstName(), clientDTO.getAdress(),
				clientDTO.getZipCode(), clientDTO.getCity(), clientDTO.getPhoneNumber(), conseiller.get(),
				compteCourant.get(), // Obtenez la valeur à partir de l'Optional
				compteEpargne.get() // Obtenez la valeur à partir de l'Optional
		);
	}

}
