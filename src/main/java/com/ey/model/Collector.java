package com.ey.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Collector {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	private String username;
	private String password;
	
	private String phone;
	
	private String vehiceNumber;
	
	@ManyToOne
	@JoinColumn(name="recyclerId")
	private Recycler recycler;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUserName() {
		return username;
	}

	public void setUserName(String name) {
		this.username = name;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}
	

	public String getVehiceNumber() {
		return vehiceNumber;
	}

	public void setVehiceNumber(String vehiceNumber) {
		this.vehiceNumber = vehiceNumber;
	}

	public Collector(Long id, String name, String phone,String vehicleNumber) {
		super();
		this.id = id;
		this.username = name;
		this.phone = phone;
		this.vehiceNumber=vehicleNumber;
	}

	public Collector() {
		super();
	}

	
	
	
}
