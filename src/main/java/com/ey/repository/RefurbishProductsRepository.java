package com.ey.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ey.model.RefurbishProducts;

@Repository
public interface RefurbishProductsRepository extends JpaRepository<RefurbishProducts,Long>{

}
