package com.study.shop.member.controller;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.study.shop.member.service.EmailService;
import com.study.shop.member.service.MemberService;
import com.study.shop.member.vo.MemberVO;

import jakarta.annotation.Resource;

@Controller
@RequestMapping("/member")
public class MemberController {
	@Resource(name = "memberService")
	private MemberService memberService;
	@Autowired
	private PasswordEncoder encoder;
	@Resource(name = "emailService")
	private EmailService emailService;
	

	
		@PostMapping("/checkId")
		@ResponseBody
		public int checkId(String memId) {
			
			return memberService.checkId(memId);
		}
		
		@PostMapping("/joinMember")
		public String joinMember(MemberVO memberVO) {
			
			memberVO.setMemPw(encoder.encode(memberVO.getMemPw()));
			memberVO.setMemTell(memberVO.getMemTell().replace( ','  , '-'));
			
			memberService.joinMember(memberVO);
			
			return "redirect:/";
		}
		
		//로그인페이지이동
		@GetMapping("/loginForm")
		public String loginForm() {
			return "/content/member/login";
		}
		
		//비밀번호 초기화
		@ResponseBody
		@PostMapping("/findPwAjax")
		public void findPw(MemberVO memberVO) {
			
			//랜덤비번 생성
			String newPw = getRandomPw(6);
			
			//이메일주소
			//String email = memberService.getEmail(memberVO);
			
			//그냥 메일보내기
			//emailService.sendSimpleMessage(email, newPw);
			
			//html 메일보내기
			emailService.sendHTMLEmail();
			
			//랜덤비번암호화
			memberVO.setMemPw(encoder.encode(newPw));
			memberService.findPw(memberVO);
		}
		
		
		//랜덤비번 생성 메소드
		public String getRandomPw(int length) {
			int leftLimit = 48; // numeral '0'
			int rightLimit = 122; // letter 'z'
			int targetStringLength = length;
			Random random = new Random();

			String generatedString = random.ints(leftLimit,rightLimit + 1)
			  .filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97))
			  .limit(targetStringLength)
			  .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
			  .toString();
			
			return generatedString;
		}
		
}
