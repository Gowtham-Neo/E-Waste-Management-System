package com.ey.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;

import com.ey.dto.request.AssignCollectorRequest;
import com.ey.enums.HazardLevel;
import com.ey.enums.RecyclerStatus;
import com.ey.enums.RequestStatus;
import com.ey.model.Catagory;
import com.ey.model.Collector;
import com.ey.model.Dispose;
import com.ey.model.Recycler;
import com.ey.repository.CatagoryRepository;
import com.ey.repository.CollectorRepository;
import com.ey.repository.DisposeRepository;
import com.ey.repository.RecyclerRepository;
import com.ey.security.JwtUtil;

import jakarta.transaction.Transactional;
@SpringBootTest
@ActiveProfiles("test")
@Transactional
class DisposeServiceTest2 {


    @Autowired
    private DisposeService disposeService;

    @Autowired
    private DisposeRepository disposeRepo;

    @Autowired
    private CollectorRepository collectorRepo;

    @Autowired
    private RecyclerRepository recyclerRepo;

    @Autowired
    private CatagoryRepository cataRepo;
    
    @Autowired
    private JwtUtil jwtUtil;
	@Test
	void testAssignCollector() {
		Recycler recycler=new Recycler();
		recycler.setEmail("recycler1@gmail.com");
		recycler.setPassword("pass1");
		recycler.setLicenceNumber("LIC7681987");
		recycler.setMobileNumber("98767819876");
		recycler.setStatus(RecyclerStatus.APPROVED);
		recyclerRepo.save(recycler);
		
		
		Collector coll=new Collector();
		coll.setEmail("dsf@gmail.com");
		coll.setMobileNumber("9879876780");
		coll.setRecycler(recycler);
		coll.setVehicleNumber("Tn-312312");
		collectorRepo.save(coll);
		
		Catagory cata=new Catagory();
		cata.setName("Laptop");
		cata.setLevel(HazardLevel.LOW);
		cata.setRefurbishable(true);
		cataRepo.save(cata);
		
		Dispose dis=new Dispose();
		dis.setCatagory(cata);
		dis.setQuantity(5);
		dis.setStatus(RequestStatus.CREATED);
		disposeRepo.save(dis);
		
		
		AssignCollectorRequest req=new AssignCollectorRequest();
	    req.setCollectorId(coll.getId());
	    
	    String token=jwtUtil.generateToken(recycler.getEmail(), "RECYCLER");
	    
	    ResponseEntity<?> res=disposeService.assignCollector(req, dis.getId(),"Bearer "+token);
	    
	    assertEquals(HttpStatus.CREATED,res.getStatusCode());
	    assertNotNull(res.getBody());
		
	}
	@Test
	void testAssignCollectorFailure() {
		Catagory cata=new Catagory();
		cata.setName("Laptop");
		cata.setLevel(HazardLevel.LOW);
		cata.setRefurbishable(true);
		cataRepo.save(cata);
		
		Dispose dis=new Dispose();
		dis.setCatagory(cata);
		dis.setQuantity(5);
		dis.setStatus(RequestStatus.CREATED);
		disposeRepo.save(dis);
		
		Recycler recycler=new Recycler();
		recycler.setEmail("recycler1@gmail.com");
		recycler.setPassword("pass1");
		recycler.setLicenceNumber("LIC7681987");
		recycler.setMobileNumber("98767819876");
		recycler.setStatus(RecyclerStatus.PENDING);
		recyclerRepo.save(recycler);
		
		Collector coll=new Collector();
		coll.setEmail("dsf2@gmail.com");
		coll.setMobileNumber("9879276780");
		coll.setRecycler(recycler);
		coll.setVehicleNumber("TN-2827928");
		collectorRepo.save(coll);
		
		AssignCollectorRequest req=new AssignCollectorRequest();
		req.setCollectorId(coll.getId());
		
		String token=jwtUtil.generateToken(recycler.getEmail(), "RECYCLER");
	    
	    ResponseEntity<?> res=disposeService.assignCollector(req, dis.getId(),"Bearer "+token);
		
		assertEquals(HttpStatus.BAD_REQUEST,res.getStatusCode());
		
	}

}
