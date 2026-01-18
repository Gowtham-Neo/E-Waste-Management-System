package com.ey.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ey.model.Inspection;

@Repository
public interface InspectionRepository extends JpaRepository<Inspection,Long>{

	@Query("""
	        SELECT i
	        FROM Inspection i
	        JOIN i.disposeRequest dr
	        JOIN dr.collector c
	        JOIN c.recycler r
	        WHERE r.id = :recyclerId
	    """)
	 List<Inspection> findAllByRecyclerId(Long recyclerId);
	
	Optional<Inspection>
	findByIdAndDisposeRequestCollectorRecyclerId(
	        Long inspectionId,
	        Long recyclerId
	);
}
