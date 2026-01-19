package com.ey.service;

import java.time.LocalDate;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.ey.dto.request.CreateInspectRequest;
import com.ey.dto.response.InspectResponse;
import com.ey.dto.response.RegisterInspectResponse;
import com.ey.enums.ConditionGrading;
import com.ey.enums.DisposalStatus;
import com.ey.enums.FunctionalStatus;
import com.ey.enums.RequestStatus;
import com.ey.exception.DisposeNotFound;
import com.ey.exception.InspectionNotFoundException;
import com.ey.exception.UserNotFoundException;
import com.ey.mapper.InspectionMapper;
import com.ey.mapper.RefurbishProductsMapper;
import com.ey.model.Dispose;
import com.ey.model.Inspection;
import com.ey.model.Recycler;
import com.ey.model.RefurbishProducts;
import com.ey.repository.DisposeRepository;
import com.ey.repository.InspectionRepository;
import com.ey.repository.RecyclerRepository;
import com.ey.repository.RefurbishProductsRepository;
import com.ey.security.JwtUtil;

@Service
public class InspectService {

	@Autowired
	private InspectionRepository inspectRepo;
	
	@Autowired
	private DisposeRepository disposeRepo;
	@Autowired
	private RecyclerRepository recycleRepo;
	
	@Autowired
	private RefurbishProductsRepository prodRepo;
	
	@Autowired
	private JwtUtil jwtUtil;;
	
	Logger log = LoggerFactory.getLogger(InspectService.class);
	private final Integer margin=1000;
	

	public ResponseEntity<?> inspectDispose(CreateInspectRequest req,Long id,String token) {
		
		Dispose dis=disposeRepo.findById(id).orElseThrow(()->new DisposeNotFound("Invalid Dispose Id"));
		
		Recycler recycler=recycleRepo.findByEmail(jwtUtil.extractSubject(token))
				.orElseThrow(()-> new UserNotFoundException("Invalid Recycler Loged in"));

		
		if (dis.getStatus()==RequestStatus.COLLECTED) {
			if (dis.getCollector()==null) {
				log.info("this disposal is not collected yet");
				return new ResponseEntity<>("this disposal is managed by other recycler",HttpStatus.BAD_REQUEST);
			}
			
			if (dis.getCollector().getRecycler().getId()!=recycler.getId()) {
				log.info("this disposal is managed by other recycler");
				return new ResponseEntity<>("this disposal is managed by other recycler",HttpStatus.BAD_REQUEST);
			}
			int age=LocalDate.now().getYear()-req.getManufatureYear();
			
			Inspection ip=InspectionMapper.toEntity(req);
			
			if (req.getConditionGrade()==ConditionGrading.POOR|| req.getCurrentMarketValue()<req.getEstimatedRepairCost()
					||req.getStatus()==FunctionalStatus.NOT_WORKING||age>7) {
				
				ip.setDecision(DisposalStatus.RECYCLE);
			}
			else {
				ip.setDecision(DisposalStatus.REFURBISH);
			}
			
			dis.setStatus(RequestStatus.INSPECTED);
			
			ip.setDisposeRequest(dis);
			dis.setInspection(ip);
			inspectRepo.save(ip);
			
			if (ip.getDecision().equals(DisposalStatus.REFURBISH)) {
				
				RefurbishProducts prod=RefurbishProductsMapper.toEntity(ip,dis.getQuantity(),margin);
				dis.setStatus(RequestStatus.COMPLETED);
				log.info("Inspected is added to products DB "+ip.getDecision());
				prodRepo.save(prod);
				disposeRepo.save(dis);
				
			
			}
			log.info("Dipose Products Inspected Successfully and marked as "+ip.getDecision());
			return new ResponseEntity<>(InspectionMapper.toResponse(ip, "Dipose Products Inspected Successfully and marked as "+ip.getDecision()),HttpStatus.CREATED);
		
		}
		
		log.error("Dipose Products is in the stage of "+dis.getStatus()+" and cant be inspected");
		return new ResponseEntity<>("Dipose Products is in the stage of "+dis.getStatus()+" and cant be inspected",HttpStatus.BAD_REQUEST);
	}
	public ResponseEntity<?> updateinspectDispose(CreateInspectRequest req,Long id,String token) {
		
		System.out.println("Here 111...................");
		
		Inspection ip=inspectRepo.findById(id).orElseThrow(()->new InspectionNotFoundException("Invalid Inspection Id"));
		Dispose dis=disposeRepo.findById(ip.getDisposeRequest().getId()).orElseThrow(()->new DisposeNotFound("Invalid Dispose Id"));

		
		if (dis.getStatus()==RequestStatus.COMPLETED|| ip.getDecision().equals(DisposalStatus.REFURBISH)) {	
			log.error("Dispose process are already completed and cant be updated");
			return new ResponseEntity<>("Dispose process are already completed and cant be updated",HttpStatus.BAD_REQUEST);
		}
		
		Recycler recycler=recycleRepo.findByEmail(jwtUtil.extractSubject(token))
				.orElseThrow(()-> new UserNotFoundException("Invalid Recycler Loged in"));
		
		if (ip.getDisposeRequest().getCollector().getRecycler().getId()!=recycler.getId()) {
			log.error("you are not access to update other recyclers dispose inspection");
			return new ResponseEntity<>("you are not access to update other recyclers dispose inspection",HttpStatus.BAD_REQUEST);
		}
		ip.setBrand(req.getBrand());
		ip.setConditionGrade(req.getConditionGrade());
		ip.setCurrentMarketValue(req.getCurrentMarketValue());
		ip.setEstimatedRepairCost(req.getEstimatedRepairCost());
		ip.setInspectedAt(LocalDate.now());
		ip.setManufatureYear(req.getManufatureYear());
		ip.setModelName(req.getModelName());
		ip.setStatus(req.getStatus());
		ip.setType(req.getType());
		
		int age=LocalDate.now().getYear()-req.getManufatureYear();
		

		
		if (req.getConditionGrade()==ConditionGrading.POOR|| req.getCurrentMarketValue()<req.getEstimatedRepairCost()
				||req.getStatus()==FunctionalStatus.NOT_WORKING||age>7) {
			
			ip.setDecision(DisposalStatus.RECYCLE);
		}
		else {
			ip.setDecision(DisposalStatus.REFURBISH);
		}
		
		dis.setStatus(RequestStatus.INSPECTED);
		
		ip.setDisposeRequest(dis);
		dis.setInspection(ip);
		inspectRepo.save(ip);
		
		if (ip.getDecision().equals(DisposalStatus.REFURBISH)) {
			
			log.info("dispose added to products table");
			RefurbishProducts prod=RefurbishProductsMapper.toEntity(ip,dis.getQuantity(),margin);
			dis.setStatus(RequestStatus.COMPLETED);
			prodRepo.save(prod);
			disposeRepo.save(dis);
			
			
		}
		log.info("inspection Products updated Successfully and marked as "+ip.getDecision());

		return new ResponseEntity<>(InspectionMapper.toResponse(ip, "Insption Products updated Successfully and marked as "+ip.getDecision()),HttpStatus.ACCEPTED);
	}
	public ResponseEntity<?> deleteinspectDispose(Long id,String token) {
		
		Inspection ip=inspectRepo.findById(id).orElseThrow(()->new InspectionNotFoundException("Invalid Inspection Id"));
		Dispose dis=disposeRepo.findById(ip.getDisposeRequest().getId()).orElseThrow(()->new DisposeNotFound("Invalid Dispose Id"));

		
		if (dis.getStatus()==RequestStatus.COMPLETED|| ip.getDecision().equals(DisposalStatus.REFURBISH))  {
			log.error("Dispose process are already completed and cant be deleted");
			return new ResponseEntity<>("Dispose process are already completed and cant be deleted",HttpStatus.BAD_REQUEST);
		}
		Recycler recycler=recycleRepo.findByEmail(jwtUtil.extractSubject(token))
				.orElseThrow(()-> new UserNotFoundException("Invalid Recycler Loged in"));
		
		if (dis.getCollector().getRecycler().getId()!=recycler.getId()) {
			log.error("you are not access to delete other recyclers dispose inspection");
			return new ResponseEntity<>("you are not access to delete other recyclers dispose inspection",HttpStatus.BAD_REQUEST);
		}
		
		dis.setInspection(null);
		dis.setStatus(RequestStatus.COLLECTED);
		disposeRepo.save(dis);
	
		inspectRepo.deleteById(id);
		
		log.info("Inspection Products deleted Successfully");
		return new ResponseEntity<>("Inspection deleted Successfully",HttpStatus.ACCEPTED);
	}
	
