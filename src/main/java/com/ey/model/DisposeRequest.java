package com.ey.model;

import java.time.LocalDate;

import com.ey.enums.RequestStatus;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class DisposeRequest {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	private Long userId;
	
	private Long catagoryId;
	
	private Integer quantity;
	
	private String location;
	
	private LocalDate requestDate;
	
	private RequestStatus status;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Long getCatagoryId() {
		return catagoryId;
	}

	public void setCatagoryId(Long catagoryId) {
		this.catagoryId = catagoryId;
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

	public DisposeRequest(Long id, Long userId, Long catagoryId, Integer quantity, String location,
			LocalDate requestDate, RequestStatus status) {
		super();
		this.id = id;
		this.userId = userId;
		this.catagoryId = catagoryId;
		this.quantity = quantity;
		this.location = location;
		this.requestDate = requestDate;
		this.status = status;
	}

	public DisposeRequest() {
		super();
	}
	
	

	
	
	
}
