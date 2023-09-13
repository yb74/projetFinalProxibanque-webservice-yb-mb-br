package com.example.demo.model;

import jakarta.persistence.*;

@Entity
public class Carte {
    public enum TypeDeCarte {
        VISA_ELECTRON, VISA_PREMIER;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING) // Map the enum as a string in the database
    private TypeDeCarte typeDeCarte;

    @OneToOne(mappedBy = "carte", cascade = { CascadeType.PERSIST })
    CompteCourant compteCourant;

    public Carte() {}

    public Carte(TypeDeCarte typeDeCarte) {
        this.typeDeCarte = typeDeCarte;
    }

    public Carte(TypeDeCarte typeDeCarte, CompteCourant compteCourant ) {
        this.typeDeCarte = typeDeCarte;
        this.compteCourant = compteCourant;
        if (compteCourant != null) {
            compteCourant.setCarte(this);
        }
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public TypeDeCarte  getTypeDeCarte() {
        return typeDeCarte;
    }

    public void setTypeDeCarte(TypeDeCarte  typeDeCarte) {
        this.typeDeCarte = typeDeCarte;
    }

    public CompteCourant getCompte() {
        return compteCourant;
    }

    public void setCompte(CompteCourant compteCourant) {
        this.compteCourant = compteCourant;

    }
}
