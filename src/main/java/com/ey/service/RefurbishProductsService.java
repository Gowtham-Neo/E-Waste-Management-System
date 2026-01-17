package com.ey.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.ey.dto.response.RefurbishProductsResponse;
import com.ey.mapper.RefurbishProductsMapper;
import com.ey.repository.RefurbishProductsRepository;

@Service
public class RefurbishProductsService {

	@Autowired
	private RefurbishProductsRepository prodRepo;

	public ResponseEntity<?> getAllRefubishProducts() {

		List<RefurbishProductsResponse> products=prodRepo.findAll()
													.stream()
													.map(s->RefurbishProductsMapper.toResponse(s, "Ready for sale"))
													.toList();
										
		if (products.size()==0) {
			return new ResponseEntity<>("NO products available to list",HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<>(products,HttpStatus.OK);
	}
}
