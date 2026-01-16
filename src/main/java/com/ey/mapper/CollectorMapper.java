package com.ey.mapper;

import com.ey.dto.request.RegisterCollectorRequest;
import com.ey.dto.response.CollectorResponse;
import com.ey.model.Collector;

public class CollectorMapper {

	
	
	public static Collector toEntity(RegisterCollectorRequest req) {
		
		Collector user=new Collector();
		user.setEmail(req.getEmail());
		user.setMobileNumber(req.getMobileNumber());
		user.setVehicleNumber(req.getVehicleNumber());		
		return user;
	}
	
	public static CollectorResponse toResponse(Collector req,String msg) {
		CollectorResponse res=new CollectorResponse();
		res.setEmail(req.getEmail());
		res.setId(req.getId());
		res.setMessage(msg);
		res.setMobileNumber(req.getMobileNumber());
		res.setVehicleNumber(req.getVehicleNumber());
		
		return res;
	}
	
}
