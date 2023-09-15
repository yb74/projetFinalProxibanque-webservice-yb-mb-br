package com.example.demo.dto;

public class CompteCourantDTO {
	private Long id;
	private double balance;
	private double overdraft = 1000;
	private Long carteId;
	private Long clientId;

	public CompteCourantDTO() {
	}

	public CompteCourantDTO(double balance) {
		this.balance = balance;
	}

//	public CompteCourantDTO(Long id, double balance, double overdraft, Carte carte, Client client) {
//		this.id = id;
//		this.balance = balance;
//		this.overdraft = overdraft;
//		this.carte = carte;
//		this.client = client;
//	}

	public CompteCourantDTO(Long id, double balance, double overdraft, Long carteId, Long clientId) {
		this.id = id;
		this.balance = balance;
		this.overdraft = overdraft;
		this.carteId = carteId;
		this.clientId = clientId;
	}

//	public CompteCourantDTO(double balance, Carte carte) {
//		this.balance = balance;
//		this.carte = carte;
//	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}

	public double getOverdraft() {
		return overdraft;
	}

	public void setOverdraft(int overdraft) {
		this.overdraft = overdraft;
	}

//	public Carte getCarte() {
//		return carte;
//	}
//
//	public void setCarte(Carte carte) {
//		this.carte = carte;
//	}

//	public Client getClient() {
//		return client;
//	}
//
//	public void setClient(Client client) {
//		this.client = client;
//	}

	public Long getClientId() {
		return clientId;
	}

	public Long getCarteId() {
		return carteId;
	}

	public void setCarteId(Long carteId) {
		this.carteId = carteId;
	}

	public void setClientId(Long clientId) {
		this.clientId = clientId;
	}

	public void setOverdraft(double overdraft) {
		this.overdraft = overdraft;
	}

}
