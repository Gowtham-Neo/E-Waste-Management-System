package com.ey.mapper;

import java.time.LocalDate;

import com.ey.dto.request.CreateInspectRequest;
import com.ey.dto.response.InspectResponse;
import com.ey.model.Inspection;

public class InspectionMapper {

	
	
	public static Inspection toEntity(CreateInspectRequest req) {
		
		Inspection ip=new Inspection();
		ip.setBrand(req.getBrand());
		ip.setConditionGrade(req.getConditionGrade());
		ip.setCurrentMarketValue(req.getCurrentMarketValue());
		ip.setEstimatedRepairCost(req.getEstimatedRepairCost());
		ip.setInspectedAt(LocalDate.now());
		ip.setManufatureYear(req.getManufatureYear());
		ip.setModelName(req.getModelName());
		ip.setStatus(req.getStatus());
		ip.setType(req.getType());
		
		
		return ip;
	}
	
	public static InspectResponse toResponse(Inspection req,String msg) {
		InspectResponse ip=new InspectResponse();
		
		ip.setBrand(req.getBrand());
		ip.setConditionGrade(req.getConditionGrade());
		ip.setCurrentMarketValue(req.getCurrentMarketValue());
		ip.setEstimatedRepairCost(req.getEstimatedRepairCost());
		ip.setInspectedAt(req.getInspectedAt());
		ip.setManufatureYear(req.getManufatureYear());
		ip.setModelName(req.getModelName());
		ip.setStatus(req.getStatus());
		ip.setId(req.getId());
		ip.setType(req.getType());
		ip.setDecision(req.getDecision());
		ip.setId(req.getId());
		ip.setMessage(msg);
		
		
		return ip;
	}
	
}
