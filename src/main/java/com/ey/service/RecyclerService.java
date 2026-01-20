package com.ey.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.ey.dto.request.RegisterCollectorRequest;
import com.ey.dto.request.recycler.RegisterRecyclerRequest;
import com.ey.dto.request.recycler.UpdateRecyclerDetailsRequest;
import com.ey.dto.request.user.UserForgetPassordRequest;
import com.ey.dto.request.user.UserResetPassordRequest;
import com.ey.dto.response.CollectorResponse;
import com.ey.exception.UserAlreadyExsistsException;
import com.ey.exception.UserNotFoundException;
import com.ey.mapper.CollectorMapper;
import com.ey.mapper.RecyclerMapper;
import com.ey.model.Collector;
import com.ey.model.Recycler;
import com.ey.repository.CollectorRepository;
import com.ey.repository.DisposeRepository;
import com.ey.repository.RecyclerRepository;
import com.ey.security.JwtUtil;

@Service
public class RecyclerService {

	@Autowired
	private RecyclerRepository recyclerRepo;
	@Autowired
	private CollectorRepository collectorRepo;
	@Autowired
	private DisposeRepository disposeRepo;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private JwtUtil jwtUtil;
	
	Logger log = LoggerFactory.getLogger(RecyclerService.class);

	public ResponseEntity<?> registerRecycler(RegisterRecyclerRequest req) {
		if (recyclerRepo.findByEmail(req.getEmail()).isPresent()){
			log.error("email already registered");
            throw new UserAlreadyExsistsException("email already registered");
		}
		Recycler recycler = RecyclerMapper.toEntity(req);

		recycler.setPassword(passwordEncoder.encode(req.getPassword()));
		recyclerRepo.save(recycler);

		log.info("Recycler Created Successfully");
		return new ResponseEntity<>(RecyclerMapper.toResponse(recycler, "Recycler Created Successfully"),
				HttpStatus.CREATED);
	}

	public ResponseEntity<?> getMyDetails(String token) {

		Recycler recycler = recyclerRepo.findByEmail(jwtUtil.extractSubject(token))
				.orElseThrow(() -> new UserNotFoundException("Invalid user login"));

		log.info("Recycler Fetch is Successfully");
		return new ResponseEntity<>(RecyclerMapper.toResponse(recycler, "Recycler Fetch is Successfully"),
				HttpStatus.OK);
	}

	public ResponseEntity<?> updateRecycler(UpdateRecyclerDetailsRequest req, String token) {

		Recycler recycler = recyclerRepo.findByEmail(jwtUtil.extractSubject(token))
				.orElseThrow(() -> new UserNotFoundException("Invalid Recycler."));
		if (recyclerRepo.findByEmail(req.getEmail()).isPresent()){
			log.error("email already registered");
            throw new UserAlreadyExsistsException("email already registered");
		}
		recycler.setEmail(req.getEmail());
		recycler.setMobileNumber(req.getMobileNumber());
		recycler.setOrganizationName(req.getOrganizationName());
		recycler.setLicenceNumber(req.getLicenceNumber());
		recyclerRepo.save(recycler);

		log.info( "Recycler details updated Successfully");
		
		return new ResponseEntity<>(RecyclerMapper.toResponse(recycler, "Recycler details updated Successfully"),
				HttpStatus.ACCEPTED);
	}

	public ResponseEntity<?> resetPassword(UserResetPassordRequest req, String token) {

		if (!req.getNewPassword().equals(req.getConfirmPassword())) {
			log.error("password mismatch");
			return new ResponseEntity<>("password mismatch", HttpStatus.BAD_REQUEST);
		}

		Recycler recycler = recyclerRepo.findByEmail(jwtUtil.extractSubject(token))
				.orElseThrow(() -> new UserNotFoundException("Invalid Recycler."));
		recycler.setPassword(passwordEncoder.encode(req.getConfirmPassword()));
		recyclerRepo.save(recycler);

		log.info("Password reser Successfully");
		return new ResponseEntity<>(RecyclerMapper.toResponse(recycler, "Password reser Successfully"),
				HttpStatus.ACCEPTED);
	}

