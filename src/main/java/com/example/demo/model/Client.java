package com.example.demo.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;

@Entity
public class Client {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String name;

	private String firstName;

	private String adress;

	private int zipCode;

	private String city;

	private String phoneNumber;

	@OneToOne(cascade = { CascadeType.REMOVE, CascadeType.PERSIST })
	@JoinColumn(name = "compteCourant_id")
	private CompteCourant compteCourant;

	@OneToOne(cascade = { CascadeType.REMOVE, CascadeType.PERSIST })
	@JoinColumn(name = "compteEpargne_id")
	private CompteEpargne compteEpargne;

	@JsonIgnore
	@ManyToOne(cascade = { CascadeType.PERSIST })
	@JoinColumn(name = "conseiller_id")
	private Conseiller conseiller;

	@JsonIgnore
	@OneToMany(mappedBy = "clientEmetteur",cascade = { CascadeType.REMOVE })
	private List<Transaction> transactionsEmetteur;

	@JsonIgnore
	@OneToMany(mappedBy = "clientRecepteur", cascade = { CascadeType.REMOVE })
	private List<Transaction> transactionsRecepteur;

	public Client() {
	}

	// on change le constructeur pour forcer l'association client compte courant ou
	// epargne
	// we enforce the association between Client and CompteCourant or CompteEpargne
	public Client(String name, String firstName, CompteCourant compteCourant, CompteEpargne compteEpargne) {
		this.name = name;
		this.firstName = firstName;
		this.compteCourant = compteCourant;
		if (compteCourant != null) {
			compteCourant.setClient(this);
		}
		this.compteEpargne = compteEpargne;
		if (compteEpargne != null) {
			compteEpargne.setClient(this);
		}
	}

	public Client(Long id, String name, String firstName, String adress, int zipCode, String city, String phoneNumber,
			Conseiller conseiller) {
		this.id = id;
		this.name = name;
		this.firstName = firstName;
		this.adress = adress;
		this.zipCode = zipCode;
		this.city = city;
		this.phoneNumber = phoneNumber;
		this.conseiller = conseiller;
	}

	public Client(Long id, String name, String firstName, String adress, int zipCode, String city, String phoneNumber,
			Conseiller conseiller, CompteCourant compteCourant, CompteEpargne compteEpargne) {
		this.id = id;
		this.name = name;
		this.firstName = firstName;
		this.adress = adress;
		this.zipCode = zipCode;
		this.city = city;
		this.phoneNumber = phoneNumber;
		this.compteCourant = compteCourant;
		this.compteEpargne = compteEpargne;
		this.conseiller = conseiller;
	}

	public Client(String name, String firstName, String adress, int zipCode, String city, String phoneNumber) {
		this.name = name;
		this.firstName = firstName;
		this.adress = adress;
		this.zipCode = zipCode;
		this.city = city;
		this.phoneNumber = phoneNumber;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getAdress() {
		return adress;
	}

	public void setAdress(String adress) {
		this.adress = adress;
	}

	public int getZipCode() {
		return zipCode;
	}

	public void setZipCode(int zipCode) {
		this.zipCode = zipCode;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public CompteCourant getCompteCourant() {
		return compteCourant;
	}

	public void setCompteCourant(CompteCourant compteCourant) {
		this.compteCourant = compteCourant;
	}

	public CompteEpargne getCompteEpargne() {
		return compteEpargne;
	}

	public void setCompteEpargne(CompteEpargne compteEpargne) {
		this.compteEpargne = compteEpargne;
	}

	public Conseiller getConseiller() {
		return conseiller;
	}

	public void setConseiller(Conseiller conseiller) {
		this.conseiller = conseiller;
	}

	public List<Transaction> getTransactionsEmetteur() {
		return transactionsEmetteur;
	}

	public void setTransactionsEmetteur(List<Transaction> transactionsEmetteur) {
		this.transactionsEmetteur = transactionsEmetteur;
	}

	public List<Transaction> getTransactionsRecepteur() {
		return transactionsRecepteur;
	}

	public void setTransactionsRecepteur(List<Transaction> transactionsRecepteur) {
		this.transactionsRecepteur = transactionsRecepteur;
	}

	@Override
	public String toString() {
		return "Client [id=" + id + ", name=" + name + ", firstName=" + firstName + ", adress=" + adress + ", zipCode="
				+ zipCode + ", city=" + city + ", phoneNumber=" + phoneNumber + ", conseiller=" + conseiller + "]";
	}

}
