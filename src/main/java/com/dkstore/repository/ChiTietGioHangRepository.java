package com.dkstore.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.dkstore.models.ChiTietGioHang;
import com.dkstore.models.Product;

public interface ChiTietGioHangRepository extends JpaRepository<ChiTietGioHang, Integer>{
	@Query("SELECT SUM(c.soLuong) FROM ChiTietGioHang c WHERE c.size = :size AND c.product.id = :productId")
    Integer getTotalSoLuongBySizeAndProduct(@Param("size") Integer size, @Param("productId") Integer productId);
}
