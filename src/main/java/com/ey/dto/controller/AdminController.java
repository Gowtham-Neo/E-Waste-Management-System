package com.ey.dto.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ey.dto.request.AddCatagory;
import com.ey.service.AdminService;

@RestController
@RequestMapping
public class AdminController {
	
	@Autowired
	private AdminService adminService;
	
	

	@PostMapping("/admin/catagory")
	public ResponseEntity<?> addCatagory(@RequestBody AddCatagory req){
		
		return adminService.addCatagory(req);
	}
	
	

	
}
