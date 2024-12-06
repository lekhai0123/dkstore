package com.dkstore.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.dkstore.models.ChiTietGioHang;
import com.dkstore.models.ChiTietHoaDon;
import com.dkstore.models.GioHang;
import com.dkstore.repository.ChiTietGioHangRepository;
import com.dkstore.repository.GioHangRepository;

@Service
public class ChiTietGioHangServiceImple implements ChiTietGioHangService{
	@Autowired
	private ChiTietGioHangRepository chiTietGioHangRepository;
	@Autowired
	private GioHangRepository gioHangRepository;
	@Override
	public List<ChiTietGioHang> getAll() {
		// TODO Auto-generated method stub
		return this.chiTietGioHangRepository.findAll();
	}

	@Override
	public Boolean create(ChiTietGioHang chiTietGioHang) {
	    try {
	        GioHang gioHang = chiTietGioHang.getGiohang(); // Lấy giỏ hàng từ chi tiết
	        gioHang.getChiTietGioHang().add(chiTietGioHang);  // Thêm chi tiết vào danh sách giỏ hàng
	        this.gioHangRepository.save(gioHang);  // Lưu lại giỏ hàng sau khi thêm chi tiết
	        return true;
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    return false;
	}

	@Override
	public ChiTietGioHang findById(Integer id) {
		// TODO Auto-generated method stub
		return this.chiTietGioHangRepository.findById(id).get();
	}

	@Override
	public Boolean update(ChiTietGioHang chiTietGioHang) {
		// TODO Auto-generated method stub
		try {
		    ChiTietGioHang existingDetail = chiTietGioHangRepository.findById(chiTietGioHang.getId()).orElse(null);
		    if (existingDetail != null) {
		        existingDetail.setProduct(chiTietGioHang.getProduct());
		        existingDetail.setSoLuong(chiTietGioHang.getSoLuong());
		        existingDetail.setGia(chiTietGioHang.getGia());
		        existingDetail.setTonggiasanpham(chiTietGioHang.getTonggiasanpham());
		        chiTietGioHangRepository.save(existingDetail); // Lưu bản ghi đã cập nhật
		        return true;
		    }
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public Boolean delete(Integer id) {
		// TODO Auto-generated method stub
		try {
			this.chiTietGioHangRepository.delete(findById(id));
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public Page<ChiTietGioHang> getAll(Integer pageNo) {
		// TODO Auto-generated method stub
		return null;
	}

}
