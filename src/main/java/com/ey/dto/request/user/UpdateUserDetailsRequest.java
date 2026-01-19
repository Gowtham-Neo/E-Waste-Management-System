package com.ey.dto.request.user;

import jakarta.persistence.Column;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public class UpdateUserDetailsRequest {

	@NotBlank(message= "organizationName is required")
	private String organizationName;
	
	@NotBlank(message= "name is required")
	private String name;
	
	@Column(unique=true)
	@NotBlank(message= "email is required")
	@Email(message="Invalid email id")
	private String email;
	
	@NotBlank(message="mobileNumber is required")
	@Pattern(regexp = "^[6-9]\\d{9}$",message = "Invalid mobile number")
	@Column(unique=true)
	private String mobileNumber;
	

	public String getOrganizationName() {
		return organizationName;
	}

	public void setOrganizationName(String organizationName) {
		this.organizationName = organizationName;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	
}
