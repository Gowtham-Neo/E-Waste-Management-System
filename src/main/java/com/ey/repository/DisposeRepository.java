package com.ey.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ey.model.Dispose;

@Repository
public interface DisposeRepository extends JpaRepository<Dispose,Long>{

}
