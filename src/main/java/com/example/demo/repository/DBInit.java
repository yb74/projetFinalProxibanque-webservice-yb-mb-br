package com.example.demo.repository;

import com.example.demo.model.*;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Component;

import com.example.demo.model.Client;
import com.example.demo.model.Conseiller;

import jakarta.annotation.PostConstruct;

import java.util.List;

@Component
public class DBInit {
	private ConseillerRepository conseillerRepository;

	public DBInit(ConseillerRepository conseillerRepository) {
		this.conseillerRepository = conseillerRepository;
	}

//	@PostConstruct
//	@Transactional
//	public void start() {
//		// Create comptes and associate them with cards
//		CompteCourant compteC1 = new CompteCourant(30000);
////		compteC1.setCartes(carte);
////		compteC1.setCartes(carte2);
//		CompteEpargne compteE1 = new CompteEpargne(60000);
//		CompteCourant compteC3 = new CompteCourant(1000);
////		compteC3.setCartes(carte4);
//		CompteCourant compteC4 = new CompteCourant(200);
////		compteC4.setCartes(carte5);
//
//		CompteEpargne compteE2 = new CompteEpargne(2000);
//		CompteEpargne compteE3 = new CompteEpargne(4000);
//
//		//1 - Create cartes
//		Carte carte1 = new Carte(Carte.TypeDeCarte.VISA_ELECTRON,compteC1);
//		Carte carte2 = new Carte(Carte.TypeDeCarte.VISA_PREMIER,compteC3);
//		Carte carte3 = new Carte(Carte.TypeDeCarte.VISA_PREMIER,compteC4);
//
//
//		// Create clients and associate them with the comptes
//		Client client1 = new Client("Dupont","Alice",compteC1,compteE1);
//		Client client2 = new Client("Gautier","Martin",compteC3,null);
//		Client client3 = new Client("Durant","Bruno",compteC4,compteE2);
//		Client client4 = new Client("Rivière", "Sophie", null, compteE3);
//
//
//		// Create conseillers and associate them with the clients
//		Conseiller conseiller1 = new Conseiller();
//		conseiller1.setName("Smith");
//		conseiller1.setFirstName("John");
//		conseiller1.addClient(client1);
//		conseiller1.addClient(client2);
//
//		Conseiller conseiller2 = new Conseiller();
//		conseiller2.setName("Tom");
//		conseiller2.setFirstName("Evans");
//		conseiller2.addClient(client3);
//		conseiller2.addClient(client4);
//
//		conseillerRepository.saveAll(List.of(conseiller1, conseiller2));
//	}
}
