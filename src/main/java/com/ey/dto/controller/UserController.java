package com.ey.dto.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ey.dto.request.RegisterUserRequest;
import com.ey.service.UserService;

@RestController
@RequestMapping
public class UserController {
	
	@Autowired
	private UserService userService;
	
	

	@PostMapping("/auth/register")
	public ResponseEntity<?> registerUser(@RequestBody RegisterUserRequest req){
		System.out.println("Here....1");
		return userService.registerUser(req);
	}

	
}
