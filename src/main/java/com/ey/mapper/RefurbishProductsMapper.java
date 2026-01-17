package com.ey.mapper;

import com.ey.dto.response.DisposeResponse;
import com.ey.dto.response.RefurbishProductsResponse;
import com.ey.model.Inspection;
import com.ey.model.RefurbishProducts;

public class RefurbishProductsMapper {

	
	
	public static RefurbishProducts toEntity(Inspection ip,Integer quantity,Integer margin) {
		
		RefurbishProducts p=new RefurbishProducts();
		p.setGrade(ip.getConditionGrade());
		p.setInspection(ip);
		p.setPrice(ip.getEstimatedRepairCost()+margin);
		p.setQuantity(quantity);
		
		
		return p;
	}
	
	public static RefurbishProductsResponse toResponse(RefurbishProducts req,String msg) {
		RefurbishProductsResponse res=new RefurbishProductsResponse();
		res.setGrade(req.getGrade());
		res.setId(req.getId());
		res.setBrand(req.getInspection().getBrand());
		res.setManufatureYear(req.getInspection().getManufatureYear());
		res.setMessage(msg);
		res.setModelName(req.getInspection().getModelName());
		res.setPrice(req.getPrice());
		res.setType(req.getInspection().getType());
		
		res.setQuantity(req.getQuantity());
		
		return res;
	}
	
	
	
}
