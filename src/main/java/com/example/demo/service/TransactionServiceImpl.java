package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.demo.exception.GeneralException;
import com.example.demo.model.Client;
import com.example.demo.model.CompteCourant;
import com.example.demo.model.Transaction;
import com.example.demo.model.Transaction.TypeDeVirement;
import com.example.demo.repository.ClientRepository;
import com.example.demo.repository.TransactionRepository;

@Service
public class TransactionServiceImpl implements TransactionService {

	private final ClientRepository clientRepository;
	private final TransactionRepository transactionRepository;

	public TransactionServiceImpl(ClientRepository clientRepository, TransactionRepository transactionRepository) {
		this.clientRepository = clientRepository;
		this.transactionRepository = transactionRepository;
	}

	@Override
	public String virementComptesCourants(double montant, Client clientEmetteur, Client clientRecepteur)
			throws GeneralException {
		String messageReponse;
		if (montant > 0) {
			CompteCourant compteCourantEmetteur = clientEmetteur.getCompteCourant();
			CompteCourant compteCourantRecepteur = clientRecepteur.getCompteCourant();
			double soldeEmetteur = compteCourantEmetteur.getBalance();
			if (montant <= soldeEmetteur || soldeEmetteur + compteCourantEmetteur.getOverdraft() >= montant) {
				double nouveauSoldeEmetteur = soldeEmetteur - montant;
				compteCourantEmetteur.setBalance(nouveauSoldeEmetteur);
				double nouveauSoldeRecepteur = compteCourantRecepteur.getBalance() + montant;
				compteCourantRecepteur.setBalance(nouveauSoldeRecepteur);
				messageReponse = String.format("Virement effectué avec succès !" +
                        " %.2f € transférés du compte numéro %s au compte numéro %s ." +
                        " Nouveau solde émmetteur = %.2f et Nouveau solde créditeur = %.2f", montant, compteCourantEmetteur.getAccountNumber(), compteCourantRecepteur.getAccountNumber(), nouveauSoldeEmetteur, nouveauSoldeRecepteur);
				clientRepository.save(clientEmetteur);
				clientRepository.save(clientRecepteur);
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
	public String virementCourantEpargne(double montant, Client client) throws GeneralException {
		String messageReponse;
		if (montant > 0) {
			double soldeEmetteur = client.getCompteCourant().getBalance();
			double nouveauSoldeEmetteur;
			double nouveauSoldeRecepteur;
			if (soldeEmetteur >= montant || soldeEmetteur + client.getCompteCourant().getOverdraft() >= montant) {
				nouveauSoldeEmetteur = soldeEmetteur - montant;
				client.getCompteCourant().setBalance(nouveauSoldeEmetteur);
				nouveauSoldeRecepteur = client.getCompteEpargne().getBalance() + montant;
				client.getCompteEpargne().setBalance(nouveauSoldeRecepteur);
				clientRepository.save(client);
				// messageReponse = "Virement effectué avec succès !";

				messageReponse = String.format("Virement effectué avec succès !" +
						" %.2f € transférés du compte numéro %s au compte numéro %s." +
						" Nouveau solde émmetteur = %.2f et Nouveau solde créditeur = %.2f", montant, client.getCompteCourant().getAccountNumber(), client.getCompteCourant().getAccountNumber(), nouveauSoldeEmetteur, nouveauSoldeRecepteur);

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
	public String virementEpargneCourant(double montant, Client client) throws GeneralException {
		String messageReponse;
		if (montant > 0) {
			double soldeEpargne = client.getCompteEpargne().getBalance();
			if (montant <= soldeEpargne) {
				double nouveauSoldeEpargne = soldeEpargne - montant;
				client.getCompteEpargne().setBalance(nouveauSoldeEpargne);
				double nouveauSoldeCourant = client.getCompteCourant().getBalance() + montant;
				client.getCompteCourant().setBalance(nouveauSoldeCourant);
				clientRepository.save(client);

				messageReponse = String.format("Virement effectué avec succès !" +
						" %.2f € transférés du compte numéro %s au compte numéro %s. " +
						"Nouveau solde émmetteur = %.2f et Nouveau solde créditeur = %.2f", montant, client.getCompteEpargne().getAccountNumber(), client.getCompteCourant().getAccountNumber(), nouveauSoldeEpargne, nouveauSoldeCourant);

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
    public Transaction createTransaction(double amount, Client clientEmetteur, Client clientRecepteur, TypeDeVirement typeDeVirement) {
        Transaction transaction = new Transaction(amount, clientEmetteur, clientRecepteur, typeDeVirement);
        return transactionRepository.save(transaction);
    }

}
