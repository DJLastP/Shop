package com.study.shop.member.service;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.study.shop.member.vo.MemberVO;

import jakarta.annotation.Resource;


@Service("userDetailsService")
public class UserDetailsServiceImpl implements UserDetailsService{
	@Resource(name = "memberService")
	private MemberService memberService;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		MemberVO member = memberService.login(username);;
		
		if(member == null) {
			throw new UsernameNotFoundException("오류");
		}
		
		
		UserDetails userDetails = User.withUsername(member.getMemId())
													.password(member.getMemPw())
													.roles(member.getMemRole())
													.build();
		
		return userDetails;
	}

}
