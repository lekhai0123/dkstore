package com.dkstore.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dkstore.models.HinhAnhSanPham;

public interface HinhAnhSanPhamRepository extends JpaRepository<HinhAnhSanPham, Integer>{
	HinhAnhSanPham findByProductIdAndIsMainTrue(Integer productId);
	List<HinhAnhSanPham> findByProductId(Integer productId);
}
