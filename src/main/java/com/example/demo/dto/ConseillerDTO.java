package com.example.demo.dto;

import java.util.Set;

import com.example.demo.model.Client;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public class ConseillerDTO {
	@NotNull
	private Long id;

	@NotEmpty(message = "the name of the conseiller is necessary")
	private String name;

	@NotEmpty(message = "the first name of the conseiller is necessary")
	private String firstname;

	@NotEmpty(message = "the username of the conseiller is necessary")
	private String username;
	
	@NotEmpty(message = "the password of the conseiller is necessary")
	private String password;

	private Set<Client> clients;

	public ConseillerDTO(Long id, String name, String firstname, String username, String password,
			Set<Client> clients) {
		this.id = id;
		this.name = name;
		this.firstname = firstname;
		this.username = username;
		this.password = password;
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

	public Set<Client> getClients() {
		return clients;
	}

	public void setClients(Set<Client> clients) {
		this.clients = clients;
	}

}
