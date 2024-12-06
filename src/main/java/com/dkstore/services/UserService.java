package com.dkstore.services;



import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;

import com.dkstore.models.User;

public interface UserService {
	User findByUserName(String userName);
	Optional<User> findById(Integer id);
	List<User> getAll();
	Boolean create(User user);
	Boolean update(User user);
	Boolean delete(String userName);
	Page<User> getAll(Integer pageNo);
	List<User> search(String keyword);
	Page<User> search(String keyword, Integer pageNo);
}
