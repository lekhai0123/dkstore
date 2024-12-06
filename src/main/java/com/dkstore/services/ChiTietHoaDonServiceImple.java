package com.dkstore.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.dkstore.models.ChiTietHoaDon;
import com.dkstore.models.Product;
import com.dkstore.repository.ChiTietHoaDonRepository;

@Service
public class ChiTietHoaDonServiceImple implements ChiTietHoaDonService{
	@Autowired
	private ChiTietHoaDonRepository chiTietHoaDonRepository;
	@Override
	public List<ChiTietHoaDon> getAll() {
		// TODO Auto-generated method stub
		return this.chiTietHoaDonRepository.findAll();
	}

	@Override
	public Boolean create(ChiTietHoaDon chiTietHoaDon) {
		// TODO Auto-generated method stub
		try {
			this.chiTietHoaDonRepository.save(chiTietHoaDon);
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public ChiTietHoaDon findById(Integer id) {
		// TODO Auto-generated method stub
		return this.chiTietHoaDonRepository.findById(id).get();
	}

	@Override
	public Boolean update(ChiTietHoaDon chiTietHoaDon) {
		// TODO Auto-generated method stub
		try {
		    ChiTietHoaDon existingDetail = chiTietHoaDonRepository.findById(chiTietHoaDon.getId()).orElse(null);
		    if (existingDetail != null) {
		        existingDetail.setProduct(chiTietHoaDon.getProduct());
		        existingDetail.setSoluong(chiTietHoaDon.getSoluong());
		        existingDetail.setGia(chiTietHoaDon.getGia());
		        existingDetail.setTonggiasanpham(chiTietHoaDon.getTonggiasanpham());
		        chiTietHoaDonRepository.save(existingDetail); // Lưu bản ghi đã cập nhật
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
			this.chiTietHoaDonRepository.delete(findById(id));
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public Page<ChiTietHoaDon> getAll(Integer pageNo) {
		// TODO Auto-generated method stub
		return null;
	}

}
