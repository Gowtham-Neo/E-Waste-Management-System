package com.ey.exception;

public class OrderNotFound extends RuntimeException{

	public OrderNotFound(String msg) {
		super(msg);
	}
}
