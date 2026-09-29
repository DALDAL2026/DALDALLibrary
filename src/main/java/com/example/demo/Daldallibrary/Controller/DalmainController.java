package com.example.demo.Daldallibrary.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class DalmainController {
	@Autowired
	private PasswordEncoder passwordEncoder;

	@RequestMapping("/")
	public String roor() {
		return "redirect:/main/enter";
	}
	
	@RequestMapping("main/enter")
	public String enter() {
		return "main/enter";
	}
	
	@RequestMapping("/main/main")
	public String main() {
		return "main/main";
	}
}
