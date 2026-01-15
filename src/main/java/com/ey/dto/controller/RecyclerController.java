package com.ey.dto.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ey.dto.request.RegisterCollectorRequest;
import com.ey.dto.request.RegisterRecyclerRequest;
import com.ey.service.RecyclerService;

@RestController
@RequestMapping
public class RecyclerController {
	
	@Autowired
	private RecyclerService recyclerService;
	
	

	@PostMapping("/auth/recycler/register")
	public ResponseEntity<?> registerUser(@RequestBody RegisterRecyclerRequest req){
		return recyclerService.registerRecycler(req);
	}
	
	@PostMapping("/recycler/register/collector")
	public ResponseEntity<?> registerUser(@RequestBody RegisterCollectorRequest req,@RequestHeader("Authorization") String token){
		return recyclerService.registerCollector(req,token);
	}

	
}
