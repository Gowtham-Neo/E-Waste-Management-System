package com.ey.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ey.dto.request.AddMaterialsRequest;
import com.ey.dto.request.UpdateMaterialsRequest;
import com.ey.service.RecycledMaterialsService;

import jakarta.validation.Valid;

@RestController
@RequestMapping
public class RecyclerMaterialController {
	
	@Autowired
	private RecycledMaterialsService materialsService;
	
	

	@PostMapping("/recycler/inspect/{id}/material")
	public ResponseEntity<?> addMaterials(@Valid @RequestBody AddMaterialsRequest req,@PathVariable("id") Long id){
		return materialsService.addMaterials(req,id);
	}
	@PutMapping("/recycler/material/{id}")
	public ResponseEntity<?> updateMaterials(@Valid @RequestBody UpdateMaterialsRequest req,@PathVariable("id") Long id){
		return materialsService.updateMaterial(req,id);
	}
	
	@GetMapping("/recycler/materials")
	public ResponseEntity<?> getAllMaterials(){
		return materialsService.getAllMaterials();
	}
	@GetMapping("/recycler/material/{id}")
	public ResponseEntity<?> getMaterialById(@PathVariable("id") Long id){
		return materialsService.getMaterialById(id);
	}
	
	
}
