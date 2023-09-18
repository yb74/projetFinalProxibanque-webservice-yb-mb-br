package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;

@Entity
public class CompteCourant extends Compte {
	private double overdraft = 1000;

	@JsonIgnore
	@OneToOne(mappedBy = "compteCourant", cascade = CascadeType.PERSIST)
	private Client client;

	@JsonIgnore
	@OneToOne(cascade = { CascadeType.PERSIST, CascadeType.REMOVE })
	@JoinColumn(name = "carte_id")
	private Carte carte;

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;

	}

	public CompteCourant() {
		this.client = new Client();
		this.client.setCompteCourant(this);
		this.carte = new Carte();
		this.carte.setCompte(this);
	}

	public CompteCourant(double balance, String accountNumber, double overdraft, Carte carte, Client client) {
		super(accountNumber, balance);
		this.overdraft = overdraft;
		this.carte = carte;
		this.client = client;
	}

	public CompteCourant(double balance) {
		super(balance);
//		this.client = new Client();
//		this.client.setCompteCourant(this);
		this.carte = new Carte();
		this.carte.setCompte(this);
	}

	public double getOverdraft() {
		return overdraft;
	}

	public void setOverdraft(double overdraft) {
		this.overdraft = overdraft;
	}

	public Carte getCarte() {
		return carte;
	}

	public void setCarte(Carte carte) {
		this.carte = carte;
	}

	@Override
	public String toString() {
		return "CompteCourant [overdraft=" + overdraft + ", clientId=" + client.getId() + ", carte=" + carte + "]";
	}
	
	
}
