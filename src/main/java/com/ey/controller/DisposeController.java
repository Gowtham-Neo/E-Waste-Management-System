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

import com.ey.dto.request.AssignCollectorRequest;
import com.ey.dto.request.recycler.DisposeRequest;
import com.ey.service.DisposeService;

@RestController
@RequestMapping
public class DisposeController {
	
	@Autowired
	private DisposeService disposeService;
	
	
	@PostMapping("/user/dispose")
	public ResponseEntity<?> disposeProducts(@RequestBody DisposeRequest req,@RequestHeader("Authorization") String token){
		return disposeService.disposeProduct(req,token);
	}
	@GetMapping("/user/disposes")
	public ResponseEntity<?> getAllDispose(@RequestHeader("Authorization") String token){
		return disposeService.getAllDisposeRequest(token);
	}
	@GetMapping("/user/dispose/{id}")
	public ResponseEntity<?> getDispose(@PathVariable("id") Long id,@RequestHeader("Authorization") String token){
		return disposeService.getDisposeById(id,token);
	}
	
	@PutMapping("/user/dispose/{id}")
	public ResponseEntity<?>updatedisposeProducts(@RequestBody DisposeRequest req,@PathVariable("id") Long id,@RequestHeader("Authorization") String token)
	{ 
		return disposeService.updateDisposeProduct(req,id,token); 
	}
	  
	@DeleteMapping("/user/dispose/{id}") 
	public ResponseEntity<?>deletedisposeProducts(@PathVariable("id") Long id,@RequestHeader("Authorization") String token){ 
		 
		return disposeService.deleteDisposeProduct(id,token);
	}
	 
	
	
	
	
	@PutMapping("/recycler/dispose/{id}/assign")
	public ResponseEntity<?> assignCollector(@RequestBody AssignCollectorRequest req,@PathVariable("id") Long id){
		return disposeService.assignCollector(req,id);
	}

	
}
