package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.exception.GeneralException;
import com.example.demo.model.Client;
import com.example.demo.model.CompteCourant;
import com.example.demo.model.CompteEpargne;
import com.example.demo.model.Transaction;
import com.example.demo.model.Transaction.TypeDeVirement;
import com.example.demo.repository.ClientRepository;
import com.example.demo.repository.CompteCourantRepository;
import com.example.demo.repository.CompteEpargneRepository;
import com.example.demo.repository.TransactionRepository;

@Service
public class TransactionServiceImpl implements TransactionService {

	private final ClientRepository clientRepository;
	private final TransactionRepository transactionRepository;

	@Autowired
	private CompteCourantRepository compteCourantRepository;

	@Autowired
	private CompteEpargneRepository compteEpargneRepository;

	public TransactionServiceImpl(ClientRepository clientRepository, TransactionRepository transactionRepository) {
		this.clientRepository = clientRepository;
		this.transactionRepository = transactionRepository;
	}

	@Override
	public String virementComptesCourants(double montant, Long idEmetteur, Long idRecepteur) throws GeneralException {
		String messageReponse;
		if (montant > 0) {
			if (idEmetteur == idRecepteur) {
				messageReponse = "Les comptes de l'émetteur et du récepteur ne peuvent pas être les mêmes.";
				throw new GeneralException(messageReponse);
			}
			Optional<CompteCourant> optionalCompteCourantEmetteur = compteCourantRepository.findById(idEmetteur);
			Optional<CompteCourant> optionalCompteCourantRecepteur = compteCourantRepository.findById(idRecepteur);
			if (optionalCompteCourantEmetteur.isEmpty()) {
				messageReponse = "le compte courant émeteur n'existe pas";
				throw new GeneralException(messageReponse);
			}
			if (optionalCompteCourantRecepteur.isEmpty()) {
				messageReponse = "le compte courant recepteur n'existe pas";
				throw new GeneralException(messageReponse);
			}
			CompteCourant existingCompteCourantEmetteur = optionalCompteCourantEmetteur.get();
			CompteCourant existingCompteCourantRecepteur = optionalCompteCourantRecepteur.get();

			double soldeEmetteur = existingCompteCourantEmetteur.getBalance();
			if (montant <= soldeEmetteur || soldeEmetteur + existingCompteCourantEmetteur.getOverdraft() >= montant) {
				double nouveauSoldeEmetteur = soldeEmetteur - montant;
				existingCompteCourantEmetteur.setBalance(nouveauSoldeEmetteur);
				double nouveauSoldeRecepteur = existingCompteCourantRecepteur.getBalance() + montant;
				existingCompteCourantRecepteur.setBalance(nouveauSoldeRecepteur);
				messageReponse = String.format(
						"Virement effectué avec succès !"
								+ " %.2f € transférés du compte numéro %s au compte numéro %s ."
								+ " Nouveau solde émmetteur = %.2f et Nouveau solde créditeur = %.2f",
						montant, existingCompteCourantEmetteur.getAccountNumber(),
						existingCompteCourantRecepteur.getAccountNumber(), nouveauSoldeEmetteur, nouveauSoldeRecepteur);
				Client clientEmetteur = clientRepository.findById(existingCompteCourantEmetteur.getId()).get();
				Client clientRecepteur = clientRepository.findById(existingCompteCourantRecepteur.getId()).get();
				
				Transaction transaction1 = new Transaction();
				transaction1.setAmount(montant);
				transaction1.setClientEmetteur(clientEmetteur);
				transaction1.setCompteEmitteurId(existingCompteCourantEmetteur.getId());
				transaction1.setClientRecepteur(clientRecepteur);
				transaction1.setCompteRecepteurId(existingCompteCourantRecepteur.getId());
				transaction1.setTypeDeVirement(Transaction.TypeDeVirement.COURANT_COURANT);
				
				clientRepository.save(clientEmetteur);
				clientRepository.save(clientRecepteur);
				transactionRepository.save(transaction1);

				return messageReponse;
			} else {
				messageReponse = "solde insuffisant";
				throw new GeneralException(messageReponse);
			}
		} else {
			messageReponse = "Le montant du virement doit être positif";
			throw new GeneralException(messageReponse);
		}
	}

