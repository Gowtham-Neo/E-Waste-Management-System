package com.ey.dto.response;

public class MaterialsResponse {

	
	private Long id;
	
	private String materialType;
	private Double weightInKg;
	private Boolean hazardous;
	private String recoveryMethod;
	private Double estimatedValue;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
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
