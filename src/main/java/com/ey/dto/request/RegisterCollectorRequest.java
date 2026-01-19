package com.ey.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public class RegisterCollectorRequest {

	@NotBlank(message="email is required")
	@Email(message="invalid email id")
	private String email;
	
	@NotBlank(message="password is required")
	private String password;
	
	@NotBlank(message="mobileNumber is required")
	@Pattern(regexp = "^[6-9]\\d{9}$",message = "Invalid mobile number")
	private String mobileNumber;
	
	@NotBlank(message="vehicleNumber is required")
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
