package com.ey.dto.request;

import com.ey.enums.HazardLevel;

public class CreateCatagoryRequest {

	private String name;
	
	private HazardLevel level;
	
	private boolean refurbishable;
	
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
