package com.couponsystem.entities;

import com.couponsystem.services.ClientService.ClientType;

public class UserDetails {

	public int id;
	public String email;
	public String name;
	public String password;
	public ClientType userType;

	public UserDetails(int id, String email, String password, ClientType userType, String name) {
		super();
		this.id = id;
		this.email = email;
		this.password = password;
		this.userType = userType;
		this.name = name;
	}

	public UserDetails(int id, String email, String password, ClientType userType) {
		super();
		this.id = id;
		this.email = email;
		this.password = password;
		this.userType = userType;

	}

	public UserDetails(String email, String password, ClientType userType) {
		super();
		this.email = email;
		this.password = password;
		this.userType = userType;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public ClientType getUserType() {
		return userType;
	}

	public void setUserType(ClientType userType) {
		this.userType = userType;
	}

}
