package com.ey.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ey.service.CollectorService;

@RestController
@RequestMapping
public class CollectorController {

	@Autowired
	private CollectorService collService;
	
	@PutMapping("/collector/dispose/{id}/collect")
	public ResponseEntity<?> collectDiposeRequest(@PathVariable("id") Long id,@RequestHeader("Authorization") String token){
		return collService.collectDiposeRequest(id,token);
	}
	
	@GetMapping("/collector/disposes")
	public ResponseEntity<?> getAllDiposeRequest(@RequestHeader("Authorization") String token){
		return collService.getAllDiposeRequest(token);
	}
	
	@GetMapping("/collector/dispose/{id}")
	public ResponseEntity<?> getDiposeRequestById(@PathVariable("id") Long id,@RequestHeader("Authorization") String token){
		return collService.getDiposeRequestById(id,token);
	}
	
	@PutMapping("/collector/dispose/{id}/cancel")
	public ResponseEntity<?> cancelDisposeRequest(@PathVariable("id") Long id,@RequestHeader("Authorization") String token){
		return collService.cancelDisposeRequest(id,token);
	}
}
