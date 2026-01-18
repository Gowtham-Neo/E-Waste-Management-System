package com.ey.dto.request;


import com.ey.enums.ConditionGrading;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;

public class UpdateRefurbishProducts {

	
	
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
