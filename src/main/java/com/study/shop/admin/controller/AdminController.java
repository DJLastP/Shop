package com.study.shop.admin.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.study.shop.admin.service.AdminService;
import com.study.shop.admin.vo.SearchVO;
import com.study.shop.admin.vo.SubMenuVO;
import com.study.shop.cart.vo.BuyVO;
import com.study.shop.item.service.ItemService;
import com.study.shop.item.vo.CategoryVO;
import com.study.shop.item.vo.ImgVO;
import com.study.shop.item.vo.ItemVO;
import com.study.shop.util.ConstVariable;
import com.study.shop.util.DateUtil;
import com.study.shop.util.PageVO;
import com.study.shop.util.UploadUtil;

import jakarta.annotation.Resource;

@Controller
@RequestMapping("/admin")
public class AdminController {
	@Resource(name = "adminService")
	private AdminService adminService;
	@Resource(name = "itemService")
	private ItemService itemService;
	
	//카테고리 관리 페이지
	@GetMapping("/cateManage")
	public String cateManage(Model model, SubMenuVO subMenuVO) {
		subMenuVO.setMenuInfo(ConstVariable.DEFAULT_MENU_CODE, ConstVariable.DEFAULT_SUB_MENU_CODE);
		
		//카테고리 목록 조회
		model.addAttribute("categoryList", adminService.getCateListForAdmin());
		
		return "content/admin/cate_manage";
	}
	
	//상품등록 페이지
	@GetMapping("/regItem")
	public String regItemForm(Model model, SubMenuVO subMenuVO) {

		subMenuVO.setMenuInfo("MENU_001", "SUB_MENU_002");
		
		model.addAttribute("categoryList", itemService.cateList());
		return "content/admin/reg_item";
	}
	
	//상품등록
	@PostMapping("/insertItem")
	public String regItem(ItemVO itemVO, MultipartFile mainImg, MultipartFile[] subImg) {
		//파일 첨부
		//메인이미지 업로드
		ImgVO attachedImgVO =  UploadUtil.uploadFile(mainImg);
		//서브 이미지들 업로드
		List<ImgVO> attachedImgList = UploadUtil.multiFileUpload(subImg);
		String ItemCode = adminService.getNextItemCode();
		itemVO.setItemCode(ItemCode);
		//상품이미지 디비 등록
		///상품 이미지 등록 쿼리 실행시 모든 빈 값을 채워줄 데이터를 가진List
		List<ImgVO> imgList = attachedImgList;
		imgList.add(attachedImgVO);
		//ITEM_CODE 데이터 추가
		for(ImgVO e : imgList) {
			e.setItemCode(ItemCode);
		}
		
		itemVO.setImgList(imgList);
		adminService.regItem(itemVO);
		return "redirect:/admin/regItem";
	}
	
	//상품 업데이트 정보 조회
	@PostMapping("/updateItemFormAjax")
	@ResponseBody
	public Map<String, Object> updateItemForm(String itemCode) {
		//사용중인 카테고리 목록
		List<CategoryVO> cateList = itemService.cateList();
		//상품 상세 정보
		ItemVO item =  adminService.updateItemForm(itemCode);
		
		Map<String, Object> mapData = new HashMap<>();
		mapData.put("cateList", cateList);
		mapData.put("item", item);
		return mapData;
	}
	
	//상품 정보 업데이트
	@PostMapping("/updateItem")
	public String updateItem(ItemVO itemVO) {
			adminService.updateItem(itemVO);
		return "redirect:/admin/itemManage?menuCode=MENU_001&subMenuCode=SUB_MENU_003";
	}
	
	//상품 재고 페이지
	//@requstMapping 어노테이션으로 요청을 받으면 get,post 둘다 받을수 있음
	@RequestMapping("/itemManage")
	public String stockManage(ItemVO itemVO, Model model, SubMenuVO subMenuVO) {
		subMenuVO.setMenuInfo("MENU_001", "SUB_MENU_003");
		
		model.addAttribute("categoryList", adminService.getCateListForAdmin());
		model.addAttribute("getItemList", adminService.getItemList(itemVO));

		return "content/admin/item_manage";
	}

