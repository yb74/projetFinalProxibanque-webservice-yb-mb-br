package com.example.demo.model;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotEmpty;

@Entity
public class Consoler {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotEmpty(message = "the name of the consoler is necessary")
	private String name;

	@NotEmpty(message = "the first name of the client is necessary")
	private String firstName;

	@OneToMany(mappedBy = "consoler", cascade = { CascadeType.PERSIST })
	private Set<Client> clients = new HashSet<>();

	public Consoler() {
	}

	public Consoler(Long id, String name, String firstName, Set<Client> clients) {
		this.id = id;
		this.name = name;
		this.firstName = firstName;
		this.clients = clients;
	}
	
	public Consoler(String name, String firstName) {
		this.name = name;
		this.firstName = firstName;
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

	public void addClient(Client c) {
		clients.add(c);
		c.setConsoler(this);
	}

}
