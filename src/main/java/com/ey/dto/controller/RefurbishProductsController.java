package com.ey.dto.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ey.dto.request.RegisterCollectorRequest;
import com.ey.dto.request.recycler.RegisterRecyclerRequest;
import com.ey.service.RefurbishProductsService;

@RestController
@RequestMapping
public class RefurbishProductsController {
	
	@Autowired
	private RefurbishProductsService prodService;
	
	

	@GetMapping("/user/products")
	public ResponseEntity<?> getAllRefubishProducts(){
		return prodService.getAllRefubishProducts();
	}
	
	

	
}
