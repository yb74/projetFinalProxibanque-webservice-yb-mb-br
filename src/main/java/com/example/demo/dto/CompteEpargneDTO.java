package com.example.demo.dto;

import com.example.demo.model.Client;

public class CompteEpargneDTO {
    private Long id;
    private double balance;
    private double remuneration = 0.3;
    private Client client;

    public CompteEpargneDTO() {
    }

    public CompteEpargneDTO(Long id, double balance, double remuneration, Client client) {
        this.id = id;
        this.balance = balance;
        this. remuneration = remuneration;
        this.client = client;
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