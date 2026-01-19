package com.ey.model;

import java.time.LocalDate;

import com.ey.enums.RequestStatus;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;

@Entity
public class Dispose {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne
	@JoinColumn(name = "userId")
	private User user;
	
	@ManyToOne
	@JoinColumn(name="catagoryId")
	private Catagory catagory;
	
	private Integer quantity;
	
	private String location;
	
	private LocalDate requestDate;
	@Enumerated(EnumType.STRING)
	private RequestStatus status;
	
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "collectorId")
    private Collector collector;

    @OneToOne(mappedBy = "disposeRequest",fetch = FetchType.LAZY)
    private Inspection inspection;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	
	

	public Inspection getInspection() {
		return inspection;
	}

	public void setInspection(Inspection inspection) {
		this.inspection = inspection;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Catagory getCatagory() {
		return catagory;
	}

	public void setCatagory(Catagory catagory) {
		this.catagory = catagory;
	}

	public Collector getCollector() {
		return collector;
	}

	public void setCollector(Collector collector) {
		this.collector = collector;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public LocalDate getRequestDate() {
		return requestDate;
	}

	public void setRequestDate(LocalDate requestDate) {
		this.requestDate = requestDate;
	}

	public RequestStatus getStatus() {
		return status;
	}

	public void setStatus(RequestStatus status) {
		this.status = status;
	}

	public Dispose(Long id, Integer quantity, String location,
			LocalDate requestDate, RequestStatus status) {
		super();
		this.id = id;
		this.quantity = quantity;
		this.location = location;
		this.requestDate = requestDate;
		this.status = status;
	}

	public Dispose() {
		super();
	}
	
	

	
	
	
}
