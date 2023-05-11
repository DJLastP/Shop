package com.study.shop.buy.service;


import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.study.shop.admin.vo.OrderStatusVO;
import com.study.shop.buy.vo.SearchVO;
import com.study.shop.cart.vo.BuyVO;

@Service("buyService")
public class BuyServiceImpl implements BuyService{
	@Autowired
	private SqlSessionTemplate sqlSession;

	@Override
	public String getNextBuyCode() {
		return sqlSession.selectOne("buyMapper.getNextBuyCode");
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public void buy(BuyVO buyVO, OrderStatusVO orderStatusVO) {
			sqlSession.insert("buyMapper.buy", buyVO);
			sqlSession.insert("buyMapper.buyDetails", buyVO);
			if(orderStatusVO != null) {
				sqlSession.insert("buyMapper.regOrderStatus", orderStatusVO);
			}
	}

	@Override
	public List<BuyVO> getBuyList(SearchVO searchVO) {
		return sqlSession.selectList("buyMapper.getBuyList", searchVO);
	}

	
}
