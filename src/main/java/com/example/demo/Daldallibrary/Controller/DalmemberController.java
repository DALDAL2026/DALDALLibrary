package com.example.demo.Daldallibrary.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.Daldallibrary.dao.IDalmemberDAO;

import lombok.val;

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
	
	@RequestMapping("/guest/jusoPopup")
	public String jusoPopup() {
		return "guest/jusoPopup";
	}
	
	// 3. 최종 회원가입 처리
	@PostMapping("/member/signup")
	public String signup(@RequestParam("dalMe") String email,
						 @RequestParam("dalMpwd") String password,
						 @RequestParam("isEmail") String isEmailVerified) {
		
		if(!"Y".equals(isEmailVerified)) {
			return "redirect:/guest/signup?error=notVerified";
		}
		
		// 비밀번호 암호화
		String encodePassword = passwordEncoder.encode(password);
		
		return "redirect:/loginForm";
	}
}
