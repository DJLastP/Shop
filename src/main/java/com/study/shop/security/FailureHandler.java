package com.study.shop.security;

import java.io.IOException;
import java.io.PrintWriter;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


//로그인 실패시 자동으로 실행되는 클래스
public class FailureHandler extends SimpleUrlAuthenticationFailureHandler{

	@Override
	public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException exception) throws IOException, ServletException {
		//오류 메세지
		String eMsg = "";
		
		//BadCredentialsException 입력한 아이디 오류발생
		if(exception instanceof BadCredentialsException) {
			eMsg = "아이디 혹은 비밀번호를 확인하세요";
		}
		//UsernameNotFoundException 없는 계정
		else if(exception instanceof UsernameNotFoundException) {
			eMsg = "존재하지 않는 계정입니다.";
		}
		else {
			eMsg = "알수 없는 이유로 로그인에 실패했습니다.";
		}
		
		String memId = request.getParameter("memId");
		
		PrintWriter p = response.getWriter();
		p.write("fail");
		p.flush();
		
		
	}
	
}
