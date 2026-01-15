package com.ey.dto.request.recycler;

import java.time.LocalDate;

import com.ey.enums.ConditionGrading;
import com.ey.enums.FunctionalStatus;
import com.ey.enums.ModelType;

public class InspectionRequest {

	private String brand;
	
	private String modelName;
	
	private ModelType type;
	
	private Integer manufatureYear;
	
	private ConditionGrading conditionGrade;
	
	private FunctionalStatus status;
	
	private Double estimatedRepairCost;
	
	private Double currentMarketValue;
	
	private LocalDate inspectedAt;

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

	public LocalDate getInspectedAt() {
		return inspectedAt;
	}

	public void setInspectedAt(LocalDate inspectedAt) {
		this.inspectedAt = inspectedAt;
	}
	
	
	
	
}
