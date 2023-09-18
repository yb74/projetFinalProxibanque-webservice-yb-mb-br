package com.example.demo.dto;

import com.example.demo.model.Carte;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

public class CompteCourantDTO {
	private Long id;
	private String accountNumber;
	private double balance;
	private double overdraft = 1000;
	private Long carteId;
	private String clientName;
	private String clientFirstname;

	@NotNull(message = "CompteCourantDTO clientId field can't be null")
	private Long clientId;

	@NotNull(message = "CompteCourantDTO typeDeCarte field can't be null")
	private Carte.TypeDeCarte typeDeCarte = Carte.TypeDeCarte.VISA_ELECTRON;

	public CompteCourantDTO() {
	}
	
	public CompteCourantDTO(double balance) {
		this.balance = balance;
	}
	
	public CompteCourantDTO(Long id, String accountNumber, double balance, double overdraft, Long carteId,
			Long clientId, String clientName, String clientFirstname) {
		this.id = id;
		this.accountNumber = accountNumber;
		this.balance = balance;
		this.overdraft = overdraft;
		this.carteId = carteId;
		this.clientId = clientId;
		this.clientFirstname = clientFirstname;
		this.clientName = clientName;
	}

	public Long getClientId() {
		return clientId;
	}

	public void setClientId(Long clientId) {
		this.clientId = clientId;
	}

	public Carte.TypeDeCarte getTypeDeCarte() {
		return typeDeCarte;
	}

	public void setTypeDeCarte(Carte.TypeDeCarte typeDeCarte) {
		this.typeDeCarte = typeDeCarte;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
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

	public void setOverdraft(double overdraft) {
		this.overdraft = overdraft;
	}

	public Long getCarteId() {
		return carteId;
	}

	public void setCarteId(Long carteId) {
		this.carteId = carteId;
	}

	public String getClientName() {
		return clientName;
	}

	public void setClientName(String clientName) {
		this.clientName = clientName;
	}

	public String getClientFirstname() {
		return clientFirstname;
	}

	public void setClientFirstname(String clientFirstname) {
		this.clientFirstname = clientFirstname;
	}

}
