package com.example.demo.Daldallibrary.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.Daldallibrary.service.DalEmailService;


@Controller
public class DalEmailController {

	@Autowired
	private DalEmailService dalemailService;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	// 1. 이메일 인증번호 전송 요청(Ajax로 호출)
	@PostMapping("/guest/sendAuthCode")
	@ResponseBody
	public String sendAuthCode(@RequestParam("dalMe") String email) {
		try {
			dalemailService.sendVerificationEmail(email);
			return "success";
		}catch(Exception e) {
			e.printStackTrace();
			return "fail";
		}
	}
	
	// 2. 인증번호 확인 요철(Ajax로 호출)
	@PostMapping("/guest/verifyAuthCode")
	@ResponseBody
	public boolean verifyAuthCode(@RequestParam("dalMe") String email,
								  @RequestParam("code") String code) {
		return dalemailService.verifyCode(email, code);
	}
}