package com.ey.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.ey.dto.request.AddCatagory;
import com.ey.dto.response.CatagoryResponse;
import com.ey.dto.response.CollectorResponse;
import com.ey.dto.response.RecyclerResponse;
import com.ey.dto.response.UserResponse;
import com.ey.enums.RecyclerStatus;
import com.ey.exception.CatagoryNotFound;
import com.ey.exception.UserNotFoundException;
import com.ey.mapper.CatagoryMapper;
import com.ey.mapper.CollectorMapper;
import com.ey.mapper.RecyclerMapper;
import com.ey.mapper.UserMapper;
import com.ey.model.Catagory;
import com.ey.model.Collector;
import com.ey.model.Recycler;
import com.ey.model.User;
import com.ey.repository.CatagoryRepository;
import com.ey.repository.CollectorRepository;
import com.ey.repository.DisposeRepository;
import com.ey.repository.RecyclerRepository;
import com.ey.repository.UserRepository;

@Service
public class AdminService {

	@Autowired
	private UserRepository userRepo;
	@Autowired
	private CollectorRepository collectorRepo;
	@Autowired
	private RecyclerRepository recyclerRepo;
	@Autowired
	private DisposeRepository disposeRepo;
	@Autowired
	private CatagoryRepository cataRepo;
	
	Logger log = LoggerFactory.getLogger(AdminService.class);
	public ResponseEntity<?> getAllUsers(){
		List<UserResponse> users=userRepo.findAll()
										.stream()
										.map(s-> UserMapper.toResponse(s))
										.toList();
		log.info("all users fetch is successfull");
		return new ResponseEntity<>(users,HttpStatus.OK);
	}
	public ResponseEntity<?> getUserById(Long id){
		User user=userRepo.findById(id).orElseThrow(()-> new UserNotFoundException("Invalid user id"));
		
		
		log.info("user fetch by id is successfull");
		return new ResponseEntity<>(UserMapper.toResponse(user),HttpStatus.OK);
	}
	
	public ResponseEntity<?> getUserByEmail(String email){
		User user=userRepo.findByEmail(email).orElseThrow(()-> new UserNotFoundException("Invalid user email"));
		
		
		log.info("users fetch by email is successfull");
		return new ResponseEntity<>(UserMapper.toResponse(user),HttpStatus.OK);
	}
	
	
	
	
	
	public ResponseEntity<?> approveRecycler(Long id){
		Recycler recycler=recyclerRepo.findById(id).orElseThrow(()-> new UserNotFoundException("Invalid Recycler id"));
		recycler.setStatus(RecyclerStatus.APPROVED);
		recyclerRepo.save(recycler);
		
		log.info("recycler approve is successfull");
		return new ResponseEntity<>(RecyclerMapper.toResponse(recycler, "REcycler Status updated successfullly"),HttpStatus.ACCEPTED);
	}
	public ResponseEntity<?> rejectRecycler(Long id){
		Recycler recycler=recyclerRepo.findById(id).orElseThrow(()-> new UserNotFoundException("Invalid Recycler id"));
		recycler.setStatus(RecyclerStatus.REJECTED);
		recyclerRepo.save(recycler);
		
		log.info("recycler reject is successfull");
		return new ResponseEntity<>(RecyclerMapper.toResponse(recycler, "Recycler Status updated successfullly"),HttpStatus.ACCEPTED);
	}
	public ResponseEntity<?> getAllRecyclers(){
		List<RecyclerResponse> recyclers=recyclerRepo.findAll()
											.stream()
											.map(s-> RecyclerMapper.toResponse(s))
											.toList();
		
		log.info("all recycler fetch is successfull");
		return new ResponseEntity<>(recyclers,HttpStatus.OK);
	}
	
	public ResponseEntity<?> getRecyclerById(Long id){
		Recycler recycler=recyclerRepo.findById(id).orElseThrow(()-> new UserNotFoundException("Invalid recycler id"));
		
		log.info("recycler fetch by id is successfull");
		return new ResponseEntity<>(RecyclerMapper.toResponse(recycler),HttpStatus.OK);
	}
	
