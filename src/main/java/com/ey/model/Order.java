package com.ey.model;

import com.ey.enums.OrderStatus;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="orders")
public class Order {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="userId")
	private User buyer;
	
	private Integer quantity;
	private Double totalAmount;
	
	@Enumerated(EnumType.STRING)
	private OrderStatus status;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="productId")
	private RefurbishProducts products;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	

	public User getBuyer() {
		return buyer;
	}

	public void setBuyer(User buyer) {
		this.buyer = buyer;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public RefurbishProducts getProducts() {
		return products;
	}

	public void setProducts(RefurbishProducts products) {
		this.products = products;
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


	public Order(Long id, Long userId, Double totalAmount, OrderStatus status, Long recyclerId) {
		super();
		this.id = id;
		
		this.totalAmount = totalAmount;
		this.status = status;
		
	}

	public Order() {
		super();
	}

	
	
	
	
}
