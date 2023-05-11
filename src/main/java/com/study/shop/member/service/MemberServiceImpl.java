package com.study.shop.member.service;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.study.shop.member.vo.MemberVO;

@Service("memberService")
public class MemberServiceImpl implements MemberService {
	@Autowired
	private SqlSessionTemplate sqlSession;

	@Override
	public int checkId(String memId) {
		
		return sqlSession.selectOne("memberMapper.checkId",memId);
	}

	@Override
	public void joinMember(MemberVO memberVO) {
		sqlSession.insert("memberMapper.joinMember", memberVO);
	}

	@Override
	public MemberVO login(String memId) {

		return sqlSession.selectOne("memberMapper.login", memId);
	}

	@Override
	public void findPw(MemberVO memberVO) {
		sqlSession.insert("memberMapper.findPw", memberVO);
	}

	@Override
	public String getEmail(MemberVO memberVO) {
		return sqlSession.selectOne("memberMapper.getEmail", memberVO);
	}
	
	
}


