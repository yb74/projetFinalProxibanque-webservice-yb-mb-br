package com.example.demo.dto;

import java.util.Set;

import com.example.demo.model.Client;

public class ConseillerDTO {
	private Long id;
	private String name;
	private String firstName;
	private Set<Client> clients;

	public ConseillerDTO(Long id, String name, String firstName, Set<Client> clients) {
		this.id = id;
		this.name = name;
		this.firstName = firstName;
		this.clients = clients;
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

	public Set<Client> getClients() {
		return clients;
	}

	public void setClients(Set<Client> clients) {
		this.clients = clients;
	}

}