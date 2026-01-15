package com.ey.dto.response;

public class AuthResponse {
    private String token;
    private String role;
    private String principalType;
	public AuthResponse(String token, String role, String principalType) {
		super();
		this.token = token;
		this.role = role;
		this.principalType = principalType;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public String getPrincipalType() {
		return principalType;
	}
	public void setPrincipalType(String principalType) {
		this.principalType = principalType;
	}

    
}