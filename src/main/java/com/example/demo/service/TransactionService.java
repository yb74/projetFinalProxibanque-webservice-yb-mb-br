package com.example.demo.service;

import java.util.List;

import com.example.demo.exception.GeneralException;
import com.example.demo.model.Client;
import com.example.demo.model.Transaction;
import com.example.demo.model.Transaction.TypeDeVirement;

public interface TransactionService {
	String virementComptesCourants(double montant, Client clientEmetteur, Client clientRecepteur)
			throws GeneralException;

	String virementCourantEpargne(double montant, Client client) throws GeneralException;

	String virementEpargneCourant(double montant, Client client) throws GeneralException;

	Transaction createTransaction(double amount, Client clientEmetteur, Client clientRecepteur, TypeDeVirement typeDeVirement);

	Transaction getTransactionById(Long id);

	List<Transaction> getAllTransactions();
}
