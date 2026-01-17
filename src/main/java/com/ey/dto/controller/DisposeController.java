package com.ey.dto.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
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
	
	
	@PostMapping("/dispose")
	public ResponseEntity<?> disposeProducts(@RequestBody DisposeRequest req,@RequestHeader("Authorization") String token){
		return disposeService.disposeProduct(req,token);
	}
	
	@PostMapping("/recycler/dispose/{id}/assign")
	public ResponseEntity<?> assignCollector(@RequestBody AssignCollectorRequest req,@PathVariable("id") Long id){
		return disposeService.assignCollector(req,id);
	}

	
}
