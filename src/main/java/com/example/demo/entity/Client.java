package com.example.demo.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotEmpty;

@Entity
public class Client {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotEmpty(message = "the name of the client is necessary")
	private String name;
	
	@NotEmpty(message = "the first name of the client is necessary")
	private String firstName;
	
	@NotEmpty(message = "the adress name of the client is necessary")
	private String adress;
	
	@NotEmpty(message = "the zip code of the client is necessary")
	private int zipCode;
	
	@NotEmpty(message = "the city of the client is necessary")
	private String city;
	
	@NotEmpty(message = "the phone number of the client is necessary")
	private String phoneNumber;
	
	@JsonIgnore
	@ManyToOne(cascade = { CascadeType.PERSIST })
	@JoinColumn(name = "conseiller_id")
	private Consoler consoler;

	public Client() {
	}
	
	public Client(Long id, String name, String firstName, String adress, int zipCode, String city, String phoneNumber,
			Consoler consoler) {
		this.id = id;
		this.name = name;
		this.firstName = firstName;
		this.adress = adress;
		this.zipCode = zipCode;
		this.city = city;
		this.phoneNumber = phoneNumber;
		this.consoler = consoler;
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

	public Consoler getConsoler() {
		return consoler;
	}

	public void setConsoler(Consoler consoler) {
		this.consoler = consoler;
	}

	@Override
	public String toString() {
		return "Client [id=" + id + ", name=" + name + ", firstName=" + firstName + ", adress=" + adress + ", zipCode="
				+ zipCode + ", city=" + city + ", phoneNumber=" + phoneNumber + ", consoler=" + consoler + "]";
	}

}
