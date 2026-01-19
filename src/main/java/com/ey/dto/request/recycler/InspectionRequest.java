package com.ey.dto.request.recycler;

import com.ey.enums.ConditionGrading;
import com.ey.enums.FunctionalStatus;
import com.ey.enums.ModelType;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public class InspectionRequest {

	@NotBlank(message = "brand is required")
	private String brand;
	
	@NotBlank(message = "modelName is required")
	private String modelName;
	
	@NotNull(message = "Model type is required")
	@Enumerated(EnumType.STRING)
	private ModelType type;
	
	@NotNull(message = "manufatureYear is required")
	@Positive(message="yeat must be positive")
	private Integer manufatureYear;
	
	@NotNull(message = "conditionGrade is required")
	@Enumerated(EnumType.STRING)
	private ConditionGrading conditionGrade;
	
	@NotNull(message = "status is required")
	@Enumerated(EnumType.STRING)
	private FunctionalStatus status;
	
	@NotNull(message = "estimatedRepairCost is required")
	@Positive(message="yeat must be positive")
	private Double estimatedRepairCost;
	
	@NotNull(message = "currentMarketValue is required")
	@Positive(message="yeat must be positive")
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
