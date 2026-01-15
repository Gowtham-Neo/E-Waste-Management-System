package com.ey.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.ey.dto.request.RegisterCollectorRequest;
import com.ey.dto.request.RegisterRecyclerRequest;
import com.ey.exception.UserNotFoundException;
import com.ey.mapper.CollectorMapper;
import com.ey.mapper.RecyclerMapper;
import com.ey.model.Collector;
import com.ey.model.Recycler;
import com.ey.repository.CollectorRepository;
import com.ey.repository.RecyclerRepository;
import com.ey.security.JwtUtil;

@Service
public class RecyclerService {

	@Autowired
	private RecyclerRepository recyclerRepo;
	@Autowired
	private CollectorRepository collectorRepo;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private JwtUtil jwtUtil;

	
	public ResponseEntity<?> registerRecycler(RegisterRecyclerRequest req){
		Recycler recycler=RecyclerMapper.toEntity(req);
		
		recycler.setPassword(passwordEncoder.encode(req.getPassword()));
		recyclerRepo.save(recycler);
		
		return new ResponseEntity<>(RecyclerMapper.toResponse(recycler, "Recycler Created Successfully"),HttpStatus.CREATED);
	}
	
	public ResponseEntity<?> registerCollector(RegisterCollectorRequest req,String token){
		
		String email=jwtUtil.extractClaims(token.substring(7)).getSubject();
		
		System.out.println(email);
			
		Recycler recycler=recyclerRepo.findByEmail(email)
										.orElseThrow(()-> new UserNotFoundException("Recycler not found"));
		
		Collector collector=CollectorMapper.toEntity(req);
		
		collector.setPassword(passwordEncoder.encode(req.getPassword()));
		collector.setRecycler(recycler);
		collectorRepo.save(collector);
		
		return new ResponseEntity<>(CollectorMapper.toResponse(collector, "Collector Created Successfully"),HttpStatus.CREATED);
	}
}
