package com.ey.dto.response;

import com.ey.enums.HazardLevel;

public class RegisterCatagoryResponse {
	private Long id;
		
		private String name;
		
		private HazardLevel level;
		
		private boolean refurbishable;
		
		private String handlingRules;
		
		private String message;

		
		public String getMessage() {
			return message;
		}

		public void setMessage(String message) {
			this.message = message;
		}

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
		
		
}

