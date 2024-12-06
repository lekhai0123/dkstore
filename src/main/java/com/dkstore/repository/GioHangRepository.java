package com.dkstore.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.dkstore.models.GioHang;
import com.dkstore.models.User;

public interface GioHangRepository extends JpaRepository<GioHang, Integer>{
	@Query("Select h FROM GioHang h JOIN h.user u WHERE u.userName LIKE %?1%")
	List<GioHang> search(String keyword);	
	List<GioHang> findByUser(User user);
}
