package com.ey.dto.request.user;

import com.ey.enums.Role;

import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public class RegisterUserRequest {

	@NotBlank(message= "organizationName is required")
	private String organizationName;
	
	@NotBlank(message= "name is required")
	private String name;
	
	@Column(unique=true,nullable = false)
	@NotBlank(message= "email is required")
	@Email(message="Invlaid email id")
	private String email;
	
	@NotBlank(message= "password is required")
	private String password;
	
	@Column(unique=true,nullable = false)
	@NotBlank(message="mobileNumber is required")
	@Pattern(regexp = "^[6-9]\\d{9}$",message = "Invalid mobile number")
	private String mobileNumber;
	
	@NotNull(message="role is required")
	@Enumerated(EnumType.STRING)
	private Role role;

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

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}
	
	
}
