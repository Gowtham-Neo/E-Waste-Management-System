package com.ey.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.ey.dto.request.CreateOrderRequest;
import com.ey.dto.response.OrderResponse;
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
	
	Logger log = LoggerFactory.getLogger(OrderService.class);

	public ResponseEntity<?> orderRequest(CreateOrderRequest req,Long id,String token) {
		// TODO Auto-generated method stub
		RefurbishProducts prod=prodRepo.findById(id).orElseThrow(()-> new CatagoryNotFound("Invalid product id"));
		
		String email=jwtUtil.extractClaims(token.substring(7)).getSubject();
		
		User user=userRepo.findByEmail(email).orElseThrow(()-> new UserNotFoundException("Invalid user login"));
		if (prod.getQuantity()<req.getQuantity()) {
			log.error("insufficent Quantity");
			return new ResponseEntity<>("insufficent Quantity",HttpStatus.BAD_REQUEST);
		}
		Order ord=OrdersMapper.toEntity(prod, req.getQuantity());
		ord.setBuyer(user);

		orderRepo.save(ord);
		
		prod.setQuantity(prod.getQuantity()-ord.getQuantity());
		prodRepo.save(prod);
		
		
		log.info("Order created Successfully");
		return new ResponseEntity<>(OrdersMapper.toResponse(ord),HttpStatus.CREATED);
	}
	
	public ResponseEntity<?> updateorderRequest(CreateOrderRequest req,Long id,String token) {
		// TODO Auto-generated method stub
		Order order=orderRepo.findById(id).orElseThrow(()-> new CatagoryNotFound("Invalid Order id"));
		RefurbishProducts prod=order.getProducts();

		
		User user=userRepo.findByEmail(jwtUtil.extractSubject(token)).orElseThrow(()-> new UserNotFoundException("Invalid user login"));
		
		if (order.getBuyer().getId()!=user.getId()) {
			log.error("Trying to update other user order");
			return new ResponseEntity<>("Trying to update other user order",HttpStatus.BAD_REQUEST);
		}
		if (order.getStatus().equals(OrderStatus.DISPATCHED)||order.getStatus().equals(OrderStatus.DELIVERED)) {
			log.error("Order has already been"+ order.getStatus()+" stage and cant change to updated");
			return new ResponseEntity<>("Order has already been"+ order.getStatus()+" stage and cant change to updated",HttpStatus.BAD_REQUEST);
		}
		if (prod.getQuantity()<req.getQuantity()) {
			log.error("insufficent Quantity to update, avaiable:"+prod.getQuantity());
			return new ResponseEntity<>("insufficent Quantity to update, avaiable: "+prod.getQuantity(),HttpStatus.BAD_REQUEST);
		}
		order.setQuantity(req.getQuantity()+order.getQuantity());
		
		orderRepo.save(order);
		
		prod.setQuantity(prod.getQuantity()-order.getQuantity());
		prodRepo.save(prod);
		
		
		log.info("Order Update Successfull");
		return new ResponseEntity<>(OrdersMapper.toResponse(order),HttpStatus.ACCEPTED);
	}
	public ResponseEntity<?> getAllOrders(String token) {
		
		User user=userRepo.findByEmail(jwtUtil.extractSubject(token)).orElseThrow(()-> new UserNotFoundException("Invalid user login"));
		
		List<OrderResponse> orders=orderRepo.findByBuyerId(user.getId())
												.stream()
												.map(s-> OrdersMapper.toResponse(s))
												.toList();
		
		log.info("All Order fetch is Successfull");

		return new ResponseEntity<>(orders,HttpStatus.OK);
	}
	public ResponseEntity<?> getOrderById(Long id,String token) {
		
		User user=userRepo.findByEmail(jwtUtil.extractSubject(token)).orElseThrow(()-> new UserNotFoundException("Invalid user login"));
		
		Order order=orderRepo.findByIdAndBuyerId(id, user.getId()).orElseThrow(()-> new OrderNotFound("Invalid Order Id"));
		
		log.info("Order fetch by id Successfull");

		return new ResponseEntity<>(OrdersMapper.toResponse(order),HttpStatus.OK);
	}

	
	
	
	
	
	public ResponseEntity<?> orderApproveRequest(Long id) {

		Order ord=orderRepo.findById(id).orElseThrow(()-> new OrderNotFound("Invalid Order Id"));
		if (ord.getStatus().equals(OrderStatus.DISPATCHED)||ord.getStatus().equals(OrderStatus.DELIVERED)) {
			log.error("Order has already been"+ ord.getStatus()+" stage and cant change to approved");
			return new ResponseEntity<>("Order has already been"+ ord.getStatus()+" stage and cant change to approved",HttpStatus.BAD_REQUEST);
		}
		ord.setStatus(OrderStatus.APPROVED);
		orderRepo.save(ord);
		
		log.info("Order approved Successfull");

		return new ResponseEntity<>(OrdersMapper.toStatusResponse(ord, "Order Approved Successfully"),HttpStatus.ACCEPTED);
		
	}



	public ResponseEntity<?> orderRejectRequest(Long id) {
		// TODO Auto-generated method stub
		Order ord=orderRepo.findById(id).orElseThrow(()-> new OrderNotFound("Invalid Order Id"));
		if (ord.getStatus().equals(OrderStatus.DISPATCHED)||ord.getStatus().equals(OrderStatus.DELIVERED)) {
			log.error("Order has already been"+ ord.getStatus()+" stage and can be rejected");
			return new ResponseEntity<>("Order has already been"+ ord.getStatus()+" stage and can be rejected",HttpStatus.BAD_REQUEST);
		}
		ord.setStatus(OrderStatus.REJECTED);
		orderRepo.save(ord);
		
		log.info("Order rejection is Successfull");

		return new ResponseEntity<>(OrdersMapper.toStatusResponse(ord, "Order Rejected Successfully"),HttpStatus.ACCEPTED);
		
	}
	
	public ResponseEntity<?> orderDispatchRequest(Long id) {
		Order ord=orderRepo.findById(id).orElseThrow(()-> new OrderNotFound("Invalid Order Id"));
		
		if (ord.getStatus().equals(OrderStatus.APPROVED)) {
			ord.setStatus(OrderStatus.DISPATCHED);
			orderRepo.save(ord);
			log.info("Order dispatch Successfull");
			return new ResponseEntity<>(OrdersMapper.toStatusResponse(ord, "Order Dispatched Successfully"),HttpStatus.ACCEPTED);
		}
		
		log.error("Order is in "+ ord.getStatus()+" stage and cant be dispatched");

		return new ResponseEntity<>("Order is in "+ ord.getStatus()+" stage and cant be dispatched",HttpStatus.BAD_REQUEST);
		
	}



	public ResponseEntity<?> orderDeliveredRequest(Long id) {
		Order ord=orderRepo.findById(id).orElseThrow(()-> new OrderNotFound("Invalid Order Id"));
		if (ord.getStatus().equals(OrderStatus.DISPATCHED)) {
			ord.setStatus(OrderStatus.DELIVERED);
			orderRepo.save(ord);
			log.info("Order Delivered Successfully");
			return new ResponseEntity<>(OrdersMapper.toStatusResponse(ord, "Order Delivered Successfully"),HttpStatus.ACCEPTED);
		}
		
		log.error("Order is in "+ord.getStatus() +" stage and cant be delivered");

		return new ResponseEntity<>("Order is in "+ord.getStatus() +" stage and cant be delivered",HttpStatus.BAD_REQUEST);
		
	}
	
	
	
	
	
	
	
	
	
	
	public ResponseEntity<?> getAllOrdersByAdmin() {
		List<OrderResponse> res=orderRepo.findAll()
										.stream()
										.map(s-> OrdersMapper.toResponse(s))
										.toList();
		
		return new ResponseEntity<>(res,HttpStatus.OK);
		
	}
	public ResponseEntity<?> getOrderByIdByAdmin(Long id) {
		Order order=orderRepo.findById(id).orElseThrow(()-> new OrderNotFound("Invalid Order Id"));
		
		return new ResponseEntity<>(OrdersMapper.toResponse(order),HttpStatus.OK);
		
	}
	
	
}
 