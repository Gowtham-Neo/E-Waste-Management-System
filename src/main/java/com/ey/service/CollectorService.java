package com.ey.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.ey.enums.RequestStatus;
import com.ey.exception.DisposeNotFound;
import com.ey.mapper.DisposeMapper;
import com.ey.model.Dispose;
import com.ey.repository.CollectorRepository;
import com.ey.repository.DisposeRepository;

@Service
public class CollectorService {

	@Autowired
	private CollectorRepository collectorRepo;
	
	@Autowired
	private DisposeRepository disposeRepo;
	
	

	public ResponseEntity<?> collectDiposeRequest(Long id) {
		
		Dispose dis=disposeRepo.findById(id).orElseThrow(()->new DisposeNotFound("Invalid Dispose Id"));
		dis.setStatus(RequestStatus.COLLECTED);
		
		return new ResponseEntity<>(DisposeMapper.toResponse(dis, "Dipose Products collected Successfully"),HttpStatus.ACCEPTED);
	}
	public ResponseEntity<?> cancelDisposeRequest(Long id) {
		
		Dispose dis=disposeRepo.findById(id).orElseThrow(()->new DisposeNotFound("Invalid Dispose Id"));
		dis.setStatus(RequestStatus.CANCELLED);
		
		return new ResponseEntity<>(DisposeMapper.toResponse(dis, "Dispose products collection cancelled as this is fake request"),HttpStatus.ACCEPTED);
	}
	
	
}
