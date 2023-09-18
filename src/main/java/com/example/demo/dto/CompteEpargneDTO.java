package com.example.demo.dto;

import com.example.demo.model.Client;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

public class CompteEpargneDTO {
	private Long id;
	private String accountNumber;
	private double balance;
	private double remuneration = 0.3;
	private String clientName;
	private String clientFirstname;

	@NotNull(message = "CompteCourantDTO clientId field can't be null")
	private Long clientId;

	public CompteEpargneDTO(Long id, String accountNumber, double balance, double remuneration, Client client) {
		this.id = id;
		this.accountNumber = accountNumber;
		this.balance = balance;
		this.remuneration = remuneration;
	}

	public CompteEpargneDTO(Long id, String accountNumber, double balance, double remuneration, Long clientId,
							String clientName, String clientFirstname) {
		this.id = id;
		this.accountNumber = accountNumber;
		this.balance = balance;
		this.remuneration = remuneration;
		this.clientId = clientId;
		this.clientName = clientName;
		this.clientFirstname = clientFirstname;
	}

	public CompteEpargneDTO(double balance) {
		this.balance = balance;
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

	public double getRemuneration() {
		return remuneration;
	}

	public void setRemuneration(double remuneration) {
		this.remuneration = remuneration;
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

	public Long getClientId() {
		return clientId;
	}

	public void setClientId(Long clientId) {
		this.clientId = clientId;
	}
}
