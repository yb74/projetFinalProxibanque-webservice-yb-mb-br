package com.example.demo.mapper;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import com.example.demo.dto.CompteCourantDTO;
import com.example.demo.model.CompteCourant;
import com.example.demo.service.CarteService;
import com.example.demo.service.ClientService;

@Component
public class CompteCourantMapper {

	private final ClientService clientService;
	private CarteService carteService;

	public CompteCourantMapper(@Lazy ClientService clientService, CarteService carteService) {
		this.clientService = clientService;
		this.carteService = carteService;
	}

	public CompteCourantDTO toDto(CompteCourant c) {
		return new CompteCourantDTO(c.getId(), c.getAccountNumber(), c.getBalance(), c.getOverdraft(), c.getCarte().getId(), c.getClient().getId(),c.getClient().getFirstName(),c.getClient().getName() );
	}

	public CompteCourant toCompteCourant(CompteCourantDTO compteCourantDTO) {
		return new CompteCourant(compteCourantDTO.getBalance(), compteCourantDTO.getAccountNumber(), compteCourantDTO.getOverdraft(),
				carteService.getCarteById(compteCourantDTO.getCarteId()).get(), clientService.getClientById(compteCourantDTO.getClientId()).get());
	}
}
