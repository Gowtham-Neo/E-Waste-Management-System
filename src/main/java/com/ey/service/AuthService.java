package com.ey.service;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.ey.dto.request.user.UserLoginRequest;
import com.ey.dto.response.AuthResponse;
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

    private final UserRepository userRepo;
    private final RecyclerRepository recyclerRepo;
    private final CollectorRepository collectorRepo;
    private final JwtUtil jwtUtil;
    private final BCryptPasswordEncoder encoder;

    public AuthService(UserRepository userRepo,
                       RecyclerRepository recyclerRepo,
                       CollectorRepository collectorRepo,
                       JwtUtil jwtUtil,
                       BCryptPasswordEncoder encoder) {
        this.userRepo = userRepo;
        this.recyclerRepo = recyclerRepo;
        this.collectorRepo = collectorRepo;
        this.jwtUtil = jwtUtil;
        this.encoder = encoder;
    }

    public ResponseEntity<?> loginUser(UserLoginRequest request) {

        User user = userRepo.findByEmail(request.getEmail())
                .orElseThrow(() -> new UserNotFoundException("User not found"));

        if (!encoder.matches(request.getPassword(), user.getPassword())) {
            throw new RuntimeException("Invalid password");
        }

        String token = jwtUtil.generateToken(
                user.getEmail(),
                "USER",
                user.getRole().name()
        );

        System.out.println("Heree1");
        AuthResponse aures=new AuthResponse(token, user.getRole().name(), "USER");
        return new ResponseEntity<>(aures,HttpStatus.OK);
    }
    public ResponseEntity<?> loginAdmin(UserLoginRequest request) {
    	
    	User user = userRepo.findByEmail(request.getEmail())
    			.orElseThrow(() -> new RuntimeException("User not found"));
    	
    	if (!encoder.matches(request.getPassword(), user.getPassword())) {
    		throw new RuntimeException("Invalid password");
    	}
    	
    	String token = jwtUtil.generateToken(
    			user.getEmail(),
    			"USER",
    			user.getRole().name()
    			);
    	
    	System.out.println("Heree1");
    	AuthResponse aures=new AuthResponse(token, user.getRole().name(), "USER");
    	return new ResponseEntity<>(aures,HttpStatus.OK);
    }

    public AuthResponse loginRecycler(UserLoginRequest request) {

        Recycler recycler = recyclerRepo.findByEmail(request.getEmail())
                .orElseThrow(() -> new RuntimeException("Recycler not found"));

        if (!encoder.matches(request.getPassword(), recycler.getPassword())) {
            throw new RuntimeException("Invalid password");
        }

        String token = jwtUtil.generateToken(
                recycler.getEmail(),
                "RECYCLER",
                "RECYCLER"
        );

        return new AuthResponse(token, "RECYCLER", "RECYCLER");
    }

    
    public AuthResponse loginCollector(UserLoginRequest request) {

        Collector collector = collectorRepo.findByEmail(request.getEmail())
                .orElseThrow(() -> new RuntimeException("Collector not found"));

        if (!encoder.matches(request.getPassword(), collector.getPassword())) {
            throw new RuntimeException("Invalid password");
        }

        String token = jwtUtil.generateToken(
                collector.getEmail(),
                "COLLECTOR",
                "COLLECTOR"
        );

        return new AuthResponse(token, "COLLECTOR", "COLLECTOR");
    }
}
