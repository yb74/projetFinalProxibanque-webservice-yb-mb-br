package com.example.demo.mapper;

import com.example.demo.dto.ConseillerDTO;
import com.example.demo.model.Conseiller;

public class ConseillerrMapper {
	public ConseillerDTO ToDto(Conseiller c) {
		return new ConseillerDTO(c.getId(), c.getName(), c.getFirstName(), c.getClients());
	}

	public Conseiller toConsoler(ConseillerDTO c) {
		return new Conseiller(c.getId(), c.getName(), c.getFirstName(), c.getClients());
	}
}
