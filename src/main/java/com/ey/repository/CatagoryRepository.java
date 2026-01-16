package com.ey.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ey.model.Catagory;

@Repository
public interface CatagoryRepository extends JpaRepository<Catagory,Long>{

}
