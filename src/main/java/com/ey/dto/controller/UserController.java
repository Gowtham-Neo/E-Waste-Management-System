package com.ey.dto.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ey.dto.request.user.RegisterUserRequest;
import com.ey.dto.request.user.UpdateUserDetailsRequest;
import com.ey.dto.request.user.UserForgetPassordRequest;
import com.ey.dto.request.user.UserResetPassordRequest;
import com.ey.service.UserService;

@RestController
@RequestMapping
public class UserController {
	
	@Autowired
	private UserService userService;
	
	

	@PostMapping("/auth/user/register")
	public ResponseEntity<?> registerUser(@RequestBody RegisterUserRequest req){
		System.out.println("Here....1");
		return userService.registerUser(req);
	}
	
	@PostMapping("/user/update")
	public ResponseEntity<?> updateUser(@RequestBody UpdateUserDetailsRequest req,@RequestHeader("Authorization") String token){
		
		return userService.updateUser(req,token);
	}
	
	@PostMapping("/user/reset-password")
	public ResponseEntity<?> resetPassword(@RequestBody UserResetPassordRequest req,@RequestHeader("Authorization") String token){
		
		return userService.resetPassword(req,token);
	}
	
	@PostMapping("/auth/user/forget-password")
	public ResponseEntity<?> changePassword(@RequestBody UserForgetPassordRequest req){
		
		return userService.forgetPassword(req);
	}
	


	
}
