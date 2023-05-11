package com.study.shop.cart.service;

import java.util.List;

import com.study.shop.cart.vo.BuyVO;
import com.study.shop.cart.vo.CartVO;

public interface CartService {
	//장바구니 추가
	void addCart(CartVO cartVO);
	//장바구니 목록조회
	List<CartVO> cartList(String memId);
	//장바구니 상품 삭제
	void deleteCartItem(String cateCode);
	//장바구니 상품 수량 변경
	void updateCartCnt(CartVO cartVO);
	//장바구니 선택상품 구매시 구매정보 등록
	void buySelectedItemAjax(BuyVO buyVO);
	//장바구니 선택상품 구매 상세정보 등록
	void buyItemDetailAjax(BuyVO buyVO);
	//다음 buyCode 조회
	String getNextBuyCode();
}
