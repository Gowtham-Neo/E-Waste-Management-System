package com.ey.model;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;

@Entity
public class RecycledMaterials {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;


    private String materialType;
	private Double weightInKg;
	
	private boolean hazardous;
	private String recoveryMethod;
	private Double estimatedValue;

    @OneToOne
    private Inspection inspection;

	public Long getId() {
		return id;
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

	public boolean isHazardous() {
		return hazardous;
	}

	public void setHazardous(boolean hazardous) {
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

	public void setId(Long id) {
		this.id = id;
	}

	
	public Inspection getInspection() {
		return inspection;
	}

	public void setInspection(Inspection inspection) {
		this.inspection = inspection;
	};
	
	
	
	

	
	
	
}
