package com.ey.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ey.dto.request.user.RegisterUserRequest;
import com.ey.dto.request.user.UpdateUserDetailsRequest;
import com.ey.dto.request.user.UserForgetPassordRequest;
import com.ey.dto.request.user.UserResetPassordRequest;
import com.ey.service.AdminService;
import com.ey.service.UserService;

import jakarta.validation.Valid;

@RestController
@RequestMapping
public class UserController {
	
	@Autowired
	private UserService userService;
	@Autowired
	private AdminService adminService;
	
	

	@PostMapping("/auth/user/register")
	public ResponseEntity<?> registerUser(@Valid @RequestBody RegisterUserRequest req){
		System.out.println("Here....1");
		return userService.registerUser(req);
	}
	
	@PutMapping("/user")
	public ResponseEntity<?> updateUser(@Valid @RequestBody UpdateUserDetailsRequest req,@RequestHeader("Authorization") String token){
		
		return userService.updateUser(req,token);
	}
	
	
	@PutMapping("/user/reset-password")
	public ResponseEntity<?> resetPassword(@Valid @RequestBody UserResetPassordRequest req,@RequestHeader("Authorization") String token){
		
		return userService.resetPassword(req,token);
	}
	
	
	@PutMapping("/auth/user/forget-password")
	public ResponseEntity<?> changePassword(@Valid @RequestBody UserForgetPassordRequest req){
		
		return userService.forgetPassword(req);
	}
	
	
	@GetMapping("/user/me")
	public ResponseEntity<?> getMyDetails(@Valid @RequestHeader("Authorization") String token){
		
		return userService.getMyDetails(token);
	}
	
	

	
	
	
	
	

	
	
	@GetMapping("/user/catagory/{id}")
	public ResponseEntity<?> getCatagoryById(@PathVariable("id") Long id){
		
		return adminService.getCatagoryById(id);
	}
	@GetMapping("/user/catagory")
	public ResponseEntity<?> getAllCatagory(){
		
		return adminService.getAllCatagory();
	}

	
}
