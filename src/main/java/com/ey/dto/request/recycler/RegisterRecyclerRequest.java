package com.ey.dto.request.recycler;

import jakarta.persistence.Column;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public class RegisterRecyclerRequest {

	@NotBlank(message = "organizationName is required")
	private String organizationName;
	
	@Column(unique = true)
	@NotBlank(message = "licenceNumber is required")
	private String licenceNumber;
	
	@Column(unique = true)
	@Email(message="invalid email id")
	@NotBlank(message = "email is required")
	private String email;
	
	@NotBlank(message = "password is required")
	private String password;
	
	@NotBlank(message="mobileNumber is required")
	@Pattern(regexp = "^[6-9]\\d{9}$",message = "Invalid mobile number")
	private String mobileNumber;

	public String getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	public String getOrganizationName() {
		return organizationName;
	}

	public void setOrganizationName(String organizationName) {
		this.organizationName = organizationName;
	}

	public String getLicenceNumber() {
		return licenceNumber;
	}

	public void setLicenceNumber(String licenceNumber) {
		this.licenceNumber = licenceNumber;
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
	
	
}
