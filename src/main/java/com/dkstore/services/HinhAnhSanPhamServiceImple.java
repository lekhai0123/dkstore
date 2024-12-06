package com.dkstore.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dkstore.models.HinhAnhSanPham;
import com.dkstore.repository.HinhAnhSanPhamRepository;

@Service
public class HinhAnhSanPhamServiceImple implements HinhAnhSanPhamService{
	@Autowired
	private HinhAnhSanPhamRepository hinhAnhSanPhamRepository;
	@Override
	public List<HinhAnhSanPham> findByProductId(Integer productId) {
		// TODO Auto-generated method stub
		return hinhAnhSanPhamRepository.findByProductId(productId);
	}

}
