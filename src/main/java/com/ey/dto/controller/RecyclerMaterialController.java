package com.ey.dto.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ey.dto.request.AddMaterialsRequest;
import com.ey.dto.request.RegisterCollectorRequest;
import com.ey.service.RecycledMaterialsService;

@RestController
@RequestMapping
public class RecyclerMaterialController {
	
	@Autowired
	private RecycledMaterialsService materialsService;
	
	

	@PostMapping("/recycler/inspect/{id}/material")
	public ResponseEntity<?> addMaterials(@RequestBody AddMaterialsRequest req,@PathVariable("id") Long id){
		return materialsService.addMaterials(req,id);
	}
	
	
}
