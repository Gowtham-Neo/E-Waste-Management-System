package com.ey.model;

import com.ey.enums.OrderStatus;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Order {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	private Long userId;
	
	private Double totalAmount;
	
	private OrderStatus status;
	
	private Long recyclerId;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

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

	public Long getRecyclerId() {
		return recyclerId;
	}

	public void setRecyclerId(Long recyclerId) {
		this.recyclerId = recyclerId;
	}

	public Order(Long id, Long userId, Double totalAmount, OrderStatus status, Long recyclerId) {
		super();
		this.id = id;
		this.userId = userId;
		this.totalAmount = totalAmount;
		this.status = status;
		this.recyclerId = recyclerId;
	}

	public Order() {
		super();
	}

	
	
	
	
}
