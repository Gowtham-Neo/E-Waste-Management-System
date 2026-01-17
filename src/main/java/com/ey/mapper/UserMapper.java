package com.ey.mapper;

import com.ey.dto.request.user.RegisterUserRequest;
import com.ey.dto.response.RegisterUserResponse;
import com.ey.dto.response.UserResponse;
import com.ey.model.User;

public class UserMapper {

	
	
	public static User toEntity(RegisterUserRequest req) {
		final String organizationname="Individual";
		User user=new User();
		user.setEmail(req.getEmail());
		user.setMobileNumber(req.getMobileNumber());
		user.setName(req.getName());
		user.setOrganizationName(req.getOrganizationName()!=null?req.getOrganizationName():organizationname);
		user.setRole(req.getRole());
		
		return user;
	}

	
	public static RegisterUserResponse toResponse(User req,String msg) {
		RegisterUserResponse res=new RegisterUserResponse();
		res.setEmail(req.getEmail());
		res.setId(req.getId());
		res.setMessage(msg);
		res.setMobileNumber(req.getMobileNumber());
		res.setName(req.getName());
		res.setOrganizationName(req.getOrganizationName());
		res.setRole(req.getRole());
		
		return res;
	}
	
	
	public static UserResponse toResponse(User req) {
		UserResponse res=new UserResponse();
		res.setEmail(req.getEmail());
		res.setId(req.getId());
		res.setMobileNumber(req.getMobileNumber());
		res.setName(req.getName());
		res.setOrganizationName(req.getOrganizationName());
		res.setRole(req.getRole());
		
		return res;
	}
	
}
