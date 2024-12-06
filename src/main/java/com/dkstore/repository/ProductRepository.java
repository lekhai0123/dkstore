package com.dkstore.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.dkstore.models.Brand;
import com.dkstore.models.Product;

public interface ProductRepository extends JpaRepository<Product, Integer>{
	List<Product> findTop6ByOrderByIdDesc();
	List<Product> findTop4ByOrderByIdDesc();
	@Query("Select c FROM Product c WHERE c.name LIKE %?1%")
	List<Product> search(String keyword);
	@Query("SELECT p FROM Product p WHERE p.brand.name IN :brandNames")
    List<Product> findByBrand_Names(@Param("brandNames") List<String> brandNames);
}
