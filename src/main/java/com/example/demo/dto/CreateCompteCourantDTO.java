package com.example.demo.dto;

import com.example.demo.model.Carte;
import jakarta.validation.Valid;

import jakarta.validation.constraints.NotNull;

public class CreateCompteCourantDTO {
    @Valid
    private CompteCourantDTO compte;

    @NotNull(message = "CompteCourantDTO clientId field can't be null")
    private Long clientId;

    @NotNull(message = "CompteCourantDTO typeDeCarte field can't be null")
    private Carte.TypeDeCarte typeDeCarte;

    public CompteCourantDTO getCompte() {
        return compte;
    }

    public void setCompte(CompteCourantDTO compte) {
        this.compte = compte;
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
}
