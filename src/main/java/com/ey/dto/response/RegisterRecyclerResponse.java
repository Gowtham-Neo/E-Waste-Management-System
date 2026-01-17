package com.ey.dto.response;

import com.ey.enums.RecyclerStatus;
import com.ey.enums.Role;

public class RegisterRecyclerResponse {
	
	private Long id;
	private String organizationName;
	
	private String email;
	private String licenceNumber;
	private String mobileNumber;

	private RecyclerStatus status;
	private String message;

	
	public Long getId() {
		return id;
	}
	

	public String getLicenceNumber() {
		return licenceNumber;
	}


	public void setLicenceNumber(String licenceNumber) {
		this.licenceNumber = licenceNumber;
	}


	public void setId(Long id) {
		this.id = id;
	}

	public String getOrganizationName() {
		return organizationName;
	}

	public void setOrganizationName(String organizationName) {
		this.organizationName = organizationName;
	}


	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	
	

	public RecyclerStatus getStatus() {
		return status;
	}

	public void setStatus(RecyclerStatus status) {
		this.status = status;
	}

	public String getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
	
}
