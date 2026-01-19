package com.ey.dto.request;

import com.ey.enums.ConditionGrading;
import com.ey.enums.FunctionalStatus;
import com.ey.enums.ModelType;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public class CreateInspectRequest {

	@NotBlank(message= "brand is required")
	private String brand;
	
	@NotBlank(message= "brand is required")
	private String modelName;
	
	@NotNull(message="model type is requid")
	@Enumerated(EnumType.STRING)
	private ModelType type;
	
	@NotNull(message="manufatureYear type is requid")
	@Positive(message="year must be positive")
	private Integer manufatureYear;
	
	@NotNull(message="conditionGrade type is requid")
	@Enumerated(EnumType.STRING)
	private ConditionGrading conditionGrade;
	
	@NotNull(message="model type is requid")
	@Enumerated(EnumType.STRING)
	private FunctionalStatus status;
	
	@NotNull(message="model type is requid")
	@Positive(message="estimatedRepairCost must be positive")
	private Double estimatedRepairCost;
	
	@NotNull(message="model type is requid")
	@Positive(message="currentMarketValue must be positive")
	private Double currentMarketValue;

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public String getModelName() {
		return modelName;
	}

	public void setModelName(String modelName) {
		this.modelName = modelName;
	}

	public ModelType getType() {
		return type;
	}

	public void setType(ModelType type) {
		this.type = type;
	}

	public Integer getManufatureYear() {
		return manufatureYear;
	}

	public void setManufatureYear(Integer manufatureYear) {
		this.manufatureYear = manufatureYear;
	}

	public ConditionGrading getConditionGrade() {
		return conditionGrade;
	}

	public void setConditionGrade(ConditionGrading conditionGrade) {
		this.conditionGrade = conditionGrade;
	}

	public FunctionalStatus getStatus() {
		return status;
	}

	public void setStatus(FunctionalStatus status) {
		this.status = status;
	}

	public Double getEstimatedRepairCost() {
		return estimatedRepairCost;
	}

	public void setEstimatedRepairCost(Double estimatedRepairCost) {
		this.estimatedRepairCost = estimatedRepairCost;
	}

	public Double getCurrentMarketValue() {
		return currentMarketValue;
	}

	public void setCurrentMarketValue(Double currentMarketValue) {
		this.currentMarketValue = currentMarketValue;
	}
	
	
}
