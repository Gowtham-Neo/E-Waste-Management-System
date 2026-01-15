package com.ey.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ey.model.Collector;
import com.ey.model.User;

@Repository
public interface CollectorRepository extends JpaRepository<Collector,Long>{

	Optional<Collector> findByEmail(String email);
}
