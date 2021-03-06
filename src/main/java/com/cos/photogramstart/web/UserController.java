package com.cos.photogramstart.web;

import com.cos.photogramstart.config.auth.PrincipalDetails;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class UserController {
	
	@GetMapping("/user/{id}")
	public String profile(@PathVariable int id) {
		return "user/profile";
	}
	
	@GetMapping("/user/{id}/update")
	public String update(@PathVariable int id, @AuthenticationPrincipal PrincipalDetails principalDetails) {

		// 1. 정석적인 방법
		System.out.println("세션 정보 : " + principalDetails.getUser());

		// 2. 극혐
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		PrincipalDetails pd = (PrincipalDetails)auth.getPrincipal();
		System.out.println("직접 찾은 정보 : " + pd.getUser());

		return "user/update";
	}
	
	
}
