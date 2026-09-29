package com.example.demo.Daldallibrary.auth;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import jakarta.servlet.DispatcherType;

@Configuration
public class WebSecurityConfig {
	@Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
	
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http.csrf((csrf) -> csrf.disable()) // CSRF 보호 비활성화
			.cors((cors) -> cors.disable()) // CORS 비활성화
			.authorizeHttpRequests(request -> request
					.dispatcherTypeMatchers(DispatcherType.FORWARD).permitAll() //내부 포워드 요청 허용
					.requestMatchers("/","/main/**","/loginForm").permitAll() //루트(/)는 모두 허용
					.requestMatchers("/css/**","/js/**","/images/**").permitAll() // 정적(static)폴더 아래 css,js,images 모든 폴더에 요청 허용
					.requestMatchers("/guest/**").permitAll() // guest 폴더는 모든 파일의 요청 허용
					.requestMatchers("/member/**", "/board/**").hasAnyRole("USER","ADMIN") // member, board 폴더는 USER, ADMIN만 허용(회원페이지에 일반적으로 적용)
					.requestMatchers("/admin/**").hasAnyRole("ADMIN") // admin 폴더는 ADMIN만 허용(주로 관리자페이지)
					.anyRequest().authenticated() // 나머지는 모두 인증이 필요
			);
		
		// 로그인
		http.formLogin((formLogin) -> formLogin
					.loginPage("/loginForm") // 로그인 페이지
					.loginProcessingUrl("/j_spring_security_check") // action 매핑주소
					.failureUrl("/loginError") // 로그인 실패 시 이동할 페이지
					.defaultSuccessUrl("/")
					.usernameParameter("id")
					.passwordParameter("passwd")
					.permitAll()
		);
		
		// 로그아웃
		http.logout((logout) -> logout
					.logoutUrl("/logout")
					.logoutSuccessUrl("/") // 로그아웃 시 이동할 페이지
					.permitAll()
		);
		
		
		return http.build();
	}


}
