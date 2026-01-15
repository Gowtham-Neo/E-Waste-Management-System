package com.ey.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.ey.dto.request.RegisterUserRequest;
import com.ey.mapper.UserMapper;
import com.ey.model.User;
import com.ey.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepo;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	public ResponseEntity<?> registerUser(RegisterUserRequest req){
		User user=UserMapper.toEntity(req);
		
		System.out.println("here");
		user.setPassword(passwordEncoder.encode(req.getPassword()));
		userRepo.save(user);
		
		return new ResponseEntity<>(UserMapper.toResponse(user, "User Created Successfully"),HttpStatus.CREATED);
	}
}
