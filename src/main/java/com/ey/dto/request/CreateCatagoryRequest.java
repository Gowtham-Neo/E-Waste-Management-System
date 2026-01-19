package com.ey.dto.request;

import com.ey.enums.HazardLevel;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class CreateCatagoryRequest {

	@NotBlank(message= "name is required")
	private String name;
	
	@NotNull(message = "Level is required")
	@Enumerated(EnumType.STRING)
	private HazardLevel level;
	
	@NotNull(message = "refurbishable is required")
	private boolean refurbishable;
	
	@NotBlank(message= "handlingRules is required")
	private String handlingRules;

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
	
	
}
