package com.ey.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.ey.dto.request.AssignCollectorRequest;
import com.ey.dto.request.recycler.DisposeRequest;
import com.ey.dto.response.DisposeMsgResponse;
import com.ey.dto.response.DisposeResponse;
import com.ey.enums.RecyclerStatus;
import com.ey.enums.RequestStatus;
import com.ey.exception.CatagoryNotFound;
import com.ey.exception.DisposeNotFound;
import com.ey.exception.UserNotFoundException;
import com.ey.mapper.DisposeMapper;
import com.ey.model.Catagory;
import com.ey.model.Collector;
import com.ey.model.Dispose;
import com.ey.model.Recycler;
import com.ey.model.User;
import com.ey.repository.CatagoryRepository;
import com.ey.repository.CollectorRepository;
import com.ey.repository.DisposeRepository;
import com.ey.repository.RecyclerRepository;
import com.ey.repository.UserRepository;
import com.ey.security.JwtUtil;

@Service
public class DisposeService {

	@Autowired
	private DisposeRepository disposeRepo;
	@Autowired
	private CollectorRepository collectorRepo;
	@Autowired
	private RecyclerRepository recyclerRepo;
	@Autowired
	private UserRepository userRepo;
	@Autowired
	private JwtUtil jwtUtil;
	@Autowired
	private CatagoryRepository cataRepo;

	Logger log = LoggerFactory.getLogger(DisposeService.class);
	
	public ResponseEntity<DisposeMsgResponse> disposeProduct(DisposeRequest req,String token){
		
		Catagory cata=cataRepo.findById(req.getCatagoryId())
									.orElseThrow(()-> new CatagoryNotFound("Invalid Catagory Id"));
		
		String  email= jwtUtil.extractClaims(token.substring(7)).getSubject();
		User user=userRepo.findByEmail(email)
									.orElseThrow(()->new UserNotFoundException("Invalid user"));
		
		Dispose dis=DisposeMapper.toEntity(req,cata);
		dis.setUser(user);
		
		disposeRepo.save(dis);
		
		log.info("Dipose Created Successfully");
		return new ResponseEntity<>(DisposeMapper.toResponse(dis, "Dispose Request created Successfully"),HttpStatus.CREATED);
	}
	
	
	public ResponseEntity<?> updateDisposeProduct(DisposeRequest req,Long id,String token){
		
		User user=userRepo.findByEmail(jwtUtil.extractSubject(token))
				.orElseThrow(()-> new UserNotFoundException("Invalid user Login"));
		
		Dispose dis=disposeRepo.findById(id)
				.orElseThrow(()-> new DisposeNotFound("Invalid Dispose Id"));
		
		if (user.getId()!=dis.getUser().getId()) {
			log.error("Dispose Request is not requested by you and you cant access it");
			return new ResponseEntity<>("Dispose Request is not requested by you and you cant access it",HttpStatus.BAD_REQUEST);
		}
		
		if (dis.getStatus().equals(RequestStatus.COLLECTED)||dis.getStatus().equals(RequestStatus.COMPLETED)
				||dis.getStatus().equals(RequestStatus.INSPECTED)) {
			log.error("Dispose Request has already in "+ dis.getStatus()+" stage, and cant be updated");
			return new ResponseEntity<>("Dispose Request has already in "+ dis.getStatus()+" stage, and cant be updated",HttpStatus.BAD_REQUEST);
			
		}
		Catagory cata=cataRepo.findById(req.getCatagoryId())
				.orElseThrow(()-> new CatagoryNotFound("Invalid Catagory Id"));
		
		dis.setCatagory(cata);
		dis.setLocation(req.getLocation());
		dis.setQuantity(req.getQuantity()+dis.getQuantity());
		disposeRepo.save(dis);
		
		log.info("Dipose updated Successfully");
		return new ResponseEntity<>(DisposeMapper.toResponse(dis, "Dispose Request Updated Successfully"),HttpStatus.ACCEPTED);
	}
	
	
	public ResponseEntity<?> deleteDisposeProduct(Long id,String token){
		
		User user=userRepo.findByEmail(jwtUtil.extractSubject(token))
				.orElseThrow(()-> new UserNotFoundException("Invalid user Login"));
		
		Dispose dis=disposeRepo.findById(id)
				.orElseThrow(()-> new DisposeNotFound("Invalid Dispose Id"));
		
		if (user.getId()!=dis.getUser().getId()) {
			log.error("Dispose Request is not requested by you and you cant access it");
			return new ResponseEntity<>("Dispose Request is not requested by you and you cant access it",HttpStatus.BAD_REQUEST);
		}
		
		if (dis.getStatus().equals(RequestStatus.COLLECTED)||dis.getStatus().equals(RequestStatus.COMPLETED)
				||dis.getStatus().equals(RequestStatus.INSPECTED)) {
			log.error("Dispose Request has already in "+ dis.getStatus()+" stage, and cant be deleted");
			return new ResponseEntity<>("Dispose Request has already in "+ dis.getStatus()+" stage, and cant be deleted",HttpStatus.BAD_REQUEST);
			
		}
		disposeRepo.deleteById(id);
		
		
		log.info("Dipose deleted Successfully");

		return new ResponseEntity<>("Dispose Request Deleted Successfully",HttpStatus.ACCEPTED);
	}
	
