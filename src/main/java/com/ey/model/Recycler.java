package com.ey.model;

import java.util.List;


import com.ey.enums.RecyclerStatus;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class Recycler {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	private String organizationName;
	@Column(unique = true)
	private String licenceNumber;
	@Column(unique=true,nullable = false)
	private String email;
	
	private String password;
	
	@Enumerated(EnumType.STRING)
	private RecyclerStatus status;
	@Column(unique=true,nullable = false)
	private String mobileNumber;
	
	
	
	@OneToMany(mappedBy="recycler")
	private List<Collector> collectors;

	
	
	public String getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
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
