package com.ey.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.ey.dto.request.AddCatagory;
import com.ey.mapper.CatagoryMapper;
import com.ey.model.Catagory;
import com.ey.repository.CatagoryRepository;

@Service
public class AdminService {

	@Autowired
	private CatagoryRepository cataRepo;
	
	
	public ResponseEntity<?> addCatagory(AddCatagory req){
		Catagory cata=CatagoryMapper.toEntity(req);
		
		cataRepo.save(cata);
		
		
		
		return new ResponseEntity<>(CatagoryMapper.toResponse(cata,"Catagory Created Successfully"),HttpStatus.CREATED);
	}
}
