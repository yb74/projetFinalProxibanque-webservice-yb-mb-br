package com.example.demo.mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.demo.dto.ClientDTO;
import com.example.demo.exception.GeneralException;
import com.example.demo.model.Client;
import com.example.demo.model.Conseiller;
import com.example.demo.service.CompteCourantService;
import com.example.demo.service.CompteEpargneService;
import com.example.demo.service.ConseillerService;

@Component
public class ClientMapper {

	@Autowired
	private ConseillerService conseillerService;

	@Autowired
	private CompteCourantService compteCourantService;

	@Autowired
	private CompteEpargneService compteEpargneService;

//	public ClientDTO toDto(Client c) {
//		return new ClientDTO(c.getId(), c.getName(), c.getFirstName(), c.getAdress(), c.getZipCode(), c.getCity(),
//				c.getPhoneNumber(), c.getConseiller().getId(), c.getCompteCourant().getId(),
//				c.getCompteEpargne().getId());
//	}

//	public Client toClient(ClientDTO clientDTO) throws GeneralException {
//		return new Client(clientDTO.getId(), clientDTO.getName(), clientDTO.getFirstName(), clientDTO.getAdress(),
//				clientDTO.getZipCode(), clientDTO.getCity(), clientDTO.getPhoneNumber(),
//				conseillerService.getRealConseillerById(clientDTO.getConseillerId()),
//				compteCourantService.getCompteCourantById(clientDTO.getCompteCourantId()).get(),
//				compteEpargneService.getCompteEpargneById(clientDTO.getCompteEpargneId()).get());
//	}
	public ClientDTO toDto(Client c) {
        return new ClientDTO(c.getId(), c.getName(), c.getFirstName(), c.getAdress(), c.getZipCode(), c.getCity(),
                c.getPhoneNumber(), c.getConseiller().getId(), c.getCompteCourant().getId(),
                c.getCompteEpargne().getId());
    }

    public Client toClient(ClientDTO clientDTO) throws GeneralException {
        Conseiller conseiller = conseillerService.getRealConseillerById(clientDTO.getConseillerId());

        return new Client(clientDTO.getId(), clientDTO.getName(), clientDTO.getFirstName(), clientDTO.getAdress(),
                clientDTO.getZipCode(), clientDTO.getCity(), clientDTO.getPhoneNumber(), conseiller);
    }
	
}
