package com.ey.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ey.model.Recycler;
import com.ey.model.User;

@Repository
public interface RecyclerRepository extends JpaRepository<Recycler,Long>{
	Optional<Recycler> findByEmail(String email);
	Optional<Recycler> findByLicenceNumber(String licenceNumber);
	Optional<Recycler> findByMobileNumber(String mobileNumber);
	
}
