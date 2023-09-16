package com.example.demo.mapper;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import com.example.demo.dto.CompteEpargneDTO;
import com.example.demo.model.CompteEpargne;
import com.example.demo.service.ClientService;

@Component
public class CompteEpargneMapper {

	private final ClientService clientService;

	public CompteEpargneMapper(@Lazy ClientService clientService) {
		this.clientService = clientService;
	}

	public CompteEpargneDTO toDto(CompteEpargne c) {
		CompteEpargneDTO compteEpargneDTO = new CompteEpargneDTO(c.getId(), c.getAccountNumber(), c.getBalance(),
				c.getRemuneration(), c.getClient().getId(), c.getClient().getName(), c.getClient().getFirstName());
		return compteEpargneDTO;
	}

	public CompteEpargne toCompteEpargne(CompteEpargneDTO compteEpargneDTO) {
		CompteEpargne compteEpargne = new CompteEpargne(compteEpargneDTO.getAccountNumber(),
				compteEpargneDTO.getBalance(), compteEpargneDTO.getRemuneration(),
				clientService.getClientById(compteEpargneDTO.getClientId()).get());
		return compteEpargne;
	}

//    new CompteCourant(compteCourantDTO.getBalance(), compteCourantDTO.getAccountNumber(), compteCourantDTO.getOverdraft(),
//			carteService.getCarteById(compteCourantDTO.getCarteId()).get(), clientService.getClientById(compteCourantDTO.getClientId()).get());
//}
}
