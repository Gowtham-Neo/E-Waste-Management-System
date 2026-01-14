package com.ey.model;

import com.ey.enums.DisposalStatus;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Certificate {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	private Long referenceId;
	
	private String s3Url;
	
	private DisposalStatus status;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getReferenceId() {
		return referenceId;
	}

	public void setReferenceId(Long referenceId) {
		this.referenceId = referenceId;
	}

	public String getS3Url() {
		return s3Url;
	}

	public void setS3Url(String s3Url) {
		this.s3Url = s3Url;
	}

	public DisposalStatus getStatus() {
		return status;
	}

	public void setStatus(DisposalStatus status) {
		this.status = status;
	}

	public Certificate(Long id, Long referenceId, String s3Url, DisposalStatus status) {
		super();
		this.id = id;
		this.referenceId = referenceId;
		this.s3Url = s3Url;
		this.status = status;
	}

	public Certificate() {
		super();
	}
	
	

	
	
	
}
