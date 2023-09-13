package com.example.demo.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

public class CreateCompteEpargneDTO {
    @Valid
    private CompteEpargneDTO compte;

    @NotNull(message = "CompteCourantDTO clientId field can't be null")
    private Long clientId;

    public CompteEpargneDTO getCompte() {
        return compte;
    }

    public void setCompte(CompteEpargneDTO compte) {
        this.compte = compte;
    }

    public Long getClientId() {
        return clientId;
    }

    public void setClientId(Long clientId) {
        this.clientId = clientId;
    }
}
