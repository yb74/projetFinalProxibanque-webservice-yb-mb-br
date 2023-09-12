package com.example.demo.dto;

import java.util.Set;

import com.example.demo.model.Client;

public class ConsolerDTO {
	private Long id;
	private String nom;
	private String prenom;
	private Set<Client> clients;

	public ConsolerDTO(Long id, String nom, String prenom, Set<Client> clients) {
		this.id = id;
		this.nom = nom;
		this.prenom = prenom;
		this.clients = clients;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public Set<Client> getClients() {
		return clients;
	}

	public void setClients(Set<Client> clients) {
		this.clients = clients;
	}

}
