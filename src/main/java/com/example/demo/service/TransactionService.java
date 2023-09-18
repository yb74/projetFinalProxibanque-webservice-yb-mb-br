package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import com.example.demo.exception.GeneralException;
import com.example.demo.model.Client;
import com.example.demo.model.Transaction;
import com.example.demo.model.Transaction.TypeDeVirement;

public interface TransactionService {
	String virementEpargneCourant(double montant, Long compteEpId) throws GeneralException;

	Transaction createTransaction(double amount, Client clientEmetteur, Client clientRecepteur,
			TypeDeVirement typeDeVirement);

	Optional<Transaction> getTransactionById(Long id);

	List<Transaction> getAllTransactions();

	String virementComptesCourants(double montant, Long idEmetteur, Long idRecepteur) throws GeneralException;

	String virementCourantEpargne(double montant, Long compteCId) throws GeneralException;
}
