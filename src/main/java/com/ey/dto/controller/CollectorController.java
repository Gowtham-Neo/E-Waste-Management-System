package com.ey.dto.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ey.service.CollectorService;

@RestController
@RequestMapping
public class CollectorController {

	@Autowired
	private CollectorService collService;
	
	@PostMapping("/collector/dispose/{disposeId}/collect")
	public ResponseEntity<?> collectDiposeRequest(@PathVariable("disposeId") Long id,@RequestHeader("Authorization") String token){
		return collService.collectDiposeRequest(id,token);
	}
	
	@PostMapping("/collector/dispose/{disposeId}/cancel")
	public ResponseEntity<?> cancelDisposeRequest(@PathVariable("disposeId") Long id,@RequestHeader("Authorization") String token){
		return collService.cancelDisposeRequest(id,token);
	}
}
