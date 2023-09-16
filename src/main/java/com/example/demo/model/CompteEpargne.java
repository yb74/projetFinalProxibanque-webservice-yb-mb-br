package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToOne;

@Entity
public class CompteEpargne extends Compte {
    private double remuneration = 0.3;

    @JsonIgnore
    @OneToOne(mappedBy = "compteEpargne", cascade = { CascadeType.PERSIST })
    Client client;

    public CompteEpargne() {
        this.client=new Client();
        this.client.setCompteEpargne(this);
    }

    public CompteEpargne(String accountNumber, double balance, double remuneration, Client client) {
        super(accountNumber, balance);
        this.remuneration = remuneration;
        this.client = client;
    }

    public CompteEpargne(double balance) {
        super(balance);
//        this.client=new Client();
//        this.client.setCompteEpargne(this);
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
