package com.ey.exception;

public class UserAlreadyExsistsException extends RuntimeException{

	public UserAlreadyExsistsException(String msg) {
		super(msg);
	}
}
