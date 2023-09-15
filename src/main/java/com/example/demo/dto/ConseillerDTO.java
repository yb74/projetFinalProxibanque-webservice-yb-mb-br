package com.example.demo.dto;

import java.util.Set;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public class ConseillerDTO {
	@NotNull
	private Long id;

	@NotEmpty(message = "the name of the conseiller is necessary")
	private String name;

	@NotEmpty(message = "the first name of the client is necessary")
	private String firstName;

	private Set<Long> clientsIds;

	public ConseillerDTO(Long id, String name, String firstName, Set<Long> clientsIds) {
		this.id = id;
		this.name = name;
		this.firstName = firstName;
		this.clientsIds = clientsIds;
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

	public Set<Long> getClientsIds() {
		return clientsIds;
	}

	public void setClientsIds(Set<Long> clientsIds) {
		this.clientsIds = clientsIds;
	}

}
