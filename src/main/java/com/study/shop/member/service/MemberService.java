package com.study.shop.member.service;

import com.study.shop.member.vo.MemberVO;

public interface MemberService {
	int checkId(String memId);
	
	void joinMember(MemberVO memberVO);
	
	MemberVO login(String memId);
	//비밀번호 업데이트
	void findPw(MemberVO memberVO);
	//메일주소
	String getEmail(MemberVO memberVO);
	
}
