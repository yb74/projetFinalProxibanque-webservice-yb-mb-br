package com.example.demo.dto;

import com.example.demo.model.Conseiller;

import jakarta.validation.Valid;
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

	@Valid
	private Conseiller conseiller;

	public ClientDTO(Long id, String name, String firstName, String adress, int zipCode, String city,
			String phoneNumber, Conseiller conseiller) {
		this.id = id;
		this.name = name;
		this.firstName = firstName;
		this.adress = adress;
		this.zipCode = zipCode;
		this.city = city;
		this.phoneNumber = phoneNumber;
		this.conseiller = conseiller;
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

	public Conseiller getConseiller() {
		return conseiller;
	}

	public void setConseiller(Conseiller conseiller) {
		this.conseiller = conseiller;
	}

}
