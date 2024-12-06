package com.dkstore.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.dkstore.models.Brand;

public interface BrandRepository extends JpaRepository<Brand, Integer>{
	@Query("Select c FROM Brand c WHERE c.name LIKE %?1%")
	List<Brand> search(String keyword);	
}
