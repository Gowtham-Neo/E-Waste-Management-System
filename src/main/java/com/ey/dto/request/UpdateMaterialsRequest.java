package com.ey.dto.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public class UpdateMaterialsRequest {

	

	
	@NotNull(message = "weight is required")
	@Positive(message =" weight must be in positive")
	private Double weightInKg;
	
	@NotNull(message = "estimatedValue is required")
	@Positive(message =" estimatedValue must be in positive")
	private Double estimatedValue;

	public Double getWeightInKg() {
		return weightInKg;
	}
	public void setWeightInKg(Double weightInKg) {
		this.weightInKg = weightInKg;
	}

	public Double getEstimatedValue() {
		return estimatedValue;
	}
	public void setEstimatedValue(Double estimatedValue) {
		this.estimatedValue = estimatedValue;
	}

	
	
}
