package com.ey.model;

import java.time.LocalDate;

import com.ey.enums.ConditionGrading;
import com.ey.enums.RequestStatus;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class RefurbishProducts {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
		
	private Long catagoryId;
	
	private Double price;
	
	private ConditionGrading grade;
	
	private Integer quantity;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getCatagoryId() {
		return catagoryId;
	}

	public void setCatagoryId(Long catagoryId) {
		this.catagoryId = catagoryId;
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

	public RefurbishProducts(Long id, Long catagoryId, Double price, ConditionGrading grade, Integer quantity) {
		super();
		this.id = id;
		this.catagoryId = catagoryId;
		this.price = price;
		this.grade = grade;
		this.quantity = quantity;
	}

	public RefurbishProducts() {
		super();
	}
	
	
	
	

	
	
	
}
