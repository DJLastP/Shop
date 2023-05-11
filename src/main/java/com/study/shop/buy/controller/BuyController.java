package com.study.shop.buy.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.study.shop.admin.vo.OrderStatusVO;
import com.study.shop.buy.service.BuyService;
import com.study.shop.buy.vo.BuyDetailVO;
import com.study.shop.buy.vo.SearchVO;
import com.study.shop.cart.vo.BuyVO;
import com.study.shop.util.DateUtil;

import jakarta.annotation.Resource;

@Controller
@RequestMapping("/buy")
public class BuyController {
	@Resource(name = "buyService")
	private BuyService buyService;
	
	@ResponseBody
	@PostMapping("/buysAjax")
	public void buysAjax(@RequestBody HashMap<String, Object> mapData, Authentication authentication, BuyVO buyVO, OrderStatusVO orderStatusVO) {
		//다음번 buycode 조회
		String nextBuyCode = buyService.getNextBuyCode();
		buyVO.setBuyCode(nextBuyCode);
		//세션에있는 회원아이디 조회
		
		User user = (User)authentication.getPrincipal();
		String memId = user.getUsername();
		
		buyVO.setMemId(memId);
		
		//구매내역
		
		orderStatusVO.setBuyCode(nextBuyCode);
		orderStatusVO.setMemId(memId);
		orderStatusVO.setStatusCode(1);
		
		//총 가격
		buyVO.setBuyPrice(Integer.parseInt(mapData.get("final_price").toString()));
		//구매상세정보 
		ObjectMapper mapper = new ObjectMapper();
		BuyDetailVO[] arr = mapper.convertValue(mapData.get("detail_info_arr"), BuyDetailVO[].class);
		List<BuyDetailVO> list = Arrays.asList(arr);
		for(int i = 0; i < list.size(); i++) {
			list.get(i).setBuyCode(nextBuyCode);
		}
		buyVO.setBuyDetailList(list);
		buyService.buy(buyVO, orderStatusVO);
		
	}
	//구매 내역 페이지 이동
	@GetMapping("buyList")
	public String buyList(Authentication authentication , Model model,SearchVO searchVO, String personalMenu) {
		//오늘 날짜
		String nowDate = DateUtil.getNowDateToString();
		//이번달의 첫날
		String firstDate = DateUtil.getFirstDateOfMonth();
		//넘어온 날짜 데이터가 없다면 기본값으로 날짜 세팅
		if(searchVO.getFirstDate() == null) {
			searchVO.setFirstDate(firstDate);
		}
		if(searchVO.getLastDate() == null) {
			searchVO.setLastDate(nowDate);
		}
		
		User user = (User)authentication.getPrincipal();
		searchVO.setMemId(user.getUsername());
		
		
		model.addAttribute("buyLists", buyService.getBuyList(searchVO));
		model.addAttribute("personalMenu", personalMenu);
		
		return "content/buy/buy_list";
	}
	
	//구매내역 날짜로 검색
	@PostMapping("buyList")
	public String searchBuyDate(SearchVO searchVO,BuyVO buyVO,Authentication authentication  , Model model, String personalMenu) {
		
		User user = (User)authentication.getPrincipal();
		searchVO.setMemId(user.getUsername());
		model.addAttribute("buyLists", buyService.getBuyList(searchVO));
		
		model.addAttribute("personalMenu", personalMenu);
		
		return "content/buy/buy_list";
	}
	
	//바로구매
	@PostMapping("directBuy")
	public String directBuy(BuyVO buyVO, BuyDetailVO buyDetailVO, Authentication authentication, OrderStatusVO orderStatusVO) {
		List<BuyDetailVO> list = new ArrayList<>();
		User user = (User)authentication.getPrincipal();
		buyVO.setMemId(user.getUsername());
		
		String nextBuyCode = buyService.getNextBuyCode();
		buyVO.setBuyCode(nextBuyCode);
		buyDetailVO.setBuyDetailPrice(buyVO.getBuyPrice());
		buyDetailVO.setBuyCode(nextBuyCode);
		
		int buyPrice = Integer.parseInt(buyDetailVO.getBuyCnt()) * Integer.parseInt(buyDetailVO.getItemPrice());
		buyVO.setBuyPrice(buyPrice);
		buyDetailVO.setBuyDetailPrice(buyPrice);
		list.add(buyDetailVO);
		buyVO.setBuyDetailList(list);
		
		buyService.buy(buyVO, orderStatusVO);
		return "redirect:/buy/buyList";
	}
	
	
}
