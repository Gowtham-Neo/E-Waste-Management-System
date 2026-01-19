package com.ey.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.ey.dto.request.user.UserLoginRequest;
import com.ey.dto.response.AuthResponse;
import com.ey.enums.Role;
import com.ey.exception.InvalidPasswordException;
import com.ey.exception.UserNotFoundException;
import com.ey.model.Collector;
import com.ey.model.Recycler;
import com.ey.model.User;
import com.ey.repository.CollectorRepository;
import com.ey.repository.RecyclerRepository;
import com.ey.repository.UserRepository;
import com.ey.security.JwtUtil;

@Service
public class AuthService {

	@Autowired
    private UserRepository userRepo;
	@Autowired
    private RecyclerRepository recyclerRepo;
	@Autowired
    private CollectorRepository collectorRepo;
	@Autowired
    private JwtUtil jwtUtil;
	@Autowired
    private BCryptPasswordEncoder encoder;

	Logger log = LoggerFactory.getLogger(AuthService.class);

    public ResponseEntity<?> loginUser(UserLoginRequest request) {

        User user = userRepo.findByEmail(request.getEmail())
                .orElseThrow(() -> new UserNotFoundException("User not found"));

        if (!encoder.matches(request.getPassword(), user.getPassword())) {
            log.error("invalid password");
            throw new InvalidPasswordException("Invalid password");
        }

        String token = jwtUtil.generateToken(
                user.getEmail(),
                user.getRole().name() 
        		);

        System.out.println("Heree1");
        AuthResponse aures=new AuthResponse(token, user.getRole().name());
        
        log.info("user login successfull");
        return new ResponseEntity<>(aures,HttpStatus.OK);
    }
    public ResponseEntity<?> loginAdmin(UserLoginRequest request) {
    	
    	User user = userRepo.findByEmail(request.getEmail())
    			.orElseThrow(() -> new UserNotFoundException("User not found"));
    	
    	if (!encoder.matches(request.getPassword(), user.getPassword())) {
            log.error("invalid password");
    		throw new InvalidPasswordException("Invalid password");
    	}
    	
    	String token = jwtUtil.generateToken(
    			user.getEmail(),
    			user.getRole().name());
    	
    	System.out.println("Heree1");
    	AuthResponse aures=new AuthResponse(token, user.getRole().name());
    	
        log.info("admin login successfull");
    	return new ResponseEntity<>(aures,HttpStatus.OK);
    }

    public AuthResponse loginRecycler(UserLoginRequest request) {

        Recycler recycler = recyclerRepo.findByEmail(request.getEmail())
                .orElseThrow(() -> new UserNotFoundException("Recycler not found"));

        if (!encoder.matches(request.getPassword(), recycler.getPassword())) {
            log.error("invalid password");
            throw new InvalidPasswordException("Invalid password");
        }

        String token = jwtUtil.generateToken(
                recycler.getEmail(),
                Role.RECYCLER.name()
        );

        log.info("recycler login successfull");
        return new AuthResponse(token, "RECYCLER");
    }

    
    public AuthResponse loginCollector(UserLoginRequest request) {

        Collector collector = collectorRepo.findByEmail(request.getEmail())
                .orElseThrow(() -> new UserNotFoundException("Collector not found"));

        if (!encoder.matches(request.getPassword(), collector.getPassword())) {
            log.error("invalid password");
            throw new InvalidPasswordException("Invalid password");
        }

        String token = jwtUtil.generateToken(
                collector.getEmail(),
                Role.COLLECTOR.name()
        );

        log.info("collector login successfull");
        return new AuthResponse(token, "COLLECTOR");
    }
}
