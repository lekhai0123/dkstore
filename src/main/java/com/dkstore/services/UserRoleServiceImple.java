package com.dkstore.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dkstore.models.ThanhToan;
import com.dkstore.models.UserRole;
import com.dkstore.repository.UserRoleRepository;

@Service
public class UserRoleServiceImple implements UserRoleService{
	@Autowired
	private UserRoleRepository userRoleRepository;

	@Override
	public Boolean create(UserRole userRole) {
		// TODO Auto-generated method stub
		try {
			this.userRoleRepository.save(userRole);
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public UserRole findById(Integer id) {
	    return this.userRoleRepository.findById(id).orElse(null);  // Trả về null nếu không tìm thấy
	}

	@Override
	public Boolean update(UserRole userRole) {
		// TODO Auto-generated method stub
		try {
	        // Kiểm tra xem bản ghi ThanhToan có tồn tại với giohang.id không
	        UserRole existingUser = this.userRoleRepository.findByUserId(userRole.getUser().getId());
	        
	        if (existingUser != null) {
	            // Nếu tồn tại, cập nhật các thuộc tính cần thiết
	            existingUser.setUser(userRole.getUser());
	            existingUser.setRole(userRole.getRole());
	            
	            // Lưu lại bản ghi sau khi cập nhật
	            this.userRoleRepository.save(existingUser);
	        } else {
	            // Nếu không tồn tại, tạo mới bản ghi
	            this.userRoleRepository.save(userRole);
	        }
	        
	        return true;
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    return false;
	}
}
