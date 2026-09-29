package com.example.demo.Daldallibrary.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.Daldallibrary.dao.IDalmemberDAO;

@Controller
public class DalmemberController {
	@Autowired
	private IDalmemberDAO memeberDAO;
	
	@Autowired
	private PasswordEncoder passwordEncoder;

	@RequestMapping("/guest/signup")
	public String signup() {
		return "guest/signup";
	}
}
