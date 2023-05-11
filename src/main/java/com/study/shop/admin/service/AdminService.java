package com.study.shop.admin.service;

import java.util.List;
import java.util.Map;

import com.study.shop.admin.vo.MenuVO;
import com.study.shop.admin.vo.SearchVO;
import com.study.shop.admin.vo.SubMenuVO;
import com.study.shop.cart.vo.BuyVO;
import com.study.shop.item.vo.CategoryVO;
import com.study.shop.item.vo.ItemVO;

public interface AdminService {
	//카테고리 목록 조회
	List<CategoryVO> getCateListForAdmin();
	//카테고리 목록 추가
	void setCateList(String cateName);
	//카테고리이름 중복 체크
	int checkCateName(String cateName);
	//카테고리사용여부수정
	void updateIsUse(CategoryVO categoryVO);
	//카테고리삭제
	void deleteCate(String cateCode);
	//관리자메뉴 (헤더)
	List<MenuVO> getAdminMenu();
	//관리자 서브메뉴 (사이드)
	List<SubMenuVO> getSubMenuList(String menuCode);
	//다음 itemCode 조회
	String getNextItemCode();
	//상품추가
	void regItem(ItemVO itemVO);
	//상품목록조회
	List<ItemVO> getItemList(ItemVO itemVO);
	//상품 관리 페이지 업데이트정보 조회
	ItemVO updateItemForm(String itemCode);
	//상품정보 업데이트
	void updateItem(ItemVO itemVO);
	//월별매출정보
	List<BuyVO> getSaleList(String setYear);
	//월별 매출 정보
	List<Map<String, String>> getSaleMap(int year);
	//카테고리별 판매 추이
	List<Map<String, String>> saleStatusByCategory();
	//카테고리별 판매추이 2
	List<Map<String, Object>> saleStatusByCategory2();
	//주문목록 전체 조회
	List<Map<String, Object>> getOrderList(SearchVO searchVO);
	List<Map<String, Object>> getOriginOrderList(SearchVO searchVO);
	//주문 상태목록 조회
	List<String> getStatusList();
	//주문 상태 업데이트
	void updateStatus(SearchVO searchVO);
	//주문정보 총갯수
	int getStatusCnt(SearchVO searchVO);
	
	List<Map<String, Object>> getBuyDetailList(SearchVO searchVO);
	
}
