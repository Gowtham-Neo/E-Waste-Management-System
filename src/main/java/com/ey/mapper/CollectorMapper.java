package com.ey.mapper;

import com.ey.dto.request.RegisterCollectorRequest;
import com.ey.dto.response.RegisterCollectorResponse;
import com.ey.model.Collector;

public class CollectorMapper {

	
	
	public static Collector toEntity(RegisterCollectorRequest req) {
		
		Collector user=new Collector();
		user.setEmail(req.getEmail());
		user.setMobileNumber(req.getMobileNumber());
		user.setVehicleNumber(req.getVehicleNumber());		
		return user;
	}
	
	public static RegisterCollectorResponse toResponse(Collector req,String msg) {
		RegisterCollectorResponse res=new RegisterCollectorResponse();
		res.setEmail(req.getEmail());
		res.setId(req.getId());
		res.setMessage(msg);
		res.setMobileNumber(req.getMobileNumber());
		res.setVehicleNumber(req.getVehicleNumber());
		
		return res;
	}
	
}
