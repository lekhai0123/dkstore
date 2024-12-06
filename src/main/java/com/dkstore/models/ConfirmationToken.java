package com.dkstore.models;

import java.time.LocalDateTime;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class ConfirmationToken {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String token;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    private LocalDateTime createdAt;
    private LocalDateTime expiresAt;
	public ConfirmationToken() {
		super();
	}
	public ConfirmationToken(Long id, String token, User user, LocalDateTime createdAt, LocalDateTime expiresAt) {
		super();
		this.id = id;
		this.token = token;
		this.user = user;
		this.createdAt = createdAt;
		this.expiresAt = expiresAt;
	}
	
	public ConfirmationToken(String token, User user, LocalDateTime createdAt, LocalDateTime expiresAt) {
		super();
		this.token = token;
		this.user = user;
		this.createdAt = createdAt;
		this.expiresAt = expiresAt;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public LocalDateTime getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}
	public LocalDateTime getExpiresAt() {
		return expiresAt;
	}
	public void setExpiresAt(LocalDateTime expiresAt) {
		this.expiresAt = expiresAt;
	}

    
}

