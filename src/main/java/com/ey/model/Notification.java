package com.ey.model;

import com.ey.enums.NotificationType;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Notification {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	private Long userId;
	
	private String message;
	
	private NotificationType status;

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

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public NotificationType getStatus() {
		return status;
	}

	public void setStatus(NotificationType status) {
		this.status = status;
	}

	public Notification(Long id, Long userId, String message, NotificationType status) {
		super();
		this.id = id;
		this.userId = userId;
		this.message = message;
		this.status = status;
	}

	public Notification() {
		super();
	}

	
	
	
	
	
}
