package com.ey.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.ey.dto.request.CreateOrderRequest;
import com.ey.enums.OrderStatus;
import com.ey.exception.CatagoryNotFound;
import com.ey.exception.OrderNotFound;
import com.ey.exception.UserNotFoundException;
import com.ey.mapper.OrdersMapper;
import com.ey.model.Order;
import com.ey.model.RefurbishProducts;
import com.ey.model.User;
import com.ey.repository.OrderRepository;
import com.ey.repository.RefurbishProductsRepository;
import com.ey.repository.UserRepository;
import com.ey.security.JwtUtil;

@Service
public class OrderService {

	@Autowired
	private OrderRepository orderRepo;
	
	@Autowired
	private RefurbishProductsRepository prodRepo;
	@Autowired
	private UserRepository userRepo;
	@Autowired
	private JwtUtil jwtUtil;
	
	

	public ResponseEntity<?> orderRequest(CreateOrderRequest req,Long id,String token) {
		// TODO Auto-generated method stub
		RefurbishProducts prod=prodRepo.findById(id).orElseThrow(()-> new CatagoryNotFound("Invalid product id"));
		
		String email=jwtUtil.extractClaims(token.substring(7)).getSubject();
		
		User user=userRepo.findByEmail(email).orElseThrow(()-> new UserNotFoundException("Invalid user login"));
		Order ord=OrdersMapper.toEntity(prod, req.getQuantity());
		ord.setBuyer(user);

		orderRepo.save(ord);
		
		prod.setQuantity(prod.getQuantity()-ord.getQuantity());
		prodRepo.save(prod);
		
		
		
		return new ResponseEntity<>(OrdersMapper.toResponse(ord),HttpStatus.CREATED);
	}



	public ResponseEntity<?> orderApproveRequest(Long id) {
		// TODO Auto-generated method stub
		Order ord=orderRepo.findById(id).orElseThrow(()-> new OrderNotFound("Invalid Order Id"));
		if (ord.getStatus().equals(OrderStatus.DISPATCHED)||ord.getStatus().equals(OrderStatus.DELIVERED)) {
			return new ResponseEntity<>("Order has already been"+ ord.getStatus()+" stage and cant change to approved",HttpStatus.BAD_REQUEST);
		}
		ord.setStatus(OrderStatus.APPROVED);
		orderRepo.save(ord);
		
		return new ResponseEntity<>(OrdersMapper.toStatusResponse(ord, "Order Approved Successfully"),HttpStatus.ACCEPTED);
		
	}
	public ResponseEntity<?> orderDispatchRequest(Long id) {
		Order ord=orderRepo.findById(id).orElseThrow(()-> new OrderNotFound("Invalid Order Id"));
		
		if (ord.getStatus().equals(OrderStatus.APPROVED)) {
			ord.setStatus(OrderStatus.DISPATCHED);
			orderRepo.save(ord);
			return new ResponseEntity<>(OrdersMapper.toStatusResponse(ord, "Order Dispatched Successfully"),HttpStatus.ACCEPTED);
		}
		
		
		return new ResponseEntity<>("Order is in "+ ord.getStatus()+" stage and cant be dispatched",HttpStatus.BAD_REQUEST);
		
	}



	public ResponseEntity<?> orderRejectRequest(Long id) {
		// TODO Auto-generated method stub
		Order ord=orderRepo.findById(id).orElseThrow(()-> new OrderNotFound("Invalid Order Id"));
		if (ord.getStatus().equals(OrderStatus.DISPATCHED)||ord.getStatus().equals(OrderStatus.DELIVERED)) {
			return new ResponseEntity<>("Order has already been"+ ord.getStatus()+" stage and can be rejected",HttpStatus.BAD_REQUEST);
		}
		ord.setStatus(OrderStatus.REJECTED);
		orderRepo.save(ord);
		
		return new ResponseEntity<>(OrdersMapper.toStatusResponse(ord, "Order Rejected Successfully"),HttpStatus.ACCEPTED);
		
	}



	public ResponseEntity<?> orderDeliveredRequest(Long id) {
		Order ord=orderRepo.findById(id).orElseThrow(()-> new OrderNotFound("Invalid Order Id"));
		if (ord.getStatus().equals(OrderStatus.DISPATCHED)) {
			ord.setStatus(OrderStatus.DELIVERED);
			orderRepo.save(ord);
			return new ResponseEntity<>(OrdersMapper.toStatusResponse(ord, "Order Delivered Successfully"),HttpStatus.ACCEPTED);
		}
		
		return new ResponseEntity<>("Order is in "+ord.getStatus() +" stage and cant be delivered",HttpStatus.BAD_REQUEST);
		
	}
	
	
}
