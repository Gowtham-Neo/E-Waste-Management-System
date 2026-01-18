package com.ey.dto.response;

import com.ey.enums.RequestStatus;
import com.ey.model.Collector;

public class DisposeCollectorResponse {

	private Long id;

	
	
	private String email;
	
	private String mobileNumber;
	
	private String vehicleNumber;
	private RequestStatus status;
	
	private String message;

	public Long getId() {
		return id;
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






	public String getVehicleNumber() {
		return vehicleNumber;
	}






	public void setVehicleNumber(String vehicleNumber) {
		this.vehicleNumber = vehicleNumber;
	}






	public String getMessage() {
		return message;
	}


	public void setMessage(String message) {
		this.message = message;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public RequestStatus getStatus() {
		return status;
	}

	public void setStatus(RequestStatus status) {
		this.status = status;
	}
	
	
}
