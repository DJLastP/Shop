package com.study.shop;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


import jakarta.servlet.http.HttpServletRequest;

@Controller
public class IndexController {

	// 프로젝트 시작시 최초의 실행되는 컨트롤러
	@GetMapping("/")
	public String index(Authentication authentication, HttpServletRequest request) {
		String previousPage = request.getHeader("Referer");
		String path = "";

		if (authentication == null) {
			path = "redirect:/item/itemList";
		} else {
			// 로그인한 사람의 권한 정보
			User user = (User) authentication.getPrincipal();

			List<GrantedAuthority> authoList = new ArrayList<>(user.getAuthorities());
			List<String> strAuthoList = new ArrayList<>();
			for (GrantedAuthority auth : authoList) {
				String strAutho = auth.getAuthority();
				strAuthoList.add(strAutho);
			}

			if (strAuthoList.contains("ROLE_ADMIN")) {
				path = "redirect:/admin/cateManage";
			} else {
				if (previousPage == null) {
					path = "redirect:/item/itemList";
				} else {
					path = "redirect:" + previousPage;

					if (previousPage.contains("/admin")) {
						path = "redirect:/item/itemList";
					}
				}
			}
		}
		return path;
	}
	
	@GetMapping("/accessDeny")
	public String accessDeny() {
		return "content/access_deny";
	}
}
