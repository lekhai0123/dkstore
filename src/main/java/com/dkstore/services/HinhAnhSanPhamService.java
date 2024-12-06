package com.dkstore.services;

import java.util.List;

import com.dkstore.models.HinhAnhSanPham;

public interface HinhAnhSanPhamService {
	List<HinhAnhSanPham> findByProductId(Integer productId);
}
