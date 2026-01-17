package com.ey.dto.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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
	public ResponseEntity<?> inspectDispose(@RequestBody CreateInspectRequest req,@PathVariable Long disposeId){
		return inspectrService.inspectDispose(req,disposeId);
	}
	
	@GetMapping("/recycler/disposes/inspect")
	public ResponseEntity<?> getAllInspectedDispose(){
		return inspectrService.getAllInpectedDispose();
	}
	
	/*
	 * @PostMapping("/recycler/register/collector") public ResponseEntity<?>
	 * registerUser(@RequestBody RegisterCollectorRequest
	 * req,@RequestHeader("Authorization") String token){ return
	 * inspectrService.registerCollector(req,token); }
	 */

	
}