	public ResponseEntity<?> getAllDisposeRequest(String token){
		
		User user=userRepo.findByEmail(jwtUtil.extractSubject(token))
							.orElseThrow(()-> new UserNotFoundException("Invalid user Login"));
		
		List<DisposeResponse> disposes=disposeRepo.findByUserId(user.getId())
													.stream()
													.map(s->DisposeMapper.toResponse(s))
													.toList();
		
		log.info("Diposes fetch Successfully");

		return new ResponseEntity<>(disposes,HttpStatus.OK);
	}
	
	public ResponseEntity<?> getDisposeById(Long id,String token){
		
		User user=userRepo.findByEmail(jwtUtil.extractSubject(token))
				.orElseThrow(()-> new UserNotFoundException("Invalid user Login"));
		
		Dispose dispose=disposeRepo.findById(id)
				.orElseThrow(()-> new DisposeNotFound("Invalid Dispose Id"));
		
		if (user.getId()!=dispose.getUser().getId()) {
			log.error("Dispose Request is not requested by you and you can access it");
			return new ResponseEntity<>("Dispose Request is not requested by you and you can access it",HttpStatus.BAD_REQUEST);
		}
		
		log.info("Dipose fetch by id Successfully");

		return new ResponseEntity<>(DisposeMapper.toResponse(dispose),HttpStatus.CREATED);
	}
	
	
	
	
	
	public ResponseEntity<?> getAllDisposeByAdmin(){
		
		
		List<DisposeResponse> disposes=disposeRepo.findAll()
											.stream()
											.map(s->DisposeMapper.toResponse(s))
											.toList();
		
		log.info("all Dipose fetch by admin  Successfully");
		return new ResponseEntity<>(disposes,HttpStatus.OK);
	}
	
	public ResponseEntity<?> getDisposeByIdByAdmin(Long id){
		
		
		Dispose dispose=disposeRepo.findById(id)
				.orElseThrow(()-> new DisposeNotFound("Invalid Dispose Id"));
		
		
		log.info("Dipose fetch by id by admin Successfully");
		return new ResponseEntity<>(DisposeMapper.toResponse(dispose),HttpStatus.OK);
	}
	
	
	
	
	public ResponseEntity<?> assignCollector(AssignCollectorRequest req,Long id,String token){
		
		Recycler recycler=recyclerRepo.findByEmail(jwtUtil.extractSubject(token))
				.orElseThrow(()-> new UserNotFoundException("Invalid recycler Login"));
		
		Collector coll=collectorRepo.findById(req.getCollectorId())
										.orElseThrow(()-> new UserNotFoundException("Invlaid Collector Id"));
		
		Dispose dis=disposeRepo.findById(id)
										.orElseThrow(()-> new DisposeNotFound("Invlaid dispose Id"));
		
		if (coll.getRecycler().getId()!=recycler.getId()) {
			log.error("Tring to assign other collectors");
			return new ResponseEntity<>("Tring to assign other collectors",HttpStatus.BAD_REQUEST);

		}
		
		if (!coll.getRecycler().getStatus().equals(RecyclerStatus.APPROVED)) {
			log.error(coll.getRecycler().getStatus()+" recycler cant assgin collectors,need admin approval");
			return new ResponseEntity<>(coll.getRecycler().getStatus()+" recycler cant assgin collectors,need admin approval",HttpStatus.BAD_REQUEST);
		}
		
		if (dis.getStatus().equals(RequestStatus.COLLECTED)||dis.getStatus().equals(RequestStatus.COMPLETED)
				||dis.getStatus().equals(RequestStatus.INSPECTED)) {
			log.error("Dispose Request has already in "+ dis.getStatus()+" stage, and cant be assigned");
			return new ResponseEntity<>("Dispose Request has already in "+ dis.getStatus()+" stage, and cant be assigned",HttpStatus.BAD_REQUEST);
			
		}
		
		dis.setCollector(coll);
		dis.setStatus(RequestStatus.ASSIGNED);
	
		disposeRepo.save(dis);
		
		log.info("collector assigned Successfully");
		return new ResponseEntity<>(DisposeMapper.toResponseWithCollector(dis, "Collector Assigned Successfully"),HttpStatus.CREATED);
	}
}
