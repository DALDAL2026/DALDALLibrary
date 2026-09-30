package com.example.demo.Daldallibrary.service;

import java.security.SecureRandom;
import java.time.Duration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Value;


@Service
public class DalEmailService {

	@Autowired
	private JavaMailSender mailSender;
	
	// redis와 문자열 데이터를 쉽게 주고받게 해주는 템플릿
	@Autowired
	private StringRedisTemplate redisTemplate;
	
	// 인증번호 유효시간(3분)
	private static final long AUTH_TIME_OUT =  3 * 60;

	// 발신자 주소 (spring.mail.username과 동일한 이메일)
	@Value("${spring.mail.username}")
	private String fromEmail;

	public void sendVerificationEmail(String email) {
		String authCode = createRandomCode();

		ValueOperations<String, String> values = redisTemplate.opsForValue();
		values.set(email, authCode, Duration.ofSeconds(AUTH_TIME_OUT));

		SimpleMailMessage message = new SimpleMailMessage();
		message.setFrom(fromEmail);          // ← 이 줄 추가
		message.setTo(email);
		message.setSubject("[DALDLA 도서관] 회원가입 이메일 인증 번호");
		message.setText("인증 번호는 [" + authCode + "] 입니다. 3분 이내에 입력해주세요.");

		mailSender.send(message);
	}
	
	// 2. 인증 번호 확인
	public boolean verifyCode(String email, String code) {
		ValueOperations<String, String> values = redisTemplate.opsForValue();
		String storedCode = values.get(email);
		
		if(storedCode != null && storedCode.equals(code)) {
			redisTemplate.delete(email);
			return true;
		}
		return false;
	}
	
	// 6자리 난수 생성 메서드
	private String createRandomCode() {
		SecureRandom random = new SecureRandom();
		return String.valueOf(100000 + random.nextInt(900000));
	}
}
