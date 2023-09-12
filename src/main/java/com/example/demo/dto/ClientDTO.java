package com.example.demo.dto;

import com.example.demo.model.Consoler;

public class ClientDTO {
	private Long id;
	private String name;
	private String firstname;
	private String adress;
	private int zipCode;
	private String city;
	private String phoneNumber;
	private Consoler consoler;

	public ClientDTO(Long id, String name, String firstname, String adress, int zipCode, String city,
			String phoneNumber, Consoler consoler) {
		this.id = id;
		this.name = name;
		this.firstname = firstname;
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

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
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

}
