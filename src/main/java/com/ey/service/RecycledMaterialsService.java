package com.ey.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.ey.dto.request.AddMaterialsRequest;
import com.ey.enums.DisposalStatus;
import com.ey.enums.RequestStatus;
import com.ey.exception.InspectionNotFoundException;
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

	public ResponseEntity<?> addMaterials(AddMaterialsRequest req,Long id) {
		Inspection ip=inspectRepo.findById(id).orElseThrow(()-> new InspectionNotFoundException("Invalid Inpection Id"));
		if (ip.getDecision().equals(DisposalStatus.REFURBISH)) {
			return new ResponseEntity<>("invalid inseption id,The dipose products are refurblished and cannot be recycled",HttpStatus.BAD_REQUEST);
		}
		Dispose d=ip.getDisposeRequest();
		d.setStatus(RequestStatus.COMPLETED);
		disRepo.save(d);
		
		RecycledMaterials rm=RecycledMaterialsMapper.toEntity(req,ip);
		
										
		materialRepo.save(rm);
		return new ResponseEntity<>(RecycledMaterialsMapper.toResponse(rm),HttpStatus.OK);
	}
}
