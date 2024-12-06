package com.dkstore.services;

import com.dkstore.models.UserRole;

public interface UserRoleService {
	Boolean create(UserRole userRole);
	UserRole findById(Integer id);
	Boolean update(UserRole userRole);
}
