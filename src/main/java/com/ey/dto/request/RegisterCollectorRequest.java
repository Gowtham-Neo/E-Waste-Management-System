package com.ey.dto.request;

import jakarta.persistence.Column;

public class RegisterCollectorRequest {

	@Column(unique=true, nullable=false)
	private String username;
	private String password;
	
	private String phone;
	
	private String vehiceNumber;

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

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getVehiceNumber() {
		return vehiceNumber;
	}

	public void setVehiceNumber(String vehiceNumber) {
		this.vehiceNumber = vehiceNumber;
	}
	
	
}
