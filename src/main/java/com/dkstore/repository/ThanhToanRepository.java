package com.dkstore.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.dkstore.models.ThanhToan;

public interface ThanhToanRepository extends JpaRepository<ThanhToan, Integer>{
	ThanhToan findByGiohangId(Integer giohangId);
	
}
