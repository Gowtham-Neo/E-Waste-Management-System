package com.ey.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ey.model.Inspection;

@Repository
public interface InspectionRepository extends JpaRepository<Inspection,Long>{

}
