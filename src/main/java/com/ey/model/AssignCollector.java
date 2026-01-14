package com.ey.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class AssignCollector {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	private Long disposeRequestId;
	
	private Long collectorId;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getDisposeRequestId() {
		return disposeRequestId;
	}

	public void setDisposeRequestId(Long disposeRequestId) {
		this.disposeRequestId = disposeRequestId;
	}

	public Long getCollectorId() {
		return collectorId;
	}

	public void setCollectorId(Long collectorId) {
		this.collectorId = collectorId;
	}

	public AssignCollector(Long id, Long disposeRequestId, Long collectorId) {
		super();
		this.id = id;
		this.disposeRequestId = disposeRequestId;
		this.collectorId = collectorId;
	}

	public AssignCollector() {
		super();
	}
	
	
	
	
	
}
