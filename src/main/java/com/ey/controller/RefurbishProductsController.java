package com.ey.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ey.dto.request.UpdateRefurbishProducts;
import com.ey.service.RefurbishProductsService;

@RestController
@RequestMapping
public class RefurbishProductsController {
	
	@Autowired
	private RefurbishProductsService prodService;
	
	

	@GetMapping("/user/products")
	public ResponseEntity<?> getAllRefubishProducts(){
		return prodService.getAllRefubishProducts();
	}
	
	
	@GetMapping("/user/product/{id}")
	public ResponseEntity<?> getproductsById(@PathVariable("id") Long id){
		return prodService.getproductsById(id);
	}
	
	@PutMapping("/user/product/{id}")
	public ResponseEntity<?> updateproductsById(@RequestBody UpdateRefurbishProducts req,@PathVariable("id") Long id){
		return prodService.updateproductsById(req,id);
	}
	
	

	
}
