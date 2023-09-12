package com.example.demo.mapper;

import com.example.demo.dto.ConsolerDTO;
import com.example.demo.model.Consoler;

public class ConsolerMapper {
	public ConsolerDTO ToDto(Consoler c) {
		return new ConsolerDTO(c.getId(), c.getName(), c.getFirstName(), c.getClients());
	}

	public Consoler toConsoler(ConsolerDTO c) {
		return new Consoler(c.getId(), c.getName(), c.getFirstName(), c.getClients());
	}
}
