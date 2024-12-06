package com.dkstore.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.dkstore.models.UserRole;

public interface UserRoleRepository extends JpaRepository<UserRole, Integer>{
	UserRole findByUserId(Integer id);
}
