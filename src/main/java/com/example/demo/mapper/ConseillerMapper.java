package com.example.demo.mapper;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import com.example.demo.dto.ConseillerDTO;
import com.example.demo.model.Conseiller;
import com.example.demo.service.ClientService;

@Component
public class ConseillerMapper {

	private final ClientService clientService;

	public ConseillerMapper(@Lazy ClientService clientService) {
		this.clientService = clientService;
	}

	public ConseillerDTO ToDto(Conseiller c) {
		return new ConseillerDTO(c.getId(), c.getName(), c.getFirstName(),
				clientService.getClientsIdsByConseillerId(c.getId()));
	}

	public Conseiller toConseiller(ConseillerDTO c) {
		return new Conseiller(c.getId(), c.getName(), c.getFirstName(), c.getClientsIds());
	}
}
