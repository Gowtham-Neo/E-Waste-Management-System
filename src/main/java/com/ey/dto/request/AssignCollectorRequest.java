package com.ey.dto.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public class AssignCollectorRequest {

	

	@NotNull(message = "Collector Id Required")
	@Positive(message ="Id must be in positive")
	private Long collectorId;



	public Long getCollectorId() {
		return collectorId;
	}

	public void setCollectorId(Long collectorId) {
		this.collectorId = collectorId;
	}
	
	
	
}
