package com.dkstore.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.dkstore.models.ThanhToan;
import com.dkstore.repository.ThanhToanRepository;

@Service
public class ThanhToanServiceImple implements ThanhToanService{
	@Autowired
	private ThanhToanRepository thanhToanRepository;

	@Override
	public List<ThanhToan> getAll() {
		// TODO Auto-generated method stub
		return this.thanhToanRepository.findAll();
	}

	@Override
	public Boolean create(ThanhToan thanhtoan) {
		// TODO Auto-generated method stub
		try {
			this.thanhToanRepository.save(thanhtoan);
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public ThanhToan findById(Integer id) {
		// TODO Auto-generated method stub
		 return this.thanhToanRepository.findById(id).get();
	}

	@Override
	public Boolean update(ThanhToan thanhtoan) {
	    try {
	        // Kiểm tra xem bản ghi ThanhToan có tồn tại với giohang.id không
	        ThanhToan existingThanhToan = this.thanhToanRepository.findByGiohangId(thanhtoan.getGiohang().getId());
	        
	        if (existingThanhToan != null) {
	            // Nếu tồn tại, cập nhật các thuộc tính cần thiết
	            existingThanhToan.setPhuongthuc(thanhtoan.getPhuongthuc());
	            existingThanhToan.setTrangthai(thanhtoan.getTrangthai());
	            
	            // Lưu lại bản ghi sau khi cập nhật
	            this.thanhToanRepository.save(existingThanhToan);
	        } else {
	            // Nếu không tồn tại, tạo mới bản ghi
	            this.thanhToanRepository.save(thanhtoan);
	        }
	        
	        return true;
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    return false;
	}


	@Override
	public Boolean delete(Integer id) {
		// TODO Auto-generated method stub
		try {
			this.thanhToanRepository.delete(findById(id));
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public Page<ThanhToan> getAll(Integer pageNo) {
		// TODO Auto-generated method stub
		return null;
	}

}
