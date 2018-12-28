package com.gxwsxx.xiaoshou.model;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonProperty;

public class UserModel {

	String id;
	String name;
	@JsonProperty("userName")
	String username;
	String password;
	String token;
	String email;
	Role role;
	LocalDateTime addTime;
	Status status;

	public enum Status {
		NORMAL, DELETE
	}

	public enum Role {
		ADMIN, SALER;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public LocalDateTime getAddTime() {
		return addTime;
	}

	public void setAddTime(LocalDateTime addTime) {
		this.addTime = addTime;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

}
