package com.ey.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;

import com.ey.dto.request.CreateOrderRequest;
import com.ey.enums.OrderStatus;
import com.ey.enums.Role;
import com.ey.model.Inspection;
import com.ey.model.Order;
import com.ey.model.RefurbishProducts;
import com.ey.model.User;
import com.ey.repository.InspectionRepository;
import com.ey.repository.OrderRepository;
import com.ey.repository.RefurbishProductsRepository;
import com.ey.repository.UserRepository;
import com.ey.security.JwtUtil;

import jakarta.transaction.Transactional;
@SpringBootTest()
@ActiveProfiles("test")
@Transactional
class OrderServiceTest {

	@Autowired
    private UserRepository userRepo;
	@Autowired
	private OrderService orderService;
	@Autowired
	private RefurbishProductsRepository prodRepo;
	@Autowired
	private InspectionRepository inspectRepo;
	@Autowired
	private OrderRepository orderRepo;
	@Autowired
	private JwtUtil jwtUtil;
	
	@Test
	void testUpdateorderRequest() {
		User user=new User();
	    user.setEmail("ram@gmail.com");
	    user.setPassword("pass");
	    user.setRole(Role.SELLER);
	    user.setMobileNumber("9763889767");
	    userRepo.save(user);
	    
	    Inspection ip=new Inspection();
	    ip.setBrand("Laptop");
	    ip.setEstimatedRepairCost(123.2);
	    inspectRepo.save(ip);
	    
	    
	    RefurbishProducts prod=new RefurbishProducts();
	    prod.setPrice(100.0);
	    prod.setQuantity(12);
	    prod.setInspection(ip);
	    prodRepo.save(prod);
	    
	    
	    Order o=new Order();
	    o.setBuyer(user);
	    o.setProducts(prod);
	    o.setQuantity(10);
	    o.setStatus(OrderStatus.PENDING);
	    o.setTotalAmount(1000.0);
	    orderRepo.save(o);
	    
	    
	    String token=jwtUtil.generateToken(user.getEmail(), user.getRole().name());
	    
	    CreateOrderRequest req=new CreateOrderRequest();
	    req.setQuantity(1);
	    
	    ResponseEntity<?> res=orderService.updateorderRequest(req, o.getId(), "Bearer "+token);
	    
	    assertEquals(HttpStatus.ACCEPTED,res.getStatusCode());
	    assertNotNull(res.getBody());
	     
	}
	
	@Test
	void testUpdateorderRequestfailure() {
		User user=new User();
		user.setEmail("ragul23@gmail.com");
		user.setPassword("pass");
		user.setRole(Role.SELLER);
		user.setMobileNumber("9767835689767");
		userRepo.save(user);
		
		Inspection ip=new Inspection();
		ip.setBrand("Laptop");
		ip.setEstimatedRepairCost(123.2);
		inspectRepo.save(ip);
		
		
		RefurbishProducts prod=new RefurbishProducts();
		prod.setPrice(100.0);
		prod.setQuantity(12);
		prod.setInspection(ip);
		prodRepo.save(prod);
		
		
		Order o=new Order();
		o.setBuyer(user);
		o.setProducts(prod);
		o.setQuantity(10);
		o.setStatus(OrderStatus.DISPATCHED);
		o.setTotalAmount(1000.0);
		orderRepo.save(o);
		
		
		String token=jwtUtil.generateToken(user.getEmail(), user.getRole().name());
		
		CreateOrderRequest req=new CreateOrderRequest();
		req.setQuantity(1);
		
		ResponseEntity<?> res=orderService.updateorderRequest(req, o.getId(), "Bearer "+token);
		
		assertEquals(HttpStatus.BAD_REQUEST,res.getStatusCode());
		
	}

}
