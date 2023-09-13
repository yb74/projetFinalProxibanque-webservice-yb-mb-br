package com.example.demo.dto;

import com.example.demo.model.Carte;
import com.example.demo.model.Client;

public class CompteCourantDTO {
    private Long id;
    private double balance;
    private int decouvert = 1000;
    private Carte carte;
    private Client client;

    public CompteCourantDTO() {
    }

    public CompteCourantDTO(double balance) {
        this.balance = balance;
    }

    public CompteCourantDTO(Long id, double balance, int decouvert, Carte carte, Client client) {
        this.id = id;
        this.balance = balance;
        this.decouvert = decouvert;
        this.carte = carte;
        this.client = client;
    }

    public CompteCourantDTO(double balance, Carte carte) {
        this.balance = balance;
        this.carte = carte;
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

    public int getDecouvert() {
        return decouvert;
    }

    public void setDecouvert(int decouvert) {
        this.decouvert = decouvert;
    }

    public Carte getCarte() {
        return carte;
    }

    public void setCarte(Carte carte) {
        this.carte = carte;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }
}
