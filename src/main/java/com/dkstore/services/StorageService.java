package com.dkstore.services;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

public interface StorageService {
	void init();
	void store(MultipartFile file);
	List<String> storeMultiple(MultipartFile[] files);
}
