package com.study.shop.buy.service;


import java.util.List;

import com.study.shop.admin.vo.OrderStatusVO;
import com.study.shop.buy.vo.SearchVO;
import com.study.shop.cart.vo.BuyVO;

public interface BuyService {
	//다음 buycode 조회
	String getNextBuyCode();
	//상품 선택 구매
	void buy(BuyVO buyVO, OrderStatusVO orderStatusVO);
	//구매내역조회
	List<BuyVO> getBuyList(SearchVO searchVO);
	
}
