package com.ey.mapper;

import com.ey.dto.request.AddCatagory;
import com.ey.dto.response.CatagoryResponse;
import com.ey.model.Catagory;

public class CatagoryMapper {

	
	
	public static Catagory toEntity(AddCatagory req) {
		
		Catagory user=new Catagory();
		user.setName(req.getName());
		user.setLevel(req.getLevel());
		user.setRefurbishable(req.isRefurbishable());
		user.setHandlingRules(req.getHandlingRules());
		return user;
	}
	
	public static CatagoryResponse toResponse(Catagory req,String msg) {
		CatagoryResponse res=new CatagoryResponse();
		res.setHandlingRules(req.getHandlingRules());
		res.setId(req.getId());
		res.setMessage(msg);
		res.setLevel(req.getLevel());
		res.setName(req.getName());
		res.setRefurbishable(req.isRefurbishable());
		
		return res;
	}
	
}
