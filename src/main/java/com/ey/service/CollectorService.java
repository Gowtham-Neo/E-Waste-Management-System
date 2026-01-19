package com.ey.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.ey.dto.response.DisposeResponse;
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
	
	Logger log = LoggerFactory.getLogger(CollectorService.class);

	public ResponseEntity<?> collectDiposeRequest(Long id,String token) {
		
		Dispose dis=disposeRepo.findById(id).orElseThrow(()->new DisposeNotFound("Invalid Dispose Id"));
		
		Collector coll=collectorRepo.findByEmail(jwtUtil.extractSubject(token)).orElseThrow(()->new UserNotFoundException("Invalid collector id"));
		
		if (dis.getCollector()==null) {
			log.warn("Not Assigned collector tries to collect the disposes");
			return new ResponseEntity<>("Not Assigned collector tries to collect the disposes",HttpStatus.BAD_REQUEST);
		}
		
		if (coll.getId()!=dis.getCollector().getId()) {
			log.warn("Not Assigned collector tries to collect the disposes");
			return new ResponseEntity<>("Not Assigned collector tries to collect the disposes",HttpStatus.BAD_REQUEST);
		}
		dis.setStatus(RequestStatus.COLLECTED);
		disposeRepo.save(dis);
		
		log.info("dispose products collected successfull");

		return new ResponseEntity<>(DisposeMapper.toResponse(dis, "Dipose Products collected Successfully"),HttpStatus.ACCEPTED);
	}
	
	
	public ResponseEntity<?> cancelDisposeRequest(Long id,String token) {
		
		Dispose dis=disposeRepo.findById(id).orElseThrow(()->new DisposeNotFound("Invalid Dispose Id"));
		
		Collector coll=collectorRepo.findByEmail(jwtUtil.extractSubject(token)).orElseThrow(()->new UserNotFoundException("Invalid collector id"));
		
		if (dis.getCollector()==null) {
			log.warn("Not Assigned collector tries to collect the disposes");
			return new ResponseEntity<>("Not Assigned collector tries to collect the disposes",HttpStatus.BAD_REQUEST);
		}
		
		if (coll.getId()!=dis.getCollector().getId()) {
			log.warn("Not Assigned collector tries to collect the disposes");
			return new ResponseEntity<>("Not Assigned collector tries to collect the disposes",HttpStatus.BAD_REQUEST);
		}
		dis.setStatus(RequestStatus.CANCELLED);
		disposeRepo.save(dis);
		
		log.info("dispose products cancelled successfull");
		return new ResponseEntity<>(DisposeMapper.toResponse(dis, "Dispose products collection cancelled as this is fake request"),HttpStatus.ACCEPTED);
	}
	
	
	public ResponseEntity<?> getAllDiposeRequest(String token) {
		
		
		Collector coll=collectorRepo.findByEmail(jwtUtil.extractSubject(token)).orElseThrow(()->new UserNotFoundException("Invalid collector id"));
		
		List<DisposeResponse> disposes=disposeRepo.findByCollectorId(coll.getId())
												.stream()
												.map(s-> DisposeMapper.toResponse(s))
												.toList();
		
		log.info("all dispose fetch is successfull");
		return new ResponseEntity<>(disposes,HttpStatus.OK);
	}
	public ResponseEntity<?> getDiposeRequestById(Long id,String token) {
		
		Dispose dis=disposeRepo.findById(id).orElseThrow(()-> new DisposeNotFound("Invalid dispose Id"));
		Collector coll=collectorRepo.findByEmail(jwtUtil.extractSubject(token)).orElseThrow(()->new UserNotFoundException("Invalid collector id"));
		
		if (dis.getCollector().getId()!=coll.getId()) {
			
			log.warn("this dispose is not assigned to you");
			return new ResponseEntity<>("this dispose is not assigned to you",HttpStatus.BAD_REQUEST);
		}
		
		log.info("dispose fetch by id is successfull");
		return new ResponseEntity<>(DisposeMapper.toResponse(dis),HttpStatus.OK);
	}
	
	
}
