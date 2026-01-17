package com.ey.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ey.model.RecycledMaterials;

@Repository
public interface RecycledMaterialsRepository extends JpaRepository<RecycledMaterials,Long>{

}
