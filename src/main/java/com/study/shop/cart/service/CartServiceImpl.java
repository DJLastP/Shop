package com.study.shop.cart.service;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.study.shop.cart.vo.BuyVO;
import com.study.shop.cart.vo.CartVO;
@Service("cartService")
public class CartServiceImpl implements CartService {
	@Autowired
	private SqlSessionTemplate sqlSession;
	
	
	@Override
	public void addCart(CartVO cartVO) {
		sqlSession.insert("cartMapper.addCart", cartVO);
	}


	@Override
	public List<CartVO> cartList(String memId) {
		return sqlSession.selectList("cartMapper.cartList", memId);
	}


	@Override
	public void deleteCartItem(String cartCode) {
		sqlSession.delete("cartMapper.deleteCartItem", cartCode);
	}


	@Override
	public void updateCartCnt(CartVO cartVO) {
		sqlSession.update("cartMapper.updateCartCnt", cartVO);
	}


	@Override
	public void buySelectedItemAjax(BuyVO buyVO) {
		sqlSession.insert("cartMapper.buySelectedItemAjax", buyVO);
	}


	@Override
	public void buyItemDetailAjax(BuyVO buyVO) {
		sqlSession.insert("cartMapper.buyItemDetailAjax", buyVO);
	}


	@Override
	public String getNextBuyCode() {
		return sqlSession.selectOne("cartMapper.getNextBuyCode");
	}
}
