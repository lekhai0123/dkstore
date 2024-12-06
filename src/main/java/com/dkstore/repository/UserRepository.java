package com.dkstore.repository;


import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.dkstore.models.User;


public interface UserRepository extends JpaRepository<User, Integer>{
	User findByUserName(String userName);
	Boolean existsByUserName(String userName);
	Boolean existsByEmail(String email);
	Optional<User> findByEmail(String email);
	@Query("Select c FROM User c WHERE c.userName LIKE %?1%")
	List<User> search(String keyword);	
}
