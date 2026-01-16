package com.ey.model;

import java.time.LocalDateTime;

import com.ey.enums.NotificationType;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Notification {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="userId")
	private User user;
	
	private String message;
	
	private LocalDateTime eventTime;
	
	private NotificationType status;
	
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "disposalRequestId")
    private Dispose disposeRequest;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	

	public LocalDateTime getEventTime() {
		return eventTime;
	}

	public void setEventTime(LocalDateTime eventTime) {
		this.eventTime = eventTime;
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

	
	public Notification(Long id, String message, LocalDateTime eventTime, NotificationType status) {
		super();
		this.id = id;
		this.message = message;
		this.eventTime = eventTime;
		this.status = status;
	}

	public Notification() {
		super();
	}

	
	
	
	
	
}
