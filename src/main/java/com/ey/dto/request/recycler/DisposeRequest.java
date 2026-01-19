package com.ey.dto.request.recycler;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public class DisposeRequest {

	
	
	@NotNull(message = "catagoryId is required")
	@Positive(message =" catagoryId must be in positive")
	private Long catagoryId;
	
	@NotNull(message = "quantity is required")
	@Positive(message =" quantity must be in positive")
	private Integer quantity;
	
	@NotBlank(message = "location is required")
	private String location;

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
	
		
	
	
}
