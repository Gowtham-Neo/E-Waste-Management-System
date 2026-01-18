package com.ey.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ey.dto.request.CreateOrderRequest;
import com.ey.service.OrderService;

@RestController
@RequestMapping
public class OrderController {

	@Autowired
	private OrderService orderService;
	
	@PostMapping("/user/order/product/{id}")
	public ResponseEntity<?> orderRequest(@RequestBody CreateOrderRequest req,@PathVariable("id") Long id,
			@RequestHeader("Authorization") String token){
		return orderService.orderRequest(req,id,token);
	}
	@PutMapping("/user/order/{id}")
	public ResponseEntity<?> updateorderRequest(@RequestBody CreateOrderRequest req,@PathVariable("id") Long id,
			@RequestHeader("Authorization") String token){
		return orderService.updateorderRequest(req,id,token);
	}
	@GetMapping("/user/orders")
	public ResponseEntity<?> getAllOrders(@RequestHeader("Authorization") String token){
		return orderService.getAllOrders(token);
	}
	@GetMapping("/user/order/{id}")
	public ResponseEntity<?> getOrderById(@PathVariable("id") Long id,@RequestHeader("Authorization") String token){
		return orderService.getOrderById(id,token);
	}
	
	
	@PutMapping("/recycler/order/{id}/approve")
	public ResponseEntity<?> orderApproveRequest(@PathVariable("id") Long id){
		return orderService.orderApproveRequest(id);
	}
	
	@PutMapping("/recycler/order/{id}/reject")
	public ResponseEntity<?> orderRejectRequest(@PathVariable("id") Long id){
		return orderService.orderRejectRequest(id);
	}
	@PutMapping("/recycler/order/{id}/dispatch")
	public ResponseEntity<?> orderDispathRequest(@PathVariable("id") Long id){
		return orderService.orderDispatchRequest(id);
	}
	
	@PutMapping("/collector/order/{id}/deliver")
	public ResponseEntity<?> orderDelivedRequest(@PathVariable("id") Long id){
		return orderService.orderDeliveredRequest(id);
	}
	
	
}
