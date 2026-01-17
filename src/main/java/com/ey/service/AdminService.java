package com.ey.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.ey.dto.request.AddCatagory;
import com.ey.dto.response.CollectorResponse;
import com.ey.dto.response.RecyclerResponse;
import com.ey.dto.response.UserResponse;
import com.ey.exception.UserNotFoundException;
import com.ey.mapper.CatagoryMapper;
import com.ey.mapper.CollectorMapper;
import com.ey.mapper.RecyclerMapper;
import com.ey.mapper.UserMapper;
import com.ey.model.Catagory;
import com.ey.model.Collector;
import com.ey.model.Recycler;
import com.ey.model.User;
import com.ey.repository.CatagoryRepository;
import com.ey.repository.CollectorRepository;
import com.ey.repository.RecyclerRepository;
import com.ey.repository.UserRepository;

@Service
public class AdminService {

	@Autowired
	private UserRepository userRepo;
	@Autowired
	private CollectorRepository collectorRepo;
	@Autowired
	private RecyclerRepository recyclerRepo;
	@Autowired
	private CatagoryRepository cataRepo;
	
	
	public ResponseEntity<?> getAllUsers(){
		List<UserResponse> users=userRepo.findAll()
										.stream()
										.map(s-> UserMapper.toResponse(s))
										.toList();
		
		return new ResponseEntity<>(users,HttpStatus.OK);
	}
	public ResponseEntity<?> getUserById(Long id){
		User user=userRepo.findById(id).orElseThrow(()-> new UserNotFoundException("Invalid user id"));
		
		return new ResponseEntity<>(UserMapper.toResponse(user),HttpStatus.OK);
	}
	
	public ResponseEntity<?> getUserByEmail(String email){
		User user=userRepo.findByEmail(email).orElseThrow(()-> new UserNotFoundException("Invalid user email"));
		
		return new ResponseEntity<>(UserMapper.toResponse(user),HttpStatus.OK);
	}
	public ResponseEntity<?> getAllRecyclers(){
		List<RecyclerResponse> recyclers=recyclerRepo.findAll()
											.stream()
											.map(s-> RecyclerMapper.toResponse(s))
											.toList();
		
		return new ResponseEntity<>(recyclers,HttpStatus.OK);
	}
	
	public ResponseEntity<?> getRecyclerById(Long id){
		Recycler recycler=recyclerRepo.findById(id).orElseThrow(()-> new UserNotFoundException("Invalid recycler id"));
		
		return new ResponseEntity<>(RecyclerMapper.toResponse(recycler),HttpStatus.OK);
	}
	
	public ResponseEntity<?> getRecyclerByEmail(String email){
		Recycler recycler=recyclerRepo.findByEmail(email).orElseThrow(()-> new UserNotFoundException("Invalid recycler email"));
		
		return new ResponseEntity<>(RecyclerMapper.toResponse(recycler),HttpStatus.OK);
	}
	public ResponseEntity<?> getAllCollectors(){
		List<CollectorResponse> collectors=collectorRepo.findAll()
				.stream()
				.map(s-> CollectorMapper.toResponse(s))
				.toList();
		
		return new ResponseEntity<>(collectors,HttpStatus.OK);
	}
	
	public ResponseEntity<?> getCollectorById(Long id){
		Collector collector=collectorRepo.findById(id).orElseThrow(()-> new UserNotFoundException("Invalid Collector id"));
		
		return new ResponseEntity<>(CollectorMapper.toResponse(collector),HttpStatus.OK);
	}
	
	public ResponseEntity<?> getCollectorByEmail(String email){
		Collector collector=collectorRepo.findByEmail(email).orElseThrow(()-> new UserNotFoundException("Invalid Collector email"));
		
		return new ResponseEntity<>(CollectorMapper.toResponse(collector),HttpStatus.OK);
	}
	
	
	
	
	public ResponseEntity<?> addCatagory(AddCatagory req){
		Catagory cata=CatagoryMapper.toEntity(req);
		
		cataRepo.save(cata);
		
		return new ResponseEntity<>(CatagoryMapper.toResponse(cata,"Catagory Created Successfully"),HttpStatus.CREATED);
	}
}
