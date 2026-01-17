package com.ey.mapper;

import com.ey.dto.request.recycler.RegisterRecyclerRequest;
import com.ey.dto.response.RegisterRecyclerResponse;
import com.ey.dto.response.RegisterUserResponse;
import com.ey.enums.RecyclerStatus;
import com.ey.model.Recycler;

public class RecyclerMapper {

	
	
	public static Recycler toEntity(RegisterRecyclerRequest req) {
		
		Recycler user=new Recycler();
		user.setEmail(req.getEmail());
		user.setMobileNumber(req.getMobileNumber());
		user.setLicenceNumber(req.getLicenceNumber());
		user.setOrganizationName(req.getOrganizationName());
		user.setStatus(RecyclerStatus.PENDING);
		
		return user;
	}
	
	public static RegisterRecyclerResponse toResponse(Recycler req,String msg) {
		RegisterRecyclerResponse res=new RegisterRecyclerResponse();
		res.setEmail(req.getEmail());
		res.setId(req.getId());
		res.setMessage(msg);
		res.setMobileNumber(req.getMobileNumber());
		res.setOrganizationName(req.getOrganizationName());
		res.setStatus(req.getStatus());
		res.setLicenceNumber(req.getLicenceNumber());
		
		return res;
	}
	
}
