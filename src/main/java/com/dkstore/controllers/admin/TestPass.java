package com.dkstore.controllers.admin;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class TestPass {
public static void main(String[] args) {
	System.out.println(new BCryptPasswordEncoder().encode("123456"));
}
}
