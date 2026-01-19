package com.ey.dto.request.user;

import jakarta.validation.constraints.NotBlank;

public class UserResetPassordRequest {

	@NotBlank(message= "new password is required")
	private String newPassword;
	
	@NotBlank(message= "confirm password is required")
	private String confirmPassword;

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
