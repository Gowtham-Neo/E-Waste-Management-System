package com.ey.mapper;

import com.ey.dto.request.AddMaterialsRequest;
import com.ey.dto.response.MaterialsResponse;
import com.ey.model.Inspection;
import com.ey.model.RecycledMaterials;

public class RecycledMaterialsMapper {

	
	
	public static RecycledMaterials toEntity(AddMaterialsRequest req,Inspection ip) {
		
		RecycledMaterials rm=new RecycledMaterials();
		rm.setEstimatedValue(req.getEstimatedValue());
		rm.setInspection(ip);
		rm.setMaterialType(req.getMaterialType());
		rm.setRecoveryMethod(req.getRecoveryMethod());
		rm.setWeightInKg(req.getWeightInKg());
		rm.setHazardous(req.getHazardous());
	

		return rm;
	}
	
	public static MaterialsResponse toResponse(RecycledMaterials req) {
		MaterialsResponse res=new MaterialsResponse();
		
		res.setEstimatedValue(req.getEstimatedValue());
		res.setHazardous(req.isHazardous());
		res.setId(req.getId());
		res.setMaterialType(req.getMaterialType());
		res.setRecoveryMethod(req.getRecoveryMethod());
		res.setWeightInKg(req.getWeightInKg());
		
		
		return res;
	}
	
	
	
}
