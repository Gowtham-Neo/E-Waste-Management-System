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
import com.ey.mapper.InspectionMapper;
import com.ey.mapper.RefurbishProductsMapper;
import com.ey.model.Dispose;
import com.ey.model.Inspection;
import com.ey.model.RefurbishProducts;
import com.ey.repository.DisposeRepository;
import com.ey.repository.InspectionRepository;
import com.ey.repository.RefurbishProductsRepository;

@Service
public class InspectService {

	@Autowired
	private InspectionRepository inspectRepo;
	
	@Autowired
	private DisposeRepository disposeRepo;
	
	@Autowired
	private RefurbishProductsRepository prodRepo;
	
	private final Integer margin=1000;
	

	public ResponseEntity<?> inspectDispose(CreateInspectRequest req,Long id) {
		
		Dispose dis=disposeRepo.findById(id).orElseThrow(()->new DisposeNotFound("Invalid Dispose Id"));
		
		
		if (dis.getStatus()==RequestStatus.CANCELLED) {
			return new ResponseEntity<>("Dispose products are cancelled by the delivery partner",HttpStatus.BAD_REQUEST);
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
			dis.setStatus(RequestStatus.INSPECTED);
			prodRepo.save(prod);
			disposeRepo.save(dis);
			
		
		}
		
		return new ResponseEntity<>(InspectionMapper.toResponse(ip, "Dipose Products Inspected Successfully and marked as "+ip.getDecision()),HttpStatus.ACCEPTED);
	}
	public ResponseEntity<?> getAllInpectedDispose() {
		
	
		
		List<InspectResponse> ip=inspectRepo.findAll()
											.stream()
											.map(s-> InspectionMapper.toResponse(s, null))
											.toList();
		
		
		
		return new ResponseEntity<>(ip,HttpStatus.OK);
	}
	
	
}
