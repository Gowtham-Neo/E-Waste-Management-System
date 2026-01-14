package com.ey.model;

import com.ey.enums.RecyclerStatus;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Recycler {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	private String organizationName;
	
	private String licenceNumber;
	
	private RecyclerStatus status;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getOrganizationName() {
		return organizationName;
	}

	public void setOrganizationName(String organizationName) {
		this.organizationName = organizationName;
	}

	public String getLicenceNumber() {
		return licenceNumber;
	}

	public void setLicenceNumber(String licenceNumber) {
		this.licenceNumber = licenceNumber;
	}

	public RecyclerStatus getStatus() {
		return status;
	}

	public void setStatus(RecyclerStatus status) {
		this.status = status;
	}

	public Recycler(Long id, String organizationName, String licenceNumber, RecyclerStatus status) {
		super();
		this.id = id;
		this.organizationName = organizationName;
		this.licenceNumber = licenceNumber;
		this.status = status;
	}

	public Recycler() {
		super();
	}
	
	
}
