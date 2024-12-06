package com.dkstore.services;

import java.util.List;

import org.springframework.data.domain.Page;

import com.dkstore.models.Brand;

public interface BrandService {
	List<Brand> getAll();
	Boolean create(Brand category);
	Brand findById(Integer id);
	Boolean update(Brand category);
	Boolean delete(Integer id);
	List<Brand> search(String keyword);
	Page<Brand> getAll(Integer pageNo);
	Page<Brand> search(String keyword, Integer pageNo);
}
