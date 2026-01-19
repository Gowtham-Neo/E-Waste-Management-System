package com.ey.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;

import com.ey.dto.request.CreateInspectRequest;
import com.ey.enums.ConditionGrading;
import com.ey.enums.FunctionalStatus;
import com.ey.enums.HazardLevel;
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

@SpringBootTest()
@ActiveProfiles("test")
@Transactional
class InspectServiceTest {

	@Autowired
    private InspectService inspectService;

    @Autowired
    private DisposeRepository disposeRepo;

    @Autowired
    private RecyclerRepository recycleRepo;

    @Autowired
    private CollectorRepository collRepo;
    
    @Autowired
    private CatagoryRepository cataRepo;

    @Autowired
    private JwtUtil jwtUtil;
    
	@Test
	void testInspectDispose() {
	    Catagory cata=new Catagory();
	    cata.setName("Laptop");
	    cata.setLevel(HazardLevel.LOW);
	    cata.setRefurbishable(true);
	    cataRepo.save(cata);
	    

	    Recycler recycler=new Recycler();
	    recycler.setEmail("recycler@gmail.com");
	    recycler.setPassword("pass");
	    recycler.setLicenceNumber("LIC768987");
	    recycler.setMobileNumber("9876789876");
	    recycleRepo.save(recycler);
	    
	    Collector coll=new Collector();
		coll.setEmail("dsf@gmail.com");
		coll.setMobileNumber("9879876780");
		coll.setRecycler(recycler);
		coll.setVehicleNumber("Tn-312312");
		collRepo.save(coll);
		
		Dispose dis=new Dispose();
	    dis.setCatagory(cata);
	    dis.setQuantity(5);
	    dis.setCollector(coll);
	    dis.setStatus(RequestStatus.COLLECTED);
	    disposeRepo.save(dis);
	    

	    CreateInspectRequest req=new CreateInspectRequest();
	    req.setConditionGrade(ConditionGrading.GOOD);
	    req.setCurrentMarketValue(20000.0);
	    req.setEstimatedRepairCost(3000.0);
	    req.setManufatureYear(2018);
	    req.setStatus(FunctionalStatus.WORKING);

	    String token="Bearer "+jwtUtil.generateToken(recycler.getEmail(), "RECYCLER");

	    ResponseEntity<?> res=inspectService.inspectDispose(req, dis.getId(), token);

	    assertEquals(HttpStatus.CREATED,res.getStatusCode());
	    assertNotNull(res.getBody());
	}
	@Test
	void testInspectDisposefailure() {
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
		recycleRepo.save(recycler);
		
		
		
		CreateInspectRequest req=new CreateInspectRequest();
		req.setConditionGrade(ConditionGrading.GOOD);
		req.setCurrentMarketValue(20000.0);
		req.setEstimatedRepairCost(3000.0);
		req.setManufatureYear(2018);
		req.setStatus(FunctionalStatus.WORKING);
		
		String token="Bearer "+jwtUtil.generateToken(recycler.getEmail(), "RECYCLER");
		
		ResponseEntity<?> res=inspectService.inspectDispose(req, dis.getId(), token);
		
		assertEquals(HttpStatus.BAD_REQUEST,res.getStatusCode());
	}

}
