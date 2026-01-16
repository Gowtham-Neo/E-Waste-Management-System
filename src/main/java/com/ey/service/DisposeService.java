package com.ey.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.ey.dto.request.AssignCollectorRequest;
import com.ey.dto.request.recycler.DisposeRequest;
import com.ey.enums.RequestStatus;
import com.ey.exception.CatagoryNotFound;
import com.ey.exception.DisposeNotFound;
import com.ey.exception.UserNotFoundException;
import com.ey.mapper.DisposeMapper;
import com.ey.model.Catagory;
import com.ey.model.Collector;
import com.ey.model.Dispose;
import com.ey.model.User;
import com.ey.repository.CatagoryRepository;
import com.ey.repository.CollectorRepository;
import com.ey.repository.DisposeRepository;
import com.ey.repository.UserRepository;
import com.ey.security.JwtUtil;

@Service
public class DisposeService {

	@Autowired
	private DisposeRepository disposeRepo;
	@Autowired
	private CollectorRepository collectorRepo;
	@Autowired
	private UserRepository userRepo;
	@Autowired
	private JwtUtil jwtUtil;
	@Autowired
	private CatagoryRepository cataRepo;

	
	public ResponseEntity<?> disposeProduct(DisposeRequest req,String token){
		
		Catagory cata=cataRepo.findById(req.getCatagoryId())
									.orElseThrow(()-> new CatagoryNotFound("Invalid Catagory Id"));
		
		String  email= jwtUtil.extractClaims(token.substring(7)).getSubject();
		User user=userRepo.findByEmail(email)
									.orElseThrow(()->new UserNotFoundException("Invalid user"));
		
		Dispose dis=DisposeMapper.toEntity(req,cata);
		dis.setUser(user);
		
		disposeRepo.save(dis);
		
		
		return new ResponseEntity<>(DisposeMapper.toResponse(dis, "Dispose Request created Successfully"),HttpStatus.CREATED);
	}
	
	public ResponseEntity<?> assignCollector(AssignCollectorRequest req,Long id){
		
		Collector coll=collectorRepo.findById(req.getCollectorId())
										.orElseThrow(()-> new UserNotFoundException("Invlaid Collector Id"));
		
		Dispose dis=disposeRepo.findById(id)
										.orElseThrow(()-> new DisposeNotFound("Invlaid dispose Id"));
		dis.setCollector(coll);
		dis.setStatus(RequestStatus.ASSIGNED);
	
		disposeRepo.save(dis);
		
		
		return new ResponseEntity<>(DisposeMapper.toResponseWithCollector(dis, "Collector Assigned Successfully"),HttpStatus.CREATED);
	}
}
