package com.ey.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ey.dto.request.AddCatagory;
import com.ey.service.AdminService;
import com.ey.service.OrderService;

@RestController
@RequestMapping
public class AdminController {
	
	@Autowired
	private AdminService adminService;
	
	@Autowired
	private OrderService orderService;

	@GetMapping("/admin/users")
	public ResponseEntity<?> getAllUsers(){
		
		return adminService.getAllUsers();
	}
	@GetMapping("/admin/user/{id}")
	public ResponseEntity<?> getUserById(@PathVariable("id") Long id){
		
		return adminService.getUserById(id);
	}
	@GetMapping("/admin/user")
	public ResponseEntity<?> getUserByEmail(@RequestParam("email") String email){
		
		return adminService.getUserByEmail(email);
	}
	
	
	
	@GetMapping("/admin/recyclers")
	public ResponseEntity<?> getAllRecyclers(){
		
		return adminService.getAllRecyclers();
	}
	@GetMapping("/admin/recycler/{id}")
	public ResponseEntity<?> getRecyclerById(@PathVariable("id") Long id){
		
		return adminService.getRecyclerById(id);
	}
	@GetMapping("/admin/recycler")
	public ResponseEntity<?> getRecyclerByEmail(@RequestParam("email") String email){
		
		return adminService.getRecyclerByEmail(email);
	}
	
	
	
	@GetMapping("/admin/collectors")
	public ResponseEntity<?> getAllCollectors(){
		
		return adminService.getAllCollectors();
	}
	@GetMapping("/admin/collector/{id}")
	public ResponseEntity<?> getCollectorsById(@PathVariable("id") Long id){
		
		return adminService.getCollectorById(id);
	}
	@GetMapping("/admin/collector")
	public ResponseEntity<?> getCollectorsByEmail(@RequestParam("email") String email){
		
		return adminService.getCollectorByEmail(email);
	}
	
	
	
	
	@PostMapping("/admin/catagory")
	public ResponseEntity<?> addCatagory(@RequestBody AddCatagory req){
		
		return adminService.addCatagory(req);
	}
	@PutMapping("/admin/catagory/{id}")
	public ResponseEntity<?> updateCatagory(@RequestBody AddCatagory req,@PathVariable("id") Long id){
		
		return adminService.updateCatagory(req,id);
	}
	@DeleteMapping("/admin/catagory/{id}")
	public ResponseEntity<?> deleteCatagory(@PathVariable("id") Long id){
		
		return adminService.deleteCatagory(id);
	}
	@GetMapping("/admin/catagory/{id}")
	public ResponseEntity<?> getCatagoryById(@PathVariable("id") Long id){
		
		return adminService.getCatagoryById(id);
	}
	@GetMapping("/admin/catagory")
	public ResponseEntity<?> getAllCatagory(){
		
		return adminService.getAllCatagory();
	}
	
	
	
	
	@PutMapping("/admin/order/{id}/approve")
	public ResponseEntity<?> orderApproveRequest(@PathVariable("id") Long id){
		return orderService.orderApproveRequest(id);
	}
	
	@PutMapping("/admin/order/{id}/reject")
	public ResponseEntity<?> orderRejectRequest(@PathVariable("id") Long id){
		return orderService.orderRejectRequest(id);
	}
	

	
}
