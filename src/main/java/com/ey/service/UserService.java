package com.ey.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.ey.dto.request.user.RegisterUserRequest;
import com.ey.dto.request.user.UpdateUserDetailsRequest;
import com.ey.dto.request.user.UserForgetPassordRequest;
import com.ey.dto.request.user.UserResetPassordRequest;
import com.ey.dto.response.DisposeResponse;
import com.ey.exception.DisposeNotFound;
import com.ey.exception.UserNotFoundException;
import com.ey.mapper.DisposeMapper;
import com.ey.mapper.UserMapper;
import com.ey.model.Dispose;
import com.ey.model.User;
import com.ey.repository.DisposeRepository;
import com.ey.repository.UserRepository;
import com.ey.security.JwtUtil;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepo;
	@Autowired
	private DisposeRepository disposeRepo;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private JwtUtil jwtUtil;
	
	public ResponseEntity<?> registerUser(RegisterUserRequest req){
		User user=UserMapper.toEntity(req);
		
		System.out.println("here");
		user.setPassword(passwordEncoder.encode(req.getPassword()));
		userRepo.save(user);
		
		return new ResponseEntity<>(UserMapper.toResponse(user, "User Created Successfully"),HttpStatus.CREATED);
	}
	
	
	public ResponseEntity<?> getMyDetails(String token){
		User user=userRepo.findByEmail(jwtUtil.extractSubject(token)).orElseThrow(()-> new UserNotFoundException("Invalid user login"));
		
		
		return new ResponseEntity<>(UserMapper.toResponse(user, "User Fetched Successfully"),HttpStatus.CREATED);
	}

	
	
	public ResponseEntity<?> updateUser(UpdateUserDetailsRequest req, String token) {
		
		User user=userRepo.findByEmail(jwtUtil.extractSubject(token)).orElseThrow(()-> new UserNotFoundException("Invalid User."));
		user.setEmail(req.getEmail());
		user.setMobileNumber(req.getMobileNumber());
		user.setName(req.getName());
		user.setOrganizationName(req.getOrganizationName());
		userRepo.save(user);
		
		return new ResponseEntity<>(UserMapper.toResponse(user, "user details updated Successfully"),HttpStatus.ACCEPTED);
	}
	
	
	
	public ResponseEntity<?> resetPassword(UserResetPassordRequest req, String token) {
		if (!req.getNewPassword().equals(req.getConfirmPassword())) {
			return new ResponseEntity<>("password mismatch",HttpStatus.BAD_REQUEST);
		}
		
		User user=userRepo.findByEmail(jwtUtil.extractSubject(token)).orElseThrow(()-> new UserNotFoundException("Invalid User."));
		user.setPassword(passwordEncoder.encode(req.getConfirmPassword()));
		userRepo.save(user);
		
		return new ResponseEntity<>(UserMapper.toResponse(user, "Password reser Successfully"),HttpStatus.ACCEPTED);
	}
	
	
	
	public ResponseEntity<?> forgetPassword(UserForgetPassordRequest req) {
		if (!req.getNewPassword().equals(req.getConfirmPassword())) {
			return new ResponseEntity<>("password mismatch",HttpStatus.BAD_REQUEST);
		}
		
		User user=userRepo.findByEmail(req.getEmail()).orElseThrow(()-> new UserNotFoundException("Invalid User."));
		user.setPassword(passwordEncoder.encode(req.getConfirmPassword()));
		userRepo.save(user);
		
		return new ResponseEntity<>(UserMapper.toResponse(user, "Password reset Successfully"),HttpStatus.ACCEPTED);
	}
	
	
}
