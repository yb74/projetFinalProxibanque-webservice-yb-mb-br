package com.example.demo.repository;

import org.springframework.stereotype.Component;

import com.example.demo.model.Client;
import com.example.demo.model.Consoler;

import jakarta.annotation.PostConstruct;

@Component
public class DBInit {
	private ConsolerRepository consolerRepository;

	public DBInit(ConsolerRepository consolerRepository) {
		this.consolerRepository = consolerRepository;
	}

	@PostConstruct
	public void start() {
		Client client1 = new Client("Pierre", "Didier", "52 rue de Saintonge", 75003, "Paris", "0656434568");
		Client client2 = new Client("Pascal", "Claudine", "2 rue du Général Blaise", 75003, "Paris", "0656435413");
		Client client3 = new Client("Eliane", "Cécile", "52 Avenue de Saint-Ouen", 75017, "Paris", "0633434522");

		Consoler consoler1 = new Consoler("Delphine", "Joelle");
		Consoler consoler2 = new Consoler("René2", "Olivier");
		Consoler consoler3 = new Consoler("seul", "minté");

		consoler1.addClient(client1);
		consoler2.addClient(client2);
		consoler3.addClient(client3);

		consolerRepository.save(consoler1);
		consolerRepository.save(consoler2);
		consolerRepository.save(consoler3);

	}

}
