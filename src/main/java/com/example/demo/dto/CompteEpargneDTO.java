package com.example.demo.dto;

import com.example.demo.model.Client;

public class CompteEpargneDTO {
    private Long id;
    private String accountNumber;
    private double balance;
    private double remuneration = 0.3;
    private Client client;

    private String accountType;

    public CompteEpargneDTO() {
    }

    public CompteEpargneDTO(Long id, String accountNumber, double balance, double remuneration, Client client) {
        this.id = id;
        this.accountNumber = accountNumber;
        this.balance = balance;
        this. remuneration = remuneration;
        this.client = client;
        this.accountType = "CompteEpargne";
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

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }
}