	public ResponseEntity<?> forgetPassword(UserForgetPassordRequest req) {
		if (!req.getNewPassword().equals(req.getConfirmPassword())) {
			log.error("password mismatch");
			return new ResponseEntity<>("password mismatch", HttpStatus.BAD_REQUEST);
		}

		Recycler recycler = recyclerRepo.findByEmail(req.getEmail())
				.orElseThrow(() -> new UserNotFoundException("Invalid Recycler."));
		recycler.setPassword(passwordEncoder.encode(req.getConfirmPassword()));
		recyclerRepo.save(recycler);

		log.info( "Password change Successfully");
		return new ResponseEntity<>(RecyclerMapper.toResponse(recycler, "Password change Successfully"),
				HttpStatus.ACCEPTED);
	}

	
	
	
	public ResponseEntity<?> registerCollector(RegisterCollectorRequest req, String token) {
		
		if (collectorRepo.findByEmail(req.getEmail()).isPresent()){
			log.error("email already registered");
            throw new UserAlreadyExsistsException("email already registered");
		}

		String email = jwtUtil.extractClaims(token.substring(7)).getSubject();

		System.out.println(email);

		Recycler recycler = recyclerRepo.findByEmail(email)
				.orElseThrow(() -> new UserNotFoundException("Recycler not found"));

		Collector collector = CollectorMapper.toEntity(req);

		collector.setPassword(passwordEncoder.encode(req.getPassword()));
		collector.setRecycler(recycler);
		collectorRepo.save(collector);

		log.info("Collector Created Successfully");
		return new ResponseEntity<>(CollectorMapper.toResponse(collector, "Collector Created Successfully"),
				HttpStatus.CREATED);
	}
	
	
	public ResponseEntity<?> updateCollector(RegisterCollectorRequest req, Long id,String token) {
		
		Recycler recycler = recyclerRepo.findByEmail(jwtUtil.extractSubject(token))
				.orElseThrow(() -> new UserNotFoundException("Invalid Recycler login"));

		Collector coll = collectorRepo.findById(id)
				.orElseThrow(() -> new UserNotFoundException("Invalid Collector Id"));
		
		if (recycler.getId() != coll.getRecycler().getId()) {
			log.error("You can access other collector");
			return new ResponseEntity<>("You can access other collector", HttpStatus.BAD_REQUEST);
		}
		if (collectorRepo.findByEmail(req.getEmail()).isPresent()){
			log.error("email already registered");
            throw new UserAlreadyExsistsException("email already registered");
		}
		coll.setEmail(req.getEmail());
		coll.setMobileNumber(req.getMobileNumber());
		coll.setPassword(passwordEncoder.encode(req.getPassword()));
		coll.setVehicleNumber(req.getVehicleNumber());
		collectorRepo.save(coll);
		
		log.info("Collector Updated Successfully");
		return new ResponseEntity<>(CollectorMapper.toResponse(coll, "Collector Updated Successfully"),
				HttpStatus.ACCEPTED);
	}
	
	
	public ResponseEntity<?> deleteCollector(Long id,String token) {
		
		Recycler recycler = recyclerRepo.findByEmail(jwtUtil.extractSubject(token))
				.orElseThrow(() -> new UserNotFoundException("Invalid Recycler login"));
		
		Collector coll = collectorRepo.findById(id)
				.orElseThrow(() -> new UserNotFoundException("Invalid Collector Id"));
		
		if (recycler.getId() != coll.getRecycler().getId()) {
			log.error("You can access other collector");
			return new ResponseEntity<>("You can access other collector", HttpStatus.BAD_REQUEST);
		}
		
		List<Long> disposeId=disposeRepo.findByCollectorId(id)
											.stream()
											.map(s->s.getId())
											.toList();
		if (!disposeId.isEmpty()) {
			return new ResponseEntity<>("collector is assigned to these dispose id"+disposeId.toString()+"cant be deleted",HttpStatus.BAD_GATEWAY);
		}
		collectorRepo.deleteById(id);
		
		log.info("Collector Deleted Successfully");
		return new ResponseEntity<>("Collector Deleted Successfully",HttpStatus.ACCEPTED);
	}

	
	public ResponseEntity<?> getMyCollectors(String token) {

		Recycler recycler = recyclerRepo.findByEmail(jwtUtil.extractSubject(token))
				.orElseThrow(() -> new UserNotFoundException("Invalid Recycler."));

		List<CollectorResponse> collectors = collectorRepo.findByRecyclerId(recycler.getId()).stream()
				.map(s -> CollectorMapper.toResponse(s)).toList();

		log.info("Collector fetch by recycler is successfull");
		return new ResponseEntity<>(collectors, HttpStatus.OK);
	}

	
	public ResponseEntity<?> getMyCollectorById(Long id, String token) {

		Recycler recycler = recyclerRepo.findByEmail(jwtUtil.extractSubject(token))
				.orElseThrow(() -> new UserNotFoundException("Invalid Recycler."));

		Collector coll = collectorRepo.findById(id)
				.orElseThrow(() -> new UserNotFoundException("Invalid Collector Id"));
		if (recycler.getId() != coll.getRecycler().getId()) {
			log.error("You can access other collector");
			return new ResponseEntity<>("You can access other collector", HttpStatus.BAD_REQUEST);
		}
		log.info("Collector fetch by id by recycler is successfull");
		return new ResponseEntity<>(CollectorMapper.toResponse(coll), HttpStatus.OK);
	}

}
