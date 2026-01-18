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

import com.ey.dto.request.CreateInspectRequest;
import com.ey.service.InspectService;

@RestController
@RequestMapping
public class InspectController {
	
	@Autowired
	private InspectService inspectrService;
	
	

	@PostMapping("/recycler/dispose/{disposeId}/inspect")
	public ResponseEntity<?> inspectDispose(@RequestBody CreateInspectRequest req,@PathVariable Long disposeId,@RequestHeader("Authorization") String token){
		return inspectrService.inspectDispose(req,disposeId,token);
	}
	
	@PutMapping("/recycler/inspect/{id}")
	public ResponseEntity<?> updateinspectDispose(@RequestBody CreateInspectRequest req,@PathVariable Long id,@RequestHeader("Authorization") String token){
		return inspectrService.updateinspectDispose(req,id,token);
	}
	@DeleteMapping("/recycler/inspect/{id}")
	public ResponseEntity<?> deleteinspectDispose(@PathVariable Long id,@RequestHeader("Authorization") String token){
		return inspectrService.deleteinspectDispose(id,token);
	}
	
	@GetMapping("/recycler/inspects")
	public ResponseEntity<?> getAllInspectedDispose(@RequestHeader("Authorization") String token){
		return inspectrService.getAllInpectedDispose(token);
	}
	
	@GetMapping("/recycler/inspect/{id}")
	public ResponseEntity<?> getInspectById(@PathVariable Long id,@RequestHeader("Authorization") String token){
		return inspectrService.getInspectById(id,token);
	}
	

	
}