	//카테고리 추가
	@ResponseBody
	@PostMapping("/setCateListAjax")
	public void setCateList(String cateName) {
		adminService.setCateList(cateName);
	}
	
	//카테고리 중복검사
	@ResponseBody
	@PostMapping("/checkCateNameAjax")
	public int checkCateName(String cateName) {
		//케티고리명 중복 여부 체크 쿼리
		return adminService.checkCateName(cateName);
	}
	
	//카테고리 관리페이지의 카테고리 목록 조회
	@ResponseBody
	@PostMapping("/getCateListAjax")
	public List<CategoryVO> getCateList() {
		return adminService.getCateListForAdmin();
	}
	
	//카테고리 사용여부 수정
	@ResponseBody
	@PostMapping("/updateIsUseAjax")
	public void updateIsUse(CategoryVO categoryVO) {
		adminService.updateIsUse(categoryVO);
	}
	
	//카테고리 삭제
	@GetMapping("/deleteCate")
	public String deleteCate(String cateCode) {
		adminService.deleteCate(cateCode);
		return "redirect:/admin/cateManage";
	}
	
	//주문관리 페이지
	@GetMapping("/saleStatusPerMonth")
	public String saleStatusPerMonth(Model model,SubMenuVO subMenuVO ,BuyVO buyVO,@RequestParam(required = false, defaultValue = "0")int year) {
		subMenuVO.setMenuInfo("MENU_003", "SUB_MENU_007");
		
		if(year == 0) {
			year = DateUtil.getNowYearToInt();
		}
		
		List<Map<String, String>> mapList = adminService.getSaleMap(year);
		
		//hash맵이있는 List 를 TreeMap으로 바꾸기
		List<TreeMap<String, String>> treeMapList = new ArrayList<>();
		for (Map<String, String> map : mapList) {
		    TreeMap<String, String> treeMap = new TreeMap<>(map);
		    treeMapList.add(treeMap);
		}
		
		model.addAttribute("saleLists",treeMapList);
		model.addAttribute("year", year);
		model.addAttribute("thisYear", DateUtil.getNowYearToInt());
		return "content/admin/sale_manage";
	}
	
	//회원관리페이지
	@GetMapping("/memberManage")
	public String memberManage(Model model,SubMenuVO subMenuVO) {
		
		return "content/admin/member_manage";
	}
	//주문관리페이지
	@RequestMapping("/orderManage")
	public String orderManage(Model model, SubMenuVO subMenuVO, SearchVO searchVO, PageVO pageVO) {
		
		subMenuVO.setMenuInfo("MENU_003", "SUB_MENU_006");
		//주문관리페이지 검색을위한 데이터 세팅
		if(searchVO.getSearchBy() != null && searchVO.getSearchBy().equals("buyCode")) {
			searchVO.setBuyCode(searchVO.getSearchValue());
		}
		else if(searchVO.getSearchBy() != null &&  searchVO.getSearchBy().equals("memId")) {
			searchVO.setMemId(searchVO.getSearchValue());
		}
		else if(searchVO.getSearchBy() != null &&  searchVO.getSearchBy().equals("memTell")) {
			searchVO.setMemTell(searchVO.getSearchValue());
		}
		
		//페이지처리용 데이터 세팅
		pageVO.setTotalDateCnt(adminService.getStatusCnt(searchVO));
		
		if(pageVO.getTotalDateCnt() <= pageVO.getDisplayCnt()) {
			pageVO.setNowPage(1);
		}
		pageVO.setPageInfo();
		searchVO.setPageVO(pageVO);
		
		//주문내역 전체조회
		List<Map<String, Object>> ResultOrderList = adminService.getOrderList(searchVO);
		SearchVO order = new SearchVO();
		List<Map<String, Object>> originOrderList = adminService.getOriginOrderList(order);
		
		//주문상태별 리스트
		List<Map<String, Object>> orderStatus1 = new ArrayList<>();
		List<Map<String, Object>> orderStatus2 = new ArrayList<>();
		List<Map<String, Object>> orderStatus3 = new ArrayList<>();
		List<Map<String, Object>> orderStatus4 = new ArrayList<>();
		List<Map<String, Object>> orderStatus5 = new ArrayList<>();
		
		//주문상태별 분류
		for(Map<String, Object> map : originOrderList) {
		
			String statusCode = String.valueOf(map.get("STATUS_CODE"));
			switch(statusCode) {
				case "1" : orderStatus1.add(map);
					break;
				case "2" : orderStatus2.add(map);
					break;
				case "3" : orderStatus3.add(map);
					break;
				case "4" : orderStatus4.add(map);
					break;
				case "5" : orderStatus5.add(map);
					break;
				}
		}
		
		List<List<Map<String, Object>>> result = new ArrayList<>();
		
		result.add(orderStatus1);
		result.add(orderStatus2);
		result.add(orderStatus3);
		result.add(orderStatus4);
		result.add(orderStatus5);
		
		model.addAttribute("orderList", ResultOrderList);
		model.addAttribute("statusList", adminService.getStatusList());
		model.addAttribute("orderStatus", result);
		model.addAttribute("searchData", searchVO);
		
		return "content/admin/order_manage";
	}
	
