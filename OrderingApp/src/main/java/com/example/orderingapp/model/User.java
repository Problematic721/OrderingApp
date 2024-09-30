package com.example.orderingapp.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

@Entity
public class User {
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	private long id;
	
	@NotBlank (message = "Username is required")
	private String username;
	
	@NotBlank (message = "Password is required")
	@Size(min = 6, max = 20, message = "Password must be between 6 and 20 characters")
	private String password;
	
	@Enumerated(EnumType.STRING)
    private Role role;

	public long getId() {
		return id;
	}

	public void setId(int id) {
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
	
	
}
