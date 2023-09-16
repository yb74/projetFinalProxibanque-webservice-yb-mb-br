package com.example.demo.mapper;

import org.springframework.stereotype.Component;

import com.example.demo.dto.ConseillerDTO;
import com.example.demo.model.Conseiller;

@Component
public class ConseillerMapper {

	public ConseillerDTO ToDto(Conseiller c) {
		return new ConseillerDTO(c.getId(), c.getName(), c.getFirstname(), c.getUsername(), c.getPassword(),
				c.getClients());
	}

	public Conseiller toConseiller(ConseillerDTO c) {
		return new Conseiller(c.getId(), c.getName(), c.getFirstname(),c.getUsername(),c.getPassword(), c.getClients());
	}
}
