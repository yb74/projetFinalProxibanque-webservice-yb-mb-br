package com.example.demo.model;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import jakarta.validation.constraints.Min;

import java.time.LocalDate;
import java.util.UUID;

@MappedSuperclass
public abstract class Compte {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String accountNumber;
    
    @Min(value = -1000)
    private double balance;
    private LocalDate creationDate;

    private void generateUniqueNumCompte() {
        String uniqueId = UUID.randomUUID().toString().replace("-", "");
        accountNumber = uniqueId.substring(0, 10); // Using 10 first characters
    }

    public Compte() {
        this.creationDate = LocalDate.now();
        generateUniqueNumCompte();

    }

    public Compte(double balance) {
        this.balance = balance;
        this.creationDate = LocalDate.now();
        generateUniqueNumCompte();

    }

    public Compte(String accountNumber, double balance) {
        this.accountNumber = accountNumber;
        this.balance = balance;
        this.creationDate = LocalDate.now();
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

    public void setAccountNumber(String numCompte) {
        this.accountNumber = numCompte;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public LocalDate getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDate creationDate) {
        this.creationDate = creationDate;
    }
}