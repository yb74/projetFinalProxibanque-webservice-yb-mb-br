package com.example.demo.repository;

import java.util.List;

import org.springframework.stereotype.Component;

import com.example.demo.model.Carte;
import com.example.demo.model.Client;
import com.example.demo.model.CompteCourant;
import com.example.demo.model.CompteEpargne;
import com.example.demo.model.Conseiller;
import com.example.demo.model.Transaction;

import jakarta.annotation.PostConstruct;
import jakarta.transaction.Transactional;

@Component
public class DBInit {

	private final ConseillerRepository conseillerRepository;
	private final ClientRepository clientRepository;
	private final TransactionRepository transactionRepository;

	public DBInit(ConseillerRepository conseillerRepository, ClientRepository clientRepository,
			CompteCourantRepository compteCourantRepository, CompteEpargneRepository compteEpargneRepository,
			TransactionRepository transactionRepository) {
		this.conseillerRepository = conseillerRepository;
		this.clientRepository = clientRepository;
		this.transactionRepository = transactionRepository;
	}

	@PostConstruct
	@Transactional
	public void initializeDatabase() {
		// Create and associate accounts
		CompteCourant compteC1 = new CompteCourant(30000);
		CompteCourant compteC2 = new CompteCourant(1000);
		CompteCourant compteC3 = new CompteCourant(200);

		CompteCourant compteC4 = new CompteCourant(25696);
		CompteCourant compteC5 = new CompteCourant(3200);
		CompteCourant compteC6 = new CompteCourant(1240);
		CompteCourant compteC7 = new CompteCourant(520);

		CompteEpargne compteE1 = new CompteEpargne(60000);
		CompteEpargne compteE2 = new CompteEpargne(2000);
		CompteEpargne compteE3 = new CompteEpargne(4000);

		CompteEpargne compteE4 = new CompteEpargne(75000);
		CompteEpargne compteE5 = new CompteEpargne(32000);
		CompteEpargne compteE6 = new CompteEpargne(10000);
		CompteEpargne compteE7 = new CompteEpargne(25000);

		// Create cards
		Carte carte1 = new Carte(Carte.TypeDeCarte.VISA_ELECTRON, compteC1);
		Carte carte2 = new Carte(Carte.TypeDeCarte.VISA_PREMIER, compteC2);
		Carte carte3 = new Carte(Carte.TypeDeCarte.VISA_PREMIER, compteC3);

		Carte carte4 = new Carte(Carte.TypeDeCarte.VISA_PREMIER, compteC4);
		Carte carte5 = new Carte(Carte.TypeDeCarte.VISA_PREMIER, compteC5);
		Carte carte6 = new Carte(Carte.TypeDeCarte.VISA_PREMIER, compteC6);
		Carte carte7 = new Carte(Carte.TypeDeCarte.VISA_PREMIER, compteC7);

//        // Save accounts and cards
//        compteCourantRepository.saveAll(List.of(compteC1, compteC3, compteC4));
//        compteEpargneRepository.saveAll(List.of(compteE1, compteE2, compteE3));
//        carteRepository.saveAll(List.of(carte1, carte2, carte3));

		// Create clients and associate them with accounts
		Client client1 = new Client("Dupont", "Alice", compteC1, compteE1);
		Client client2 = new Client("Gautier", "Martin", compteC2, compteE2);
		Client client3 = new Client("Durant", "Bruno", compteC3, compteE3);

		Client client4 = new Client("Harry", "Kane", compteC4, compteE4);
		Client client5 = new Client("Jack", "Peterson", compteC5, compteE5);
		Client client6 = new Client("Martin", "Céline", compteC6, compteE6);
		Client client7 = new Client("Karine", "Smith", compteC7, compteE7);

		// Create conseillers and associate them with clients
		Conseiller conseiller1 = new Conseiller();
		conseiller1.setName("Smith");
		conseiller1.setFirstname("John");
		conseiller1.setUsername("Akira");
		conseiller1.setPassword("1234");
		conseiller1.addClient(client1);
		conseiller1.addClient(client2);

		conseiller1.addClient(client4);
		conseiller1.addClient(client5);
		conseiller1.addClient(client6);
		conseiller1.addClient(client7);

		Conseiller conseiller2 = new Conseiller();
		conseiller2.setName("Tom");
		conseiller2.setFirstname("Evans");
		conseiller2.setUsername("Akira2");
		conseiller2.setPassword("1234");
		conseiller2.addClient(client3);

		// Create transactions
		Transaction transaction1 = new Transaction();
		transaction1.setAmount(100.0);
		transaction1.setClientEmetteur(client1);
		transaction1.setCompteEmitteurId(2L);
		transaction1.setClientRecepteur(client2);
		transaction1.setCompteRecepteurId(1L);
		transaction1.setTypeDeVirement(Transaction.TypeDeVirement.COURANT_COURANT);

		Transaction transaction2 = new Transaction();
		transaction2.setAmount(500.0);
		transaction2.setClientEmetteur(client1);
		transaction2.setCompteEmitteurId(1L);
		transaction2.setClientRecepteur(client1);
		transaction2.setCompteRecepteurId(1L);
		transaction2.setTypeDeVirement(Transaction.TypeDeVirement.COURANT_EPARGNE);

		// Save conseillers
		conseillerRepository.saveAll(List.of(conseiller1, conseiller2));
		// Save clients
		clientRepository.saveAll(List.of(client1, client2, client3));

		// Save transactions
		transactionRepository.saveAll(List.of(transaction1, transaction2));
	}
}
