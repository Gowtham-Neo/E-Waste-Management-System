package com.ey.dto.request;


import com.ey.enums.ConditionGrading;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public class UpdateRefurbishProducts {

	
	@NotNull(message = "quantity is required")
	@Positive(message =" quantity must be in positive")
	private Integer quantity;

	
	
	
	
	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public UpdateRefurbishProducts(Long id,Double price, ConditionGrading grade, Integer quantity) {
		super();
		
		this.quantity = quantity;
	}

	public UpdateRefurbishProducts() {
		super();
	}
	
	
	
	

	
	
	
}
