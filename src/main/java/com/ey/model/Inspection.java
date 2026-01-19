package com.ey.model;

import java.time.LocalDate;

import com.ey.enums.ConditionGrading;
import com.ey.enums.DisposalStatus;
import com.ey.enums.FunctionalStatus;
import com.ey.enums.ModelType;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;

@Entity
public class Inspection {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	private String brand;
	
	private String modelName;
	@Enumerated(EnumType.STRING)
	private ModelType type;
	
	private Integer manufatureYear;
	@Enumerated(EnumType.STRING)
	private ConditionGrading conditionGrade;
	@Enumerated(EnumType.STRING)
	private FunctionalStatus status;
	
	private Double estimatedRepairCost;
	
	private Double currentMarketValue;
	@Enumerated(EnumType.STRING)
	private	DisposalStatus decision;
	
	private LocalDate inspectedAt;

	@OneToOne
    @JoinColumn(name = "disposalRequestId")
    private Dispose disposeRequest;
	
	@OneToOne(mappedBy = "inspection",fetch = FetchType.LAZY,cascade = CascadeType.ALL)
	private RefurbishProducts refurbishedProduct;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	
	

	public Dispose getDisposeRequest() {
		return disposeRequest;
	}

	public void setDisposeRequest(Dispose disposeRequest) {
		this.disposeRequest = disposeRequest;
	}

	public ModelType getType() {
		return type;
	}

	public void setType(ModelType type) {
		this.type = type;
	}

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

	public DisposalStatus getDecision() {
		return decision;
	}

	public void setDecision(DisposalStatus decision) {
		this.decision = decision;
	}

	public LocalDate getInspectedAt() {
		return inspectedAt;
	}

	public void setInspectedAt(LocalDate inspectedAt) {
		this.inspectedAt = inspectedAt;
	}

	public Inspection(Long id, String brand, String modelName, Integer manufatureYear,
			ConditionGrading conditionGrade, FunctionalStatus status, Double estimatedRepairCost,
			Double currentMarketValue, DisposalStatus decision, LocalDate inspectedAt) {
		super();
		this.id = id;
		this.brand = brand;
		this.modelName = modelName;
		this.manufatureYear = manufatureYear;
		this.conditionGrade = conditionGrade;
		this.status = status;
		this.estimatedRepairCost = estimatedRepairCost;
		this.currentMarketValue = currentMarketValue;
		this.decision = decision;
		this.inspectedAt = inspectedAt;
	}

	public Inspection() {
		super();
	}

	
	
	

	
	
	
}
