package com.ey.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;

import com.ey.dto.request.recycler.DisposeRequest;
import com.ey.dto.response.DisposeMsgResponse;
import com.ey.enums.HazardLevel;
import com.ey.enums.Role;
import com.ey.exception.UserNotFoundException;
import com.ey.model.Catagory;
import com.ey.model.User;
import com.ey.repository.CatagoryRepository;
import com.ey.repository.UserRepository;
import com.ey.security.JwtUtil;


@SpringBootTest()
@ActiveProfiles("test")
class DisposeServiceTest {
	
    @Autowired
    private UserRepository userRepo;

    @Autowired
    private CatagoryRepository cataRepo;

    @Autowired
    private JwtUtil jwtUtil;
    
    @Autowired
    private DisposeService disposeService;

    
    
	@Test
	void testDisposeProductSuccuss() {
		Catagory cata=new Catagory();
		cata.setName("Laptop");
		cata.setLevel(HazardLevel.LOW);
		cata.setRefurbishable(true);
		cataRepo.save(cata);
		
		User user=new User();
	    user.setEmail("gowtham@gmail.com");
	    user.setPassword("pass");
	    user.setRole(Role.SELLER);
	    user.setMobileNumber("9767889767");
	    userRepo.save(user);
	    
	    DisposeRequest req=new DisposeRequest();
	    req.setCatagoryId(cata.getId());
	    req.setQuantity(10);
	    req.setLocation("KCT Tech park");
	    
	    String token=jwtUtil.generateToken(user.getEmail(), user.getRole().name());
	    
	    ResponseEntity<DisposeMsgResponse> res=disposeService.disposeProduct(req, "Bearer " + token);
	    
	    assertEquals(HttpStatus.CREATED, res.getStatusCode());
	    assertNotNull(res.getBody().getId());
	}
	
	@Test
	void testDisposeProductFailure() {
		Catagory cata=new Catagory();
		cata.setName("Laptop");
		cata.setLevel(HazardLevel.LOW);
		cata.setRefurbishable(true);
		cataRepo.save(cata);
		
		
		DisposeRequest req=new DisposeRequest();
		req.setCatagoryId(cata.getId());
		req.setQuantity(10);
		req.setLocation("KCT Tech park");
		
		String token=jwtUtil.generateToken("gowtham@gmail.com", "SELLER");
		
		assertThrows(UserNotFoundException.class, ()-> disposeService.disposeProduct(req, "Bearer " + token));
	}

}
