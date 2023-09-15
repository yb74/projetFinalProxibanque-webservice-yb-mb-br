package com.example.demo.dto;

import com.example.demo.model.Role;

public class UserDTO {
	private Long id;
	private String username;
	private String password;
	private Role role;
	private Long conseillerId;

	public UserDTO(Long id, String username, String password, Role role, Long conseillerId) {
		this.id = id;
		this.username = username;
		this.password = password;
		this.role = role;
		this.conseillerId = conseillerId;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public Long getConseillerId() {
		return conseillerId;
	}

	public void setConseillerId(Long conseillerId) {
		this.conseillerId = conseillerId;
	}

}
