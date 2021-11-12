package com.cos.photogramstart.web;

import java.util.HashMap;
import java.util.Map;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cos.photogramstart.domain.user.User;
import com.cos.photogramstart.handler.ex.CustomValidationException;
import com.cos.photogramstart.service.AuthService;
import com.cos.photogramstart.web.dto.auth.SignupDto;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor // final 필드를 DI할때 사용
@Controller // 1. IoC 2. 파일을 리턴하는 컨트롤러
public class AuthController {
	
	
	private final AuthService authService;
	
//	public AuthController(AuthService authService) {
//		// 의존성 주입
//		this.authService = authService;
//	}
	
	
	private static final Logger log = LoggerFactory.getLogger(AuthController.class);

	
	@GetMapping("/auth/signin")
	public String signinForm() {
		return "auth/signin";
	}
	
	@GetMapping("/auth/signup")
	public String signupForm() {
		return "auth/signup";
	}
	
	
	// 회원가입버튼 클릭 -> /auth/signup -> /auth/signin
	@PostMapping("/auth/signup")
	public String signup(@Valid SignupDto signupDto, BindingResult bindingResult) {
		
		if(bindingResult.hasErrors()) {
			Map<String, String> errorMap = new HashMap<>();
			
			for(FieldError error : bindingResult.getFieldErrors()) {
				errorMap.put(error.getField(), error.getDefaultMessage());
				System.out.println(error.getDefaultMessage() + "!!!!!!!!!!!!!!!!!!!!!!!");
			}
			throw new CustomValidationException("유효성검사 실패", errorMap);
		} else {
			User user = signupDto.toEntity();
			log.info(user.toString() + "!!!!!!!!!!!!!!!!!!!!!!!!");
			User userEntity = authService.회원가입(user);

			return "auth/signin";
		}
		

	}

}
