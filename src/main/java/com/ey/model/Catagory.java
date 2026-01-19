package com.ey.model;

import com.ey.enums.HazardLevel;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Catagory {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	private String name;
	@Enumerated(EnumType.STRING)
	private HazardLevel level;
	
	private boolean refurbishable;
	
	private String handlingRules;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public HazardLevel getLevel() {
		return level;
	}

	public void setLevel(HazardLevel level) {
		this.level = level;
	}

	public boolean isRefurbishable() {
		return refurbishable;
	}

	public void setRefurbishable(boolean refurbishable) {
		this.refurbishable = refurbishable;
	}

	public String getHandlingRules() {
		return handlingRules;
	}

	public void setHandlingRules(String handlingRules) {
		this.handlingRules = handlingRules;
	}

	public Catagory(Long id, String name, HazardLevel level, boolean refurbishable, String handlingRules) {
		super();
		this.id = id;
		this.name = name;
		this.level = level;
		this.refurbishable = refurbishable;
		this.handlingRules = handlingRules;
	}

	public Catagory() {
		super();
	}

	
	
	

	
	
	
}
