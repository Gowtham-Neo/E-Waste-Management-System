package com.ey.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ey.model.Collector;

@Repository
public interface CollectorRepository extends JpaRepository<Collector,Long>{

	Optional<Collector> findByEmail(String email);
	Optional<Collector> findByVehicleNumber(String vehicleNumber);
	Optional<Collector> findByMobileNumber(String mobileNumber);
	
	List<Collector> findByRecyclerId(Long id);
}
