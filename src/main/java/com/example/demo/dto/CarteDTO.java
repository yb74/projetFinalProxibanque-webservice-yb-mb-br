package com.example.demo.dto;

import com.example.demo.model.Carte;
import com.example.demo.model.CompteCourant;
import jakarta.validation.constraints.NotNull;

public class CarteDTO {
    private Long id;
    @NotNull(message = "Carte typeDeCarte field can't be empty")
    private Carte.TypeDeCarte typeDeCarte;
    private CompteCourant compteCourant;

    public CarteDTO() {}

    public CarteDTO(Carte.TypeDeCarte typeDeCarte) {
        this.typeDeCarte = typeDeCarte;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Carte.TypeDeCarte getTypeDeCarte() {
        return typeDeCarte;
    }

    public void setTypeDeCarte(Carte.TypeDeCarte typeDeCarte) {
        this.typeDeCarte = typeDeCarte;
    }

    public CompteCourant getCompteCourant() {
        return compteCourant;
    }

    public void setCompteCourant(CompteCourant compteCourant) {
        this.compteCourant = compteCourant;
    }
}
