package com.ey.model;

import java.util.List;

import com.ey.enums.Role;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class User {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	private String organizationName;
	
	private String name;
	
	private String email;
	
	private String password;
	
	private String mobileNumber;
	
	private Role role;

	@OneToMany(mappedBy = "user")
    private List<Dispose> disposalRequests;
	
	@OneToMany(mappedBy = "buyer")
    private List<Order> orders;
	
	public User(Long id, String organizationName, String name, String email, String password, String mobileNumber,
			Role role) {
		super();
		this.id = id;
		this.organizationName = organizationName;
		this.name = name;
		this.email = email;
		this.password = password;
		this.mobileNumber = mobileNumber;
		this.role = role;
	}

	
	public User() {
		super();
	}


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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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

	public String getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}
	
	
}
