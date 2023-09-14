package com.example.demo.model;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Transaction {
	public enum TypeDeVirement {
		COURANT_COURANT, COURANT_EPARGNE, EPARGNE_COURANT;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private double amount;
	private LocalDateTime timestamp = LocalDateTime.now();

	@ManyToOne
	@JoinColumn(name = "client_emetteur_id")
	private Client clientEmetteur;

	@ManyToOne
	@JoinColumn(name = "client_recepteur_id")
	private Client clientRecepteur;

	@Enumerated(EnumType.STRING)
	private TypeDeVirement typeDeVirement;

	private Long compteEmitteurId;

	private Long compteRecepteurId;

	public Transaction() {
	}

	public Transaction(double amount, Client clientEmetteur, Client clientRecepteur, TypeDeVirement typeDeVirement) {
		this.amount = amount;
		this.clientEmetteur = clientEmetteur;
		this.clientRecepteur = clientRecepteur;
		this.timestamp = LocalDateTime.now();
		this.typeDeVirement = typeDeVirement;
	}

	public Transaction(Long id, double amount, LocalDateTime timestamp, Client clientEmetteur, Client clientRecepteur,
			TypeDeVirement typeDeVirement, Long compteEmitteurId, Long compteRecepteurId) {
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
