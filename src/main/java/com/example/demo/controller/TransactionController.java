package com.example.demo.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.exception.GeneralException;
import com.example.demo.model.Client;
import com.example.demo.model.Transaction;
import com.example.demo.service.ClientService;
import com.example.demo.service.ConseillerService;
import com.example.demo.service.TransactionService;

@RestController
@RequestMapping("/transactions")
public class TransactionController {
	private final TransactionService transactionService;
	private final ConseillerService conseillerService;
	private final ClientService clientService;

	public TransactionController(TransactionService transactionService, ConseillerService conseillerService,
			ClientService clientService) {
		this.transactionService = transactionService;
		this.conseillerService = conseillerService;
		this.clientService = clientService;
	}

	@GetMapping
	public ResponseEntity<List<Transaction>> getAllTransactions() {
		List<Transaction> transactions = transactionService.getAllTransactions();
		return ResponseEntity.ok(transactions);
	}

	@GetMapping("/{transactionId}")
	public ResponseEntity<Transaction> getTransaction(@PathVariable Long transactionId) {
		Transaction transaction = transactionService.getTransactionById(transactionId);
		if (transaction != null) {
			return ResponseEntity.ok(transaction);
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@PutMapping("/virement/comptes/courants")
	ResponseEntity<String> virementCompteCourantToCompteCourant(@RequestParam Long idEmetteur,
			@RequestParam Long idRecepteur, @RequestParam double montant) throws GeneralException {
		Optional<Client> optionalClientEmetteur = clientService.getClientById(idEmetteur);
		Optional<Client> optionalClientRecepteur = clientService.getClientById(idRecepteur);
		Client clientEmetteur = optionalClientEmetteur.get();
		Client clientRecepteur = optionalClientRecepteur.get();
		return new ResponseEntity<>(conseillerService.virementComptesCourants(montant, clientEmetteur, clientRecepteur),
				HttpStatus.OK);
	}

	@PostMapping
	public ResponseEntity<String> virement(@RequestParam Long idEmetteur, @RequestParam double montant,
			@RequestParam String typeVirement) throws GeneralException {
		try {
			switch (typeVirement) {
			case "compteCourantVersCompteEpargne": {
				Optional<Client> client = clientService.getClientById(idEmetteur);
				if (client.isEmpty()) {
					throw new GeneralException("Client non trouvé");
				}
				return ResponseEntity.ok(conseillerService.virementCourantEpargne(montant, client.get()));
			}
			case "compteEpargneVersCompteCourant": {
				Optional<Client> client = clientService.getClientById(idEmetteur);
				if (client.isEmpty()) {
					throw new GeneralException("Client non trouvé");
				}
				return ResponseEntity.ok(conseillerService.virementEpargneCourant(montant, client.get()));
			}
			default:
				throw new IllegalArgumentException("Type de virement non pris en charge: " + typeVirement);
			}
		} catch (GeneralException e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			return new ResponseEntity<>("Une erreur est survenue", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
