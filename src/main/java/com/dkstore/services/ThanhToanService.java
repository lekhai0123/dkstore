package com.dkstore.services;

import java.util.List;

import org.springframework.data.domain.Page;

import com.dkstore.models.ThanhToan;

public interface ThanhToanService {
	List<ThanhToan> getAll();
	Boolean create(ThanhToan thanhtoan);
	ThanhToan findById(Integer id);
	Boolean update(ThanhToan thanhtoan);
	Boolean delete(Integer id);
	Page<ThanhToan> getAll(Integer pageNo);
}
