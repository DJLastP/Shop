package com.study.shop.item.service;

import java.util.List;

import com.study.shop.item.vo.CategoryVO;
import com.study.shop.item.vo.ItemVO;

public interface ItemService {
	//카테고리 리스트 출력
	List<CategoryVO> cateList();
	
	//유저 상품 리스트 출력
	List<ItemVO> itemListForUser();
	
	//상품 상세정보
	ItemVO itemDetail(String itemCode);

}
