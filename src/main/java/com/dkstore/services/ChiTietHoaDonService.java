package com.dkstore.services;

import java.util.List;

import org.springframework.data.domain.Page;

import com.dkstore.models.ChiTietHoaDon;

public interface ChiTietHoaDonService {
	List<ChiTietHoaDon> getAll();
	Boolean create(ChiTietHoaDon chiTietHoaDon);
	ChiTietHoaDon findById(Integer id);
	Boolean update(ChiTietHoaDon chiTietHoaDon);
	Boolean delete(Integer id);
	Page<ChiTietHoaDon> getAll(Integer pageNo);
}
