package com.study.shop.admin.service;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.study.shop.admin.vo.MenuVO;
import com.study.shop.admin.vo.SearchVO;
import com.study.shop.admin.vo.SubMenuVO;
import com.study.shop.cart.vo.BuyVO;
import com.study.shop.item.vo.CategoryVO;
import com.study.shop.item.vo.ItemVO;

@Service("adminService")
public class AdminServiceImpl implements AdminService{
	@Autowired
	private SqlSessionTemplate sqlSession;

	@Override
	public List<CategoryVO> getCateListForAdmin() {
		return sqlSession.selectList("adminMapper.getCateListForAdmin");
	}
	
	//관리자상단메뉴
	public List<MenuVO> getAdminMenu(){
		return sqlSession.selectList("adminMapper.getAdminMenu");
	}
	
	//카테고리 추가
	public void setCateList(String cateName) {
		sqlSession.insert("adminMapper.setCateList", cateName);
	}

	//카테고리명 중복체크
	public int checkCateName(String cateName) {
		return sqlSession.selectOne("adminMapper.checkCateName", cateName);
	}
	
	public void updateIsUse(CategoryVO categoryVO) {
		sqlSession.update("adminMapper.updateIsUse",categoryVO);
	}


	@Override
	public void deleteCate(String cateCode) {
		sqlSession.delete("adminMapper.deleteCate", cateCode);
	}

	@Override
	public List<SubMenuVO> getSubMenuList(String menuCode) {
		return sqlSession.selectList("adminMapper.getSubMenuList", menuCode);
	}
	
	@Override
	public String getNextItemCode() {
		return sqlSession.selectOne("adminMapper.getNextItemCode");
	}
	
	@Override
	// 해당 메소드 내의 쿼리 실행을 트렌젝션 처리
	//rollbackFor : 어떤오류가 발생하면 롤백 할꺼냐?
	//Exception.class : 
	@Transactional(rollbackFor = Exception.class)
	public void regItem(ItemVO itemVO) {
		sqlSession.insert("adminMapper.regItem", itemVO);
		sqlSession.insert("adminMapper.insertImgs", itemVO);
	}

	@Override
	public List<ItemVO> getItemList(ItemVO itemVO) {
		return 	sqlSession.selectList("adminMapper.getItemList", itemVO);
	}

	@Override
	public ItemVO updateItemForm(String itemCode) {
		
		return sqlSession.selectOne("adminMapper.updateItemForm", itemCode);
	}

	@Override
	public void updateItem(ItemVO itemVO) {
		sqlSession.update("adminMapper.updateItem", itemVO);
	}

	@Override
	public List<BuyVO> getSaleList(String setYear) {
		return sqlSession.selectList("adminMapper.getSaleList", setYear);
	}

	@Override
	public List<Map<String, String>> getSaleMap(int year) {
		return  sqlSession.selectList("adminMapper.getSaleMap", year);
	}

	@Override
	public List<Map<String, String>> saleStatusByCategory() {
		return sqlSession.selectList("adminMapper.saleStatusByCategory");
	}

	@Override
	public List<Map<String, Object>> saleStatusByCategory2() {
		return sqlSession.selectList("adminMapper.saleStatusByCategory2");
	}
	//주문목록 전체 조회
	@Override
	public List<Map<String, Object>> getOrderList(SearchVO searchVO) {
		return sqlSession.selectList("adminMapper.getOrderList", searchVO);
	}
	@Override
	public List<Map<String, Object>> getOriginOrderList(SearchVO searchVO) {
		return sqlSession.selectList("adminMapper.getOriginOrderList", searchVO);
	}

	@Override
	public List<String> getStatusList() {
		return sqlSession.selectList("adminMapper.getStatusList");
	}

	@Override
	public void updateStatus(SearchVO searchVO) {
		sqlSession.update("adminMapper.updateStatus", searchVO);
	}

	@Override
	public int getStatusCnt(SearchVO searchVO) {
		return sqlSession.selectOne("adminMapper.getStatusCnt", searchVO);
	}

	@Override
	public List<Map<String, Object>> getBuyDetailList(SearchVO searchVO) {
		return sqlSession.selectList("adminMapper.getBuyDetailList", searchVO);
	}
	
}
