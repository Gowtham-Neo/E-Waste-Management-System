package com.ey.dto.request.user;

import jakarta.persistence.Column;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public class UserForgetPassordRequest {

	@Column(unique=true)
	@NotBlank(message= "organizationName is required")
	@Email(message="Invalid email id")
	private String email;
	
	@NotBlank(message= "new password is required")
	private String newPassword;
	
	@NotBlank(message= "confirm password is required")
	private String confirmPassword;

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getNewPassword() {
		return newPassword;
	}

	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}

	public String getConfirmPassword() {
		return confirmPassword;
	}

	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}
	
	
	
}
