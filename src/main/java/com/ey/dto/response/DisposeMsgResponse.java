package com.ey.dto.response;

import java.time.LocalDate;

import com.ey.enums.RequestStatus;
import com.ey.model.Catagory;

public class DisposeMsgResponse {

	private Long id;
	
	private Catagory catagory;
	
	private Integer quantity;
	
	private String location;
	
	private LocalDate requestDate;
	
	private RequestStatus status;
	
	private String message;

	public Long getId() {
		return id;
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

	public Catagory getCatagory() {
		return catagory;
	}

	public void setCatagory(Catagory catagory) {
		this.catagory = catagory;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public LocalDate getRequestDate() {
		return requestDate;
	}

	public void setRequestDate(LocalDate requestDate) {
		this.requestDate = requestDate;
	}

	public RequestStatus getStatus() {
		return status;
	}

	public void setStatus(RequestStatus status) {
		this.status = status;
	}
	
	
}
