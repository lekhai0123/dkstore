package com.dkstore.services;

import com.dkstore.models.User;

public interface AuthenticationService {
	String registerUser(User user);
	String confirmToken(String token);
}
