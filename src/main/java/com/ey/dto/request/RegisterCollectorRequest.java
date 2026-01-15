package com.ey.dto.request;

import jakarta.persistence.Column;

public class RegisterCollectorRequest {

	@Column(unique=true, nullable=false)
	private String email;
	private String password;
	
	private String mobileNumber;
	
	private String vehicleNumber;

	public String getEmail() {
		return email;
	}

	public void setEmail(String username) {
		this.email = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	public String getVehicleNumber() {
		return vehicleNumber;
	}

	public void setVehicleNumber(String vehiceNumber) {
		this.vehicleNumber = vehiceNumber;
	}
	
	
}
