package com.ey.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ey.dto.request.user.UserLoginRequest;
import com.ey.dto.response.AuthResponse;
import com.ey.service.AuthService;

@RestController
@RequestMapping("/auth")
public class AuthController {

	@Autowired
    private AuthService authService;


    @PostMapping("/user/login")
    public ResponseEntity<?> userLogin(@RequestBody UserLoginRequest request) {
        return authService.loginUser(request);
    }
    @PostMapping("/admin/login")
    public ResponseEntity<?> adminLogin(@RequestBody UserLoginRequest request) {
    	return authService.loginAdmin(request);
    }

    @PostMapping("/recycler/login")
    public AuthResponse recyclerLogin(@RequestBody UserLoginRequest request) {
        return authService.loginRecycler(request);
    }

    @PostMapping("/collector/login")
    public AuthResponse collectorLogin(@RequestBody UserLoginRequest request) {
        return authService.loginCollector(request);
    }
}
