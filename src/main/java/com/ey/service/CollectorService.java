package com.ey.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.ey.enums.RequestStatus;
import com.ey.exception.DisposeNotFound;
import com.ey.exception.UserNotFoundException;
import com.ey.mapper.DisposeMapper;
import com.ey.model.Collector;
import com.ey.model.Dispose;
import com.ey.repository.CollectorRepository;
import com.ey.repository.DisposeRepository;
import com.ey.security.JwtUtil;

@Service
public class CollectorService {

	@Autowired
	private CollectorRepository collectorRepo;
	
	@Autowired
	private DisposeRepository disposeRepo;
	@Autowired
	private JwtUtil jwtUtil;
	
	

	public ResponseEntity<?> collectDiposeRequest(Long id,String token) {
		
		Dispose dis=disposeRepo.findById(id).orElseThrow(()->new DisposeNotFound("Invalid Dispose Id"));
		dis.setStatus(RequestStatus.COLLECTED);
		
		String email=jwtUtil.extractClaims(token.substring(7)).getSubject();
		Collector coll=collectorRepo.findByEmail(email).orElseThrow(()->new UserNotFoundException("Invalid collector id"));
		
		if (coll.getId()!=dis.getCollector().getId()) {
			return new ResponseEntity<>("Not Assigned collector tries to collect the disposes",HttpStatus.BAD_REQUEST);
		}
		
		return new ResponseEntity<>(DisposeMapper.toResponse(dis, "Dipose Products collected Successfully"),HttpStatus.ACCEPTED);
	}
	
	
	public ResponseEntity<?> cancelDisposeRequest(Long id,String token) {
		
		Dispose dis=disposeRepo.findById(id).orElseThrow(()->new DisposeNotFound("Invalid Dispose Id"));
		
		String email=jwtUtil.extractClaims(token.substring(7)).getSubject();
		Collector coll=collectorRepo.findByEmail(email).orElseThrow(()->new UserNotFoundException("Invalid collector id"));
		
		if (coll.getId()!=dis.getCollector().getId()) {
			return new ResponseEntity<>("Not Assigned collector tries to collect the disposes",HttpStatus.BAD_REQUEST);
		}
		dis.setStatus(RequestStatus.CANCELLED);
		
		
		return new ResponseEntity<>(DisposeMapper.toResponse(dis, "Dispose products collection cancelled as this is fake request"),HttpStatus.ACCEPTED);
	}
	
	
}
