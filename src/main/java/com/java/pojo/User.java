package com.java.pojo;

import java.io.Serializable;

public class User implements Serializable {
	
	private String username;
	private String password;
	private String role;
	
	public User(String username, String password, String role) {
		super();
		this.username = username;
		this.password = password;
		this.role = role;
	}
	
	public String getUserName() {
		return username;
	}
	public void setUserName(String userName) {
		this.username = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}

	@Override
	public String toString() {
		return "User [userName=" + username + ", password=" + password + ", role=" + role + "]";
	}
	
}
