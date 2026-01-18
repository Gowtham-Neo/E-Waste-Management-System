package com.ey.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ey.model.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order,Long>{
	
	List<Order> findByBuyerId(Long id);
	Optional<Order> findByIdAndBuyerId(Long id,Long userId);
}
