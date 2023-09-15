package com.example.demo.model;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;

@Entity
public class Conseiller {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String name;
	private String firstName;

	@OneToMany(mappedBy = "conseiller", cascade = { CascadeType.PERSIST })
	private Set<Client> clients = new HashSet<>();

	@OneToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name = "user_id")
	private UserModel user;

	private Set<Long> clientsIds = new HashSet<>();

	public Conseiller() {
	}

	public Conseiller(Long id, String name, String firstName, Set<Long> clientsIds) {
		this.id = id;
		this.name = name;
		this.firstName = firstName;
		this.clientsIds = clientsIds;
	}

	public Conseiller(Long id, String name, String firstName, Set<Client> clients, UserModel user) {
		this.id = id;
		this.name = name;
		this.firstName = firstName;
		this.clients = clients;
		this.user = user;
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

	public UserModel getUser() {
		return user;
	}

	public void setUser(UserModel user) {
		this.user = user;
	}

	public Set<Long> getClientsIds() {
		return clientsIds;
	}

	public void setClientsIds(Set<Long> clientsIds) {
		this.clientsIds = clientsIds;
	}

	public void addClient(Client c) {
		clients.add(c);
		c.setConseiller(this);
	}

}
