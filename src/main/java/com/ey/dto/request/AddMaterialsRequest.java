package com.ey.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public class AddMaterialsRequest {

	

	@NotBlank(message = "Material type is required")
	private String materialType;
	
	@NotNull(message = "weight is required")
	@Positive(message =" weight must be in positive")
	private Double weightInKg;
	
	@NotNull(message = "hazardous is required")
	private Boolean hazardous;
	
	@NotBlank(message = "recoveryMethod type is required")
	private String recoveryMethod;
	
	@NotNull(message = "estimated value is required")
	@Positive(message ="estimated value must be in positive")
	private Double estimatedValue;
	
	public String getMaterialType() {
		return materialType;
	}
	public void setMaterialType(String materialType) {
		this.materialType = materialType;
	}
	public Double getWeightInKg() {
		return weightInKg;
	}
	public void setWeightInKg(Double weightInKg) {
		this.weightInKg = weightInKg;
	}
	public Boolean getHazardous() {
		return hazardous;
	}
	public void setHazardous(Boolean hazardous) {
		this.hazardous = hazardous;
	}
	public String getRecoveryMethod() {
		return recoveryMethod;
	}
	public void setRecoveryMethod(String recoveryMethod) {
		this.recoveryMethod = recoveryMethod;
	}
	public Double getEstimatedValue() {
		return estimatedValue;
	}
	public void setEstimatedValue(Double estimatedValue) {
		this.estimatedValue = estimatedValue;
	}

	
	
}