	public ResponseEntity<?> getAllInpectedDispose(String token) {
		
		Recycler recycler=recycleRepo.findByEmail(jwtUtil.extractSubject(token))
									.orElseThrow(()-> new UserNotFoundException("Invaild Recycler Id"));
		
		
		
		List<RegisterInspectResponse> ip=inspectRepo.findAllByRecyclerId(recycler.getId())
												.stream()
												.map(s-> InspectionMapper.toResponse(s, "fetch successfull"))
												.toList();
		
		
		log.info("All inspection fetch is successfull");
		return new ResponseEntity<>(ip,HttpStatus.OK);
	}
	
	public ResponseEntity<?> getInspectById(Long id,String token) {
		
		Recycler recycler=recycleRepo.findByEmail(jwtUtil.extractSubject(token))
				.orElseThrow(()-> new UserNotFoundException("Invaild Recycler Id"));
		
		Inspection ip1=inspectRepo.findById(id).orElseThrow(()-> new InspectionNotFoundException("Invalid inspect Id"));
		
		Inspection ip=inspectRepo.findByIdAndDisposeRequestCollectorRecyclerId(id, recycler.getId())
										.orElse(null);
		if (ip==null) {
			log.error("this inspection is done by other recycler");
			return new ResponseEntity<>("this inspection is done by other recycler",HttpStatus.BAD_REQUEST);
		}
		
		log.info("inspection fetch by id is successfull");
		return new ResponseEntity<>(InspectionMapper.toResponse(ip, "Fetch Successfull"),HttpStatus.OK);
	}
	
	
	
	
	public ResponseEntity<?> getAllInspectsByAdmin(){
		
		
		List<InspectResponse> inspects=inspectRepo.findAll()
													.stream()
													.map(s->InspectionMapper.toResponse(s))
													.toList();
														
		log.info("All inspection fetch by admin is successfull");
		return new ResponseEntity<>(inspects,HttpStatus.OK);
	}
	
	public ResponseEntity<?> getInspectsByIdByAdmin(Long id){
		
		
		Inspection inspect=inspectRepo.findById(id)
				.orElseThrow(()-> new InspectionNotFoundException("Invalid Inspect Id"));
		
		
		log.info("inspection fetch by admin is successfull");
		return new ResponseEntity<>(InspectionMapper.toResponse(inspect),HttpStatus.OK);
	}
	
}
