package com.example.demo.repository;

import org.springframework.stereotype.Component;

import com.example.demo.model.Client;
import com.example.demo.model.Conseiller;

import jakarta.annotation.PostConstruct;

@Component
public class DBInit {
	private ConseillerRepository consillerRepository;

	public DBInit(ConseillerRepository consillerRepository) {
		this.consillerRepository = consillerRepository;
	}

	@PostConstruct
	public void start() {
		Client client1 = new Client("Pierre", "Didier", "52 rue de Saintonge", 75003, "Paris", "0656434568");
		Client client2 = new Client("Pascal", "Claudine", "2 rue du Général Blaise", 75003, "Paris", "0656435413");
		Client client3 = new Client("Eliane", "Cécile", "52 Avenue de Saint-Ouen", 75017, "Paris", "0633434522");

		Conseiller conseiller1 = new Conseiller("Delphine", "Joelle");
		Conseiller conseiller2 = new Conseiller("René2", "Olivier");
		Conseiller conseiller3 = new Conseiller("seul", "minté");

		conseiller1.addClient(client1);
		conseiller2.addClient(client2);
		conseiller3.addClient(client3);

		consillerRepository.save(conseiller1);
		consillerRepository.save(conseiller2);
		consillerRepository.save(conseiller3);

	}

}
