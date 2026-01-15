package com.ey.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.ey.dto.request.RegisterCollectorRequest;
import com.ey.mapper.CollectorMapper;
import com.ey.model.Collector;
import com.ey.repository.CollectorRepository;

@Service
public class CollectorService {

	@Autowired
	private CollectorRepository collectorRepo;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	
}
