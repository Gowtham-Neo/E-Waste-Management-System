package com.ey.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ey.dto.request.RegisterCollectorRequest;
import com.ey.dto.request.recycler.RegisterRecyclerRequest;
import com.ey.dto.request.recycler.UpdateRecyclerDetailsRequest;
import com.ey.dto.request.user.UserForgetPassordRequest;
import com.ey.dto.request.user.UserResetPassordRequest;
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
	
	@PutMapping("/recycler")
	public ResponseEntity<?> updateUser(@RequestBody UpdateRecyclerDetailsRequest req,@RequestHeader("Authorization") String token){
		
		return recyclerService.updateRecycler(req,token);
	}
	
	
	@PutMapping("/recycler/reset-password")
	public ResponseEntity<?> resetPassword(@RequestBody UserResetPassordRequest req,@RequestHeader("Authorization") String token){
		
		return recyclerService.resetPassword(req,token);
	}
	
	
	@PutMapping("/auth/recycler/forget-password")
	public ResponseEntity<?> changePassword(@RequestBody UserForgetPassordRequest req){
		
		return recyclerService.forgetPassword(req);
	}
	

	@GetMapping("/recycler/me")
	public ResponseEntity<?> getMyDetails(@RequestHeader("Authorization") String token){
		
		return recyclerService.getMyDetails(token);
	}
	
	
	
	
	@PostMapping("/recycler/collector")
	public ResponseEntity<?> registerCollecotr(@RequestBody RegisterCollectorRequest req,@RequestHeader("Authorization") String token){
		return recyclerService.registerCollector(req,token);
	}
	
	@PutMapping("/recycler/collector/{id}")
	public ResponseEntity<?> updateCollector(@RequestBody RegisterCollectorRequest req,@PathVariable("id") Long id,@RequestHeader("Authorization") String token){
		return recyclerService.updateCollector(req,id,token);
	}
	
	@DeleteMapping("/recycler/collector/{id}")
	public ResponseEntity<?> deleteCollector(@PathVariable("id") Long id,@RequestHeader("Authorization") String token){
		return recyclerService.deleteCollector(id,token);
	}
	
	@GetMapping("/recycler/collectors")
	public ResponseEntity<?> getMyCollectors(@RequestHeader("Authorization") String token){
		
		return recyclerService.getMyCollectors(token);
	}
	
	@GetMapping("/recycler/collector/{id}")
	public ResponseEntity<?> getMyCollectorById(@PathVariable("id") Long id,@RequestHeader("Authorization") String token){
		
		return recyclerService.getMyCollectorById(id,token);
	}
	

	
}
