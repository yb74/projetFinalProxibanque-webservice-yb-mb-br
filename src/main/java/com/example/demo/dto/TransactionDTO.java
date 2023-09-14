package com.example.demo.dto;

import java.time.LocalDateTime;

import com.example.demo.model.Client;
import com.example.demo.model.Transaction.TypeDeVirement;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public class TransactionDTO {

	@NotNull
	private Long id;

	@NotEmpty(message = "the amount of the transaction is necessary")
	private double amount;

	@NotEmpty(message = "the date of the transaction is necessary")
	private LocalDateTime timestamp = LocalDateTime.now();

	@Valid
	private Client clientEmetteur;

	@Valid
	private Client clientRecepteur;

	@NotEmpty(message = "the type of the transaction is necessary")
	private TypeDeVirement typeDeVirement;

	@NotEmpty(message = "the ID of the compte is necessary")
	private Long compteEmitteurId;

	@NotEmpty(message = "the ID of the compte is necessary")
	private Long compteRecepteurId;

	public TransactionDTO(Long id, double amount, LocalDateTime timestamp, Client clientEmetteur,
			Client clientRecepteur, TypeDeVirement typeDeVirement, Long compteEmitteurId,
			Long compteRecepteurId) {
		this.id = id;
		this.amount = amount;
		this.timestamp = timestamp;
		this.clientEmetteur = clientEmetteur;
		this.clientRecepteur = clientRecepteur;
		this.typeDeVirement = typeDeVirement;
		this.compteEmitteurId = compteEmitteurId;
		this.compteRecepteurId = compteRecepteurId;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public LocalDateTime getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(LocalDateTime timestamp) {
		this.timestamp = timestamp;
	}

	public Client getClientEmetteur() {
		return clientEmetteur;
	}

	public void setClientEmetteur(Client clientEmetteur) {
		this.clientEmetteur = clientEmetteur;
	}

	public Client getClientRecepteur() {
		return clientRecepteur;
	}

	public void setClientRecepteur(Client clientRecepteur) {
		this.clientRecepteur = clientRecepteur;
	}

	public TypeDeVirement getTypeDeVirement() {
		return typeDeVirement;
	}

	public void setTypeDeVirement(TypeDeVirement typeDeVirement) {
		this.typeDeVirement = typeDeVirement;
	}

	public Long getCompteEmitteurId() {
		return compteEmitteurId;
	}

	public void setCompteEmitteurId(Long compteEmitteurId) {
		this.compteEmitteurId = compteEmitteurId;
	}

	public Long getCompteRecepteurId() {
		return compteRecepteurId;
	}

	public void setCompteRecepteurId(Long compteRecepteurId) {
		this.compteRecepteurId = compteRecepteurId;
	}

}
