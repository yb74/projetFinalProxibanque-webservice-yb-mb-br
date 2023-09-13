package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;

@Entity
public class Client {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

//	@NotEmpty(message = "the name of the client is necessary")
	private String name;

//	@NotEmpty(message = "the first name of the client is necessary")
	private String firstName;

//	@NotEmpty(message = "the adress name of the client is necessary")
	private String adress;

//	@NotEmpty(message = "the zip code of the client is necessary")
	private int zipCode;

//	@NotEmpty(message = "the city of the client is necessary")
	private String city;

//	@NotEmpty(message = "the phone number of the client is necessary")
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

	public Client() {
	}

	//on change le constructeur pour forcer l'association client compte courant ou epargne
	// we enforce the association between Client and CompteCourant or CompteEpargne
	public Client(String name,String firstName,CompteCourant compteCourant,CompteEpargne compteEpargne) {
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

	@Override
	public String toString() {
		return "Client [id=" + id + ", name=" + name + ", firstName=" + firstName + ", adress=" + adress + ", zipCode="
				+ zipCode + ", city=" + city + ", phoneNumber=" + phoneNumber + ", conseiller=" + conseiller + "]";
	}

}
