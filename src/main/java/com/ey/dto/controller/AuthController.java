package com.ey.dto.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ey.dto.request.CollectorLoginRequest;
import com.ey.dto.request.recycler.RecyclerLoginRequest;
import com.ey.dto.request.user.UserLoginRequest;
import com.ey.dto.response.AuthResponse;
import com.ey.service.AuthService;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/user/login")
    public ResponseEntity<?> userLogin(@RequestBody UserLoginRequest request) {
        return authService.loginUser(request);
    }

    @PostMapping("/recycler/login")
    public AuthResponse recyclerLogin(@RequestBody RecyclerLoginRequest request) {
        return authService.loginRecycler(request);
    }

    @PostMapping("/collector/login")
    public AuthResponse collectorLogin(@RequestBody CollectorLoginRequest request) {
        return authService.loginCollector(request);
    }
}