	@Override
	public String virementCourantEpargne(double montant, Long compteCId) throws GeneralException {
		String messageReponse;
		if (montant > 0) {
			Optional<CompteCourant> optionalCompteCourant = compteCourantRepository.findById(compteCId);
			if (optionalCompteCourant.isEmpty()) {
				messageReponse = "le compte courant émeteur n'existe pas";
				throw new GeneralException(messageReponse);
			}
			CompteCourant existingCompteCourant = optionalCompteCourant.get();

			Optional<Client> optionalClient = clientRepository.findById(existingCompteCourant.getId());
			Client client = optionalClient.get();

			Optional<CompteEpargne> optionalCompteEpargne = Optional.of(client.getCompteEpargne());
			if (optionalCompteEpargne.isEmpty()) {
				messageReponse = "le compte épargne récepteur n'existe pas";
				throw new GeneralException(messageReponse);
			}

			CompteEpargne existingCompteEpargne = optionalCompteEpargne.get();

			double soldeEmetteur = existingCompteCourant.getBalance();
			double nouveauSoldeEmetteur;
			double nouveauSoldeRecepteur;
			if (soldeEmetteur >= montant || soldeEmetteur + existingCompteCourant.getOverdraft() >= montant) {
				nouveauSoldeEmetteur = soldeEmetteur - montant;
				existingCompteCourant.setBalance(nouveauSoldeEmetteur);
				nouveauSoldeRecepteur = existingCompteEpargne.getBalance() + montant;
				existingCompteEpargne.setBalance(nouveauSoldeRecepteur);
				
				Transaction transaction1 = new Transaction();
				transaction1.setAmount(montant);
				transaction1.setClientEmetteur(client);
				transaction1.setCompteEmitteurId(existingCompteCourant.getId());
				transaction1.setClientRecepteur(client);
				transaction1.setCompteRecepteurId(existingCompteEpargne.getId());
				transaction1.setTypeDeVirement(Transaction.TypeDeVirement.COURANT_EPARGNE);
				
				clientRepository.save(client);
				transactionRepository.save(transaction1);

				messageReponse = String.format(
						"Virement effectué avec succès !"
								+ " %.2f € transférés du compte numéro %s au compte numéro %s."
								+ " Nouveau solde émmetteur = %.2f et Nouveau solde créditeur = %.2f",
						montant, existingCompteCourant.getAccountNumber(), existingCompteEpargne.getAccountNumber(),
						nouveauSoldeEmetteur, nouveauSoldeRecepteur);
				return messageReponse;
			} else {
				messageReponse = "Solde insuffisant";
				throw new GeneralException(messageReponse);
			}

		} else {
			messageReponse = "Le montant du virement doit être positif";
			throw new GeneralException(messageReponse);
		}
	}

	@Override
	public String virementEpargneCourant(double montant, Long compteEpId) throws GeneralException {
		String messageReponse;
		Optional<CompteEpargne> optionalCompteEpargne = compteEpargneRepository.findById(compteEpId);

		if (optionalCompteEpargne.isEmpty()) {
			messageReponse = "le compte épargne émeteur n'existe pas";
			throw new GeneralException(messageReponse);
		}
		CompteEpargne existingCompteEpargne = optionalCompteEpargne.get();

		Optional<Client> optionalClient = clientRepository.findById(existingCompteEpargne.getId());
		Client client = optionalClient.get();

		Optional<CompteCourant> optionalCompteCourant = Optional.of(client.getCompteCourant());
		if (optionalCompteCourant.isEmpty()) {
			messageReponse = "le compte courant récepteur n'existe pas";
			throw new GeneralException(messageReponse);
		}

		CompteCourant existingCompteCourant = optionalCompteCourant.get();

		if (montant > 0) {
			double soldeEpargne = existingCompteEpargne.getBalance();
			if (montant <= soldeEpargne) {
				double nouveauSoldeEpargne = soldeEpargne - montant;
				existingCompteEpargne.setBalance(nouveauSoldeEpargne);
				double nouveauSoldeCourant = existingCompteCourant.getBalance() + montant;
				existingCompteCourant.setBalance(nouveauSoldeCourant);
				clientRepository.save(client);

				messageReponse = String.format(
						"Virement effectué avec succès !"
								+ " %.2f € transférés du compte numéro %s au compte numéro %s. "
								+ "Nouveau solde émmetteur = %.2f et Nouveau solde créditeur = %.2f",
						montant, existingCompteEpargne.getAccountNumber(), existingCompteCourant.getAccountNumber(),
						nouveauSoldeEpargne, nouveauSoldeCourant);
				
				Transaction transaction1 = new Transaction();
				transaction1.setAmount(montant);
				transaction1.setClientEmetteur(client);
				transaction1.setCompteEmitteurId(existingCompteEpargne.getId());
				transaction1.setClientRecepteur(client);
				transaction1.setCompteRecepteurId(existingCompteCourant.getId());
				transaction1.setTypeDeVirement(Transaction.TypeDeVirement.EPARGNE_COURANT);
				
				clientRepository.save(client);
				transactionRepository.save(transaction1);
				
				return messageReponse;
			} else {
				messageReponse = "Solde épargne insuffisant";
				throw new GeneralException(messageReponse);
			}
		} else {
			messageReponse = "Le montant du virement doit être positif";
			throw new GeneralException(messageReponse);
		}
	}

	@Override
	public List<Transaction> getAllTransactions() {
		return transactionRepository.findAll();
	}

	@Override
	public Optional<Transaction> getTransactionById(Long id) {
		return transactionRepository.findById(id);
	}

	@Override
	public Transaction createTransaction(double amount, Client clientEmetteur, Client clientRecepteur,
			TypeDeVirement typeDeVirement) {
		Transaction transaction = new Transaction(amount, clientEmetteur, clientRecepteur, typeDeVirement);
		return transactionRepository.save(transaction);
	}

}