	public ResponseEntity<?> getRecyclerByEmail(String email){
		Recycler recycler=recyclerRepo.findByEmail(email).orElseThrow(()-> new UserNotFoundException("Invalid recycler email"));
		
		log.info("recycler fetch by email is successfull");
		return new ResponseEntity<>(RecyclerMapper.toResponse(recycler),HttpStatus.OK);
	}
	
	
	public ResponseEntity<?> getAllCollectors(){
		List<CollectorResponse> collectors=collectorRepo.findAll()
				.stream()
				.map(s-> CollectorMapper.toResponse(s))
				.toList();
		
		log.info("All collector fetch is successfull");
		return new ResponseEntity<>(collectors,HttpStatus.OK);
	}
	
	public ResponseEntity<?> getCollectorById(Long id){
		Collector collector=collectorRepo.findById(id).orElseThrow(()-> new UserNotFoundException("Invalid Collector id"));
		
		log.info("collector fetch by id is successfull");
		return new ResponseEntity<>(CollectorMapper.toResponse(collector),HttpStatus.OK);
	}
	
	public ResponseEntity<?> getCollectorByEmail(String email){
		Collector collector=collectorRepo.findByEmail(email).orElseThrow(()-> new UserNotFoundException("Invalid Collector email"));
		
		log.info("collector fetch by email is successfull");
		return new ResponseEntity<>(CollectorMapper.toResponse(collector),HttpStatus.OK);
	}
	
	
	
	
	public ResponseEntity<?> addCatagory(AddCatagory req){
		Catagory cata=CatagoryMapper.toEntity(req);
		
		cataRepo.save(cata);
		
		log.info("catagory added successfully");
		return new ResponseEntity<>(CatagoryMapper.toResponse(cata,"Catagory Created Successfully"),HttpStatus.CREATED);
	}
	public ResponseEntity<?> updateCatagory(AddCatagory req,Long id){
		Catagory cata=cataRepo.findById(id)
								.orElseThrow(()->new CatagoryNotFound("Invalid Catagory id"));
		
		cata.setHandlingRules(req.getHandlingRules());
		cata.setLevel(req.getLevel());
		cata.setName(req.getName());
		cata.setRefurbishable(req.isRefurbishable());
		cataRepo.save(cata);
		
		log.info("catagory updated successfully");
		return new ResponseEntity<>(CatagoryMapper.toResponse(cata,"Catagory Updated Successfully"),HttpStatus.CREATED);
	}
	public ResponseEntity<?> deleteCatagory(Long id){
		Catagory cata=cataRepo.findById(id)
				.orElseThrow(()->new CatagoryNotFound("Invalid Catagory id"));
		
		List<Long> disposeId=disposeRepo.findByCatagoryId(id)
										.stream()
										.map(s->s.getId())
										.toList();
		if(!disposeId.isEmpty()) {
			return new ResponseEntity<>("dispose with id"+disposeId.toString()+" are created using catagoryid cant be deleted",HttpStatus.BAD_REQUEST);
		}
										
		cataRepo.deleteById(id);
		
		log.info("catagory deleted successfully");
		return new ResponseEntity<>("Catagory deleted Successfully",HttpStatus.ACCEPTED);
	}
	
	public ResponseEntity<?> getCatagoryById(Long id){
		Catagory cata=cataRepo.findById(id).orElseThrow(()-> new CatagoryNotFound("Invalid catagory id"));
		
		
		log.info("catagory fetch by id is successfull");
		return new ResponseEntity<>(CatagoryMapper.toResponse(cata),HttpStatus.OK);
	}
	public ResponseEntity<?> getAllCatagory(){
		List<CatagoryResponse> res=cataRepo.findAll()
										.stream()
										.map(s-> CatagoryMapper.toResponse(s))
										.toList();
		
		
		log.info("all catagory fetch is successfull");
		return new ResponseEntity<>(res,HttpStatus.OK);
	}
}
