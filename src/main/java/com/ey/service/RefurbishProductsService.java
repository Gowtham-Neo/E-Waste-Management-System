package com.ey.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.ey.dto.request.UpdateRefurbishProducts;
import com.ey.dto.response.RefurbishProductsResponse;
import com.ey.exception.ProductNotFoundException;
import com.ey.mapper.RefurbishProductsMapper;
import com.ey.model.RefurbishProducts;
import com.ey.repository.RefurbishProductsRepository;

@Service
public class RefurbishProductsService {

	@Autowired
	private RefurbishProductsRepository prodRepo;
	
	Logger log = LoggerFactory.getLogger(RefurbishProductsService.class);


	public ResponseEntity<?> getAllRefubishProducts() {

		List<RefurbishProductsResponse> products=prodRepo.findAll()
													.stream()
													.map(s->RefurbishProductsMapper.toResponse(s, "Ready for sale"))
													.toList();
										
		if (products.size()==0) {
			return new ResponseEntity<>("NO products available to list",HttpStatus.NO_CONTENT);
		}
		log.info("all products fetch is successfull");
		return new ResponseEntity<>(products,HttpStatus.OK);
	}
	public ResponseEntity<?> getproductsById(Long id) {
		
		RefurbishProducts product=prodRepo.findById(id)
											.orElseThrow(()-> new ProductNotFoundException("INvalid Product Id"));
		
		log.info("Products fetch by id is successfull");
		return new ResponseEntity<>(RefurbishProductsMapper.toResponse(product, "Ready for Sale"),HttpStatus.OK);
	}
	
	public ResponseEntity<?> updateproductsById(UpdateRefurbishProducts req,Long id) {
		
		RefurbishProducts product=prodRepo.findById(id)
				.orElseThrow(()-> new ProductNotFoundException("INvalid Product Id"));
		product.setQuantity(req.getQuantity()+product.getQuantity());
		
		prodRepo.save(product);
		
		log.info("Quantity Updated Successfully");
		return new ResponseEntity<>(RefurbishProductsMapper.toResponse(product, "Quantity Updated Successfully"),HttpStatus.ACCEPTED);
	}
}
