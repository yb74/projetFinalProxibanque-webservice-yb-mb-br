package com.example.demo.model;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class Conseiller {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String name;
	private String firstname;

	private String username;
	private String password;

	@OneToMany(mappedBy = "conseiller", cascade = { CascadeType.PERSIST })
	private Set<Client> clients = new HashSet<>();

	public Conseiller() {
	}

	public Conseiller(Long id, String name, String firstname, String username, String password, Set<Client> clients) {
		this.id = id;
		this.name = name;
		this.firstname = firstname;
		this.username = username;
		this.password = password;
		this.clients = clients;
	}

	public Conseiller(Long id, String name, String firstname, Set<Client> clients) {
		this.id = id;
		this.name = name;
		this.firstname = firstname;
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

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public Set<Client> getClients() {
		return clients;
	}

	public void setClients(Set<Client> clients) {
		this.clients = clients;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void addClient(Client c) {
		clients.add(c);
		c.setConseiller(this);
	}

}
