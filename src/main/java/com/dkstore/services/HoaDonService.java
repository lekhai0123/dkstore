package com.dkstore.services;

import java.util.List;

import org.springframework.data.domain.Page;

import com.dkstore.models.HoaDon;

public interface HoaDonService {
	List<HoaDon> getAll();
	Boolean create(HoaDon hoadon);
	HoaDon findById(Integer id);
	Boolean update(HoaDon hoadon);
	Boolean delete(Integer id);
	List<HoaDon> search(String keyword);
	Page<HoaDon> getAll(Integer pageNo);
	Page<HoaDon> search(String keyword, Integer pageNo);
}
