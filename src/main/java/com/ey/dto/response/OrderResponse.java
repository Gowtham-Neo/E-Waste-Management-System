package com.ey.dto.response;


import com.ey.enums.ConditionGrading;
import com.ey.enums.ModelType;
import com.ey.enums.OrderStatus;


public class OrderResponse {

	private Long id;
	
	
	private Double price;
	
	private Integer quantity;
	private Double totalAmount;
	
	private OrderStatus status;

    
    private String brand;
	
	private String modelName;
	
	private ModelType type;
	
	private Integer manufatureYear;
	

	
	
	
	public Double getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(Double totalAmount) {
		this.totalAmount = totalAmount;
	}

	public OrderStatus getStatus() {
		return status;
	}

	public void setStatus(OrderStatus status) {
		this.status = status;
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


	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public OrderResponse(Long id,Double price, ConditionGrading grade, Integer quantity) {
		super();
		this.id = id;
		this.price = price;
		this.quantity = quantity;
	}

	public OrderResponse() {
		super();
	}
	
	
	
	

	
	
	
}