	@PostMapping("/updateStatus")
	public String updateStatus(SearchVO searchVO) {
		adminService.updateStatus(searchVO);
		return "redirect:/admin/orderManage";
	}
	
	
	
		//카테고리별 판매 현황
	@GetMapping("/saleStatusByCategory")
	public String saleStatusByCategory(Model model, SubMenuVO subMenuVO) {
			
		List<Map<String, String>> list = adminService.saleStatusByCategory();
		
		model.addAttribute("list", list);
		return "content/admin/sale_status_by_category";
	}
	
	//카테고리별 판매 추이 Ajax
	@PostMapping("/saleStatusByCategoryAjax")
	@ResponseBody
	public List<String> saleStatusByCategoryAjax(Model model, SubMenuVO subMenuVO) {
			
		List<Map<String, String>> list = adminService.saleStatusByCategory();

		List<String> saleCateList = new ArrayList<>();
		
		for(String strKey : list.get(0).keySet()) {
			String str = String.valueOf(list.get(0).get(strKey));
			saleCateList.add(str);
		}
		
		model.addAttribute("list",saleCateList);
		return saleCateList;
	}
	
	//월별매출현황 그래프
	@ResponseBody
	@PostMapping("/getChartDataAjax")
	public Map<String, List<String>> getChartDataAjax(int year) {
		
		List<Map<String, String>> mapList = adminService.getSaleMap(year);
		
		//hash맵이있는 List 를 TreeMap으로 바꾸기
		List<TreeMap<String, String>> treeMapList = new ArrayList<>();
		for (Map<String, String> map : mapList) {
		    TreeMap<String, String> treeMap = new TreeMap<>(map);
		    treeMapList.add(treeMap);
		}
		
		List<String> salesList = new ArrayList<>();
		List<String> cntList = new ArrayList<>();
		
		for(String strKey : treeMapList.get(0).keySet()) {
			String str = String.valueOf(treeMapList.get(0).get(strKey));
			salesList.add(str);
		}
		
		for(String strKey : treeMapList.get(1).keySet()) {
			String str = String.valueOf(treeMapList.get(1).get(strKey));
			cntList.add(str);
		}
		
		//두개의 리스트를 담을 수 있는 통
		Map<String, List<String>> result = new HashMap<>();
		
		result.put("cntList", cntList);
		result.put("salesList", salesList);
		
		return result;
	}
	
	//카테고리별 판매추이 2
	@GetMapping("saleStatusByCategory2")
	public String saleStatusByCategory2(SubMenuVO subMenuVO) {
		return "content/admin/sale_status_by_category";
	}
	
	@ResponseBody
	@PostMapping("saleStatusByCategoryAjax2")
	public List<Map<String, Object>> saleStatusByCategoryAjax2() {
		List<Map<String, Object>> mapList = adminService.saleStatusByCategory2();
		
		return mapList;
	}
	//구매상세정보
	@ResponseBody
	@PostMapping("/buyDetailList")
	public List<Map<String, Object>> getBuyDetailList(SearchVO searchVO, Authentication authentication, Model model) {
		
		List<Map<String, Object>> list = adminService.getBuyDetailList(searchVO); 
		
		model.addAttribute("list", list);
		return list;
	}
	
}
