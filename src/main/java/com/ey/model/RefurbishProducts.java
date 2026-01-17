package com.ey.model;


import com.ey.enums.ConditionGrading;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;

@Entity
public class RefurbishProducts {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	
	private Double price;
	
	private ConditionGrading grade;
	
	private Integer quantity;

	@OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "inspectionId")
    private Inspection inspection;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	
	public Inspection getInspection() {
		return inspection;
	}

	public void setInspection(Inspection inspection) {
		this.inspection = inspection;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public ConditionGrading getGrade() {
		return grade;
	}

	public void setGrade(ConditionGrading grade) {
		this.grade = grade;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public RefurbishProducts(Long id,Double price, ConditionGrading grade, Integer quantity) {
		super();
		this.id = id;
		this.price = price;
		this.grade = grade;
		this.quantity = quantity;
	}

	public RefurbishProducts() {
		super();
	}
	
	
	
	

	
	
	
}
