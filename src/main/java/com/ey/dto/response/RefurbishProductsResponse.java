package com.ey.dto.response;


import com.ey.enums.ConditionGrading;
import com.ey.enums.ModelType;


public class RefurbishProductsResponse {

	private Long id;
	
	
	private Double price;
	
	private ConditionGrading grade;
	
	private Integer quantity;
    
    private String brand;
	
	private String modelName;
	
	private ModelType type;
	
	private Integer manufatureYear;
	

	
	
	
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

	

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	private String message;

	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public RefurbishProductsResponse(Long id,Double price, ConditionGrading grade, Integer quantity) {
		super();
		this.id = id;
		this.price = price;
		this.grade = grade;
		this.quantity = quantity;
	}

	public RefurbishProductsResponse() {
		super();
	}
	
	
	
	

	
	
	
}
