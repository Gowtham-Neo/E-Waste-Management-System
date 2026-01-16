package com.ey.dto.response;

import com.ey.enums.RequestStatus;
import com.ey.model.Collector;

public class DisposeCollectorResponse {

	private Long id;

	private RequestStatus status;
	
	private String message;
	
	private CollectorResponse collector;

	public Long getId() {
		return id;
	}

	
	

	public CollectorResponse getCollector() {
		return collector;
	}




	public void setCollector(CollectorResponse collector) {
		this.collector = collector;
	}




	public String getMessage() {
		return message;
	}


	public void setMessage(String message) {
		this.message = message;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public RequestStatus getStatus() {
		return status;
	}

	public void setStatus(RequestStatus status) {
		this.status = status;
	}
	
	
}
