package com.ey.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.ey.dto.request.CreateInspectRequest;
import com.ey.dto.response.InspectResponse;
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
	
	private final Integer margin=1000;
	

	public ResponseEntity<?> inspectDispose(CreateInspectRequest req,Long id,String token) {
		
		Dispose dis=disposeRepo.findById(id).orElseThrow(()->new DisposeNotFound("Invalid Dispose Id"));
		
		
		if (dis.getStatus()==RequestStatus.CANCELLED) {
			return new ResponseEntity<>("Dispose products are cancelled by the delivery partner",HttpStatus.BAD_REQUEST);
		}
		Recycler recycler=recycleRepo.findByEmail(jwtUtil.extractSubject(token))
										.orElseThrow(()-> new UserNotFoundException("Invalid Recycler Loged in"));
		
		if (dis.getCollector().getRecycler().getId()!=recycler.getId()) {
			return new ResponseEntity<>("you are not access to inspect other recyclers dispose inspection",HttpStatus.BAD_REQUEST);
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
			prodRepo.save(prod);
			disposeRepo.save(dis);
			
		
		}
		
		return new ResponseEntity<>(InspectionMapper.toResponse(ip, "Dipose Products Inspected Successfully and marked as "+ip.getDecision()),HttpStatus.ACCEPTED);
	}
	public ResponseEntity<?> updateinspectDispose(CreateInspectRequest req,Long id,String token) {
		
		System.out.println("Here 111...................");
		
		Inspection ip=inspectRepo.findById(id).orElseThrow(()->new InspectionNotFoundException("Invalid Inspection Id"));
		Dispose dis=disposeRepo.findById(ip.getDisposeRequest().getId()).orElseThrow(()->new DisposeNotFound("Invalid Dispose Id"));

		
		if (dis.getStatus()==RequestStatus.COMPLETED|| ip.getDecision().equals(DisposalStatus.REFURBISH)) {
			return new ResponseEntity<>("Dispose process are already completed and cant be updated",HttpStatus.BAD_REQUEST);
		}
		
		Recycler recycler=recycleRepo.findByEmail(jwtUtil.extractSubject(token))
				.orElseThrow(()-> new UserNotFoundException("Invalid Recycler Loged in"));
		
		if (ip.getDisposeRequest().getCollector().getRecycler().getId()!=recycler.getId()) {
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
			
			RefurbishProducts prod=RefurbishProductsMapper.toEntity(ip,dis.getQuantity(),margin);
			dis.setStatus(RequestStatus.COMPLETED);
			prodRepo.save(prod);
			disposeRepo.save(dis);
			
			
		}
		
		return new ResponseEntity<>(InspectionMapper.toResponse(ip, "Dipose Products updated Successfully and marked as "+ip.getDecision()),HttpStatus.ACCEPTED);
	}
	public ResponseEntity<?> deleteinspectDispose(Long id,String token) {
		
		Inspection ip=inspectRepo.findById(id).orElseThrow(()->new InspectionNotFoundException("Invalid Inspection Id"));
		Dispose dis=disposeRepo.findById(ip.getDisposeRequest().getId()).orElseThrow(()->new DisposeNotFound("Invalid Dispose Id"));

		
		if (dis.getStatus()==RequestStatus.COMPLETED|| ip.getDecision().equals(DisposalStatus.REFURBISH))  {
			return new ResponseEntity<>("Dispose process are already completed and cant be deleted",HttpStatus.BAD_REQUEST);
		}
		Recycler recycler=recycleRepo.findByEmail(jwtUtil.extractSubject(token))
				.orElseThrow(()-> new UserNotFoundException("Invalid Recycler Loged in"));
		
		if (dis.getCollector().getRecycler().getId()!=recycler.getId()) {
			return new ResponseEntity<>("you are not access to delete other recyclers dispose inspection",HttpStatus.BAD_REQUEST);
		}
		
		dis.setInspection(null);
		disposeRepo.save(dis);
	
		inspectRepo.deleteById(id);
		return new ResponseEntity<>("Dipose Products deleted Successfully",HttpStatus.ACCEPTED);
	}
	
	public ResponseEntity<?> getAllInpectedDispose(String token) {
		
		Recycler recycler=recycleRepo.findByEmail(jwtUtil.extractSubject(token))
									.orElseThrow(()-> new UserNotFoundException("Invaild Recycler Id"));
		
		
		
		List<InspectResponse> ip=inspectRepo.findAllByRecyclerId(recycler.getId())
												.stream()
												.map(s-> InspectionMapper.toResponse(s, "fetch successfull"))
												.toList();
		
		
		
		return new ResponseEntity<>(ip,HttpStatus.OK);
	}
	
	public ResponseEntity<?> getInspectById(Long id,String token) {
		
		Recycler recycler=recycleRepo.findByEmail(jwtUtil.extractSubject(token))
				.orElseThrow(()-> new UserNotFoundException("Invaild Recycler Id"));
		
		Inspection ip1=inspectRepo.findById(id).orElseThrow(()-> new InspectionNotFoundException("Invalid inspect Id"));
		
		Inspection ip=inspectRepo.findByIdAndDisposeRequestCollectorRecyclerId(id, recycler.getId())
										.orElse(null);
		if (ip==null) {
			return new ResponseEntity<>("this inspection is done by other recycler",HttpStatus.BAD_REQUEST);
		}
		
		
		return new ResponseEntity<>(InspectionMapper.toResponse(ip, "Fetch Successfull"),HttpStatus.OK);
	}
	
	
}
