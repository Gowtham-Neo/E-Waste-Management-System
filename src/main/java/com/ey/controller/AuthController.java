package com.ey.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ey.dto.request.user.UserLoginRequest;
import com.ey.dto.response.AuthResponse;
import com.ey.service.AuthService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/auth")
public class AuthController {

	@Autowired
    private AuthService authService;


    @PostMapping("/user/login")
    public ResponseEntity<?> userLogin(@Valid @RequestBody UserLoginRequest request) {
        return authService.loginUser(request);
    }
    
    @PostMapping("/user/logout")
    public ResponseEntity<?> logoutByUser() {
        return new ResponseEntity<>("Logout Successfull",HttpStatus.OK);
    }
    
    
    @PostMapping("/admin/login")
    public ResponseEntity<?> adminLogin(@Valid @RequestBody UserLoginRequest request) {
    	return authService.loginAdmin(request);
    }
    @PostMapping("/admin/logout")
    public ResponseEntity<?> logoutAmdin() {
        return new ResponseEntity<>("Logout Successfull",HttpStatus.OK);
    }

    
    
    @PostMapping("/recycler/login")
    public AuthResponse recyclerLogin(@Valid @RequestBody UserLoginRequest request) {
        return authService.loginRecycler(request);
    }

    @PostMapping("/recycler/logout")
    public ResponseEntity<?> logoutRecycler() {
        return new ResponseEntity<>("Logout Successfull",HttpStatus.OK);
    }
    
    
    @PostMapping("/collector/login")
    public AuthResponse collectorLogin(@Valid @RequestBody UserLoginRequest request) {
        return authService.loginCollector(request);
    }
    @PostMapping("/collector/logout")
    public ResponseEntity<?> logoutBYCollector() {
        return new ResponseEntity<>("Logout Successfull",HttpStatus.OK);
    }
    
}
