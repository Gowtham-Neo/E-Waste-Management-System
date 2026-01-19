package com.ey.dto.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public class CreateOrderRequest {

	

	@NotNull(message="quality is required")
	@Positive(message="quality must be positive")
	private Integer quantity;

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}



	
	
}
