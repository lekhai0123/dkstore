package com.dkstore.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.dkstore.models.Product;
import com.dkstore.models.SanPhamTonKho;

public interface SanPhamTonKhoRepository extends JpaRepository<SanPhamTonKho, Integer>{
	 @Query("SELECT s FROM SanPhamTonKho s WHERE s.size = :size AND s.product.id = :productId")
	 Optional<SanPhamTonKho> findBySizeAndProduct(@Param("size") Integer size, @Param("productId") Integer productId);
}
