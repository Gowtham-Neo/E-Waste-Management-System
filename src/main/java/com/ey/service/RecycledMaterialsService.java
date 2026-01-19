package com.ey.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.ey.dto.request.AddMaterialsRequest;
import com.ey.dto.request.UpdateMaterialsRequest;
import com.ey.dto.response.MaterialsResponse;
import com.ey.enums.DisposalStatus;
import com.ey.enums.RequestStatus;
import com.ey.exception.InspectionNotFoundException;
import com.ey.exception.ProductNotFoundException;
import com.ey.mapper.RecycledMaterialsMapper;
import com.ey.model.Dispose;
import com.ey.model.Inspection;
import com.ey.model.RecycledMaterials;
import com.ey.repository.DisposeRepository;
import com.ey.repository.InspectionRepository;
import com.ey.repository.RecycledMaterialsRepository;

@Service
public class RecycledMaterialsService {

	@Autowired
	private RecycledMaterialsRepository materialRepo;
	@Autowired
	private InspectionRepository inspectRepo;
	@Autowired
	private DisposeRepository disRepo;

	Logger log = LoggerFactory.getLogger(RecycledMaterialsService.class);
	public ResponseEntity<?> updateMaterial(UpdateMaterialsRequest req,Long id) {
		
		RecycledMaterials rm=materialRepo.findById(id).orElseThrow(()-> new ProductNotFoundException("INvalid material Id"));
		rm.setEstimatedValue(req.getEstimatedValue()+rm.getEstimatedValue());
		rm.setWeightInKg(req.getWeightInKg()+rm.getWeightInKg());
		
		materialRepo.save(rm);
		log.info("Materials Update successfull");
		return new ResponseEntity<>(RecycledMaterialsMapper.toResponse(rm),HttpStatus.ACCEPTED);
	}
	public ResponseEntity<?> addMaterials(AddMaterialsRequest req,Long id) {
		Inspection ip=inspectRepo.findById(id).orElseThrow(()-> new InspectionNotFoundException("Invalid Inpection Id"));
		if (ip.getDecision().equals(DisposalStatus.REFURBISH)) {
			log.error("invalid inseption id,The dipose products are refurblished and cannot be recycled");
			return new ResponseEntity<>("invalid inseption id,The dipose products are refurblished and cannot be recycled",HttpStatus.BAD_REQUEST);
		}
		Dispose d=ip.getDisposeRequest();
		d.setStatus(RequestStatus.COMPLETED);
		disRepo.save(d);
		
		RecycledMaterials rm=RecycledMaterialsMapper.toEntity(req,ip);
				
		materialRepo.save(rm);
		
		log.info("Materials added successfull");

		return new ResponseEntity<>(RecycledMaterialsMapper.toResponse(rm),HttpStatus.CREATED);
	}
	
	
	public ResponseEntity<?> getAllMaterials() {
		List<MaterialsResponse> res=materialRepo.findAll()
												.stream()
												.map(s-> RecycledMaterialsMapper.toResponse(s))
												.toList();
	
		log.info("all Materials fetch successfull");

		return new ResponseEntity<>(res,HttpStatus.OK);
	}
	
	public ResponseEntity<?> getMaterialById(Long id) {
		RecycledMaterials materials=materialRepo.findById(id)
												.orElseThrow(()-> new ProductNotFoundException("Invalid Materials Id"));
		
		log.info("Materials fetch by id successfull");

		return new ResponseEntity<>(RecycledMaterialsMapper.toResponse(materials),HttpStatus.OK);
	}
}
