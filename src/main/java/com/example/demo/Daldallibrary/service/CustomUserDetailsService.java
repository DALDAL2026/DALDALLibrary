package com.example.demo.Daldallibrary.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.Daldallibrary.dao.IDalmemberDAO;
import com.example.demo.Daldallibrary.dto.DalmemberDTO;

@Service
public class CustomUserDetailsService implements UserDetailsService {
	@Autowired
	private IDalmemberDAO dao;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		DalmemberDTO dto = dao.findById(username);
		
		if(dto == null) {
			throw new UsernameNotFoundException("사용자가 없습니다.");
		}
		
		return User.builder()
				   .username(dto.getDalMe())
				   .password(dto.getDalMpwd())
				   .roles(dto.getDalMauthority())	   
				   .build();
	}
}
