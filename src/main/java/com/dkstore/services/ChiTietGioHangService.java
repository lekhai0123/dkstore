package com.dkstore.services;

import java.util.List;

import org.springframework.data.domain.Page;

import com.dkstore.models.ChiTietGioHang;

public interface ChiTietGioHangService {
	List<ChiTietGioHang> getAll();
	Boolean create(ChiTietGioHang chiTietGioHang);
	ChiTietGioHang findById(Integer id);
	Boolean update(ChiTietGioHang chiTietGioHang);
	Boolean delete(Integer id);
	Page<ChiTietGioHang> getAll(Integer pageNo);
}
