package com.example.demo.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public class ClientDTO {
	private Long id;

	@NotEmpty(message = "the name of the client is necessary")
	private String name;

	@NotEmpty(message = "the first name of the client is necessary")
	private String firstName;

	@NotEmpty(message = "the adress name of the client is necessary")
	private String adress;

	@Min(value = 10000, message = "the zip code must be at least 5 digits")
	private int zipCode;

	@NotEmpty(message = "the city of the client is necessary")
	private String city;

	@NotEmpty(message = "the phone number of the client is necessary")
	private String phoneNumber;

//	@Valid
//	private Conseiller conseiller;

	@NotNull
	private Long conseillerId;

	private Long compteCourantId;
	private Long compteEpargneId;
	
	//---------------------ajout constructor par défaut---------------------
	public ClientDTO() {	
		System.out.println("clientDTO");
	}	
	
	
//
//	public ClientDTO(Long id, String name, String firstName, String adress, int zipCode, String city,
//			String phoneNumber, Conseiller conseiller) {
//		this.id = id;
//		this.name = name;
//		this.firstName = firstName;
//		this.adress = adress;
//		this.zipCode = zipCode;
//		this.city = city;
//		this.phoneNumber = phoneNumber;
//		this.conseiller = conseiller;
//	}

	public ClientDTO(Long id, String name, String firstName, String adress, int zipCode, String city,
			String phoneNumber, Long conseillerId) {
		this.id = id;
		this.name = name;
		this.firstName = firstName;
		this.adress = adress;
		this.zipCode = zipCode;
		this.city = city;
		this.phoneNumber = phoneNumber;
		this.conseillerId = conseillerId;
	}

	public ClientDTO(Long id, String name, String firstName, String adress, int zipCode, String city,
			String phoneNumber, Long conseillerId, Long compteCourantId, Long compteEpargneId) {
		this.id = id;
		this.name = name;
		this.firstName = firstName;
		this.adress = adress;
		this.zipCode = zipCode;
		this.city = city;
		this.phoneNumber = phoneNumber;
		this.conseillerId = conseillerId;
		this.compteCourantId = compteCourantId;
		this.compteEpargneId = compteEpargneId;
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

	public void setFirstName(String firstname) {
		this.firstName = firstname;
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

//	public Conseiller getConseiller() {
//		return conseiller;
//	}
//
//	public void setConseiller(Conseiller conseiller) {
//		this.conseiller = conseiller;
//	}

	public Long getConseillerId() {
		return conseillerId;
	}

	public void setConseillerId(Long conseillerId) {
		this.conseillerId = conseillerId;
	}

	public Long getCompteCourantId() {
		return compteCourantId;
	}

	public void setCompteCourantId(Long compteCourantId) {
		this.compteCourantId = compteCourantId;
	}

	public Long getCompteEpargneId() {
		return compteEpargneId;
	}

	public void setCompteEpargneId(Long compteEpargneId) {
		this.compteEpargneId = compteEpargneId;
	}

}
