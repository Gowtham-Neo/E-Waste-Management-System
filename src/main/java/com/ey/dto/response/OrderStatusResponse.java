package com.ey.dto.response;


import com.ey.enums.OrderStatus;


public class OrderStatusResponse {

	private Long id;
	
	private OrderStatus status;

	
	private String message;
	
	

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public OrderStatus getStatus() {
		return status;
	}

	public void setStatus(OrderStatus status) {
		this.status = status;
	}

	
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	
	

	
	

	
	
	
}
