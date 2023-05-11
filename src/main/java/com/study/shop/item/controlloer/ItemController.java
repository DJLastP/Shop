package com.study.shop.item.controlloer;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.study.shop.item.service.ItemService;

import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/item")
public class ItemController {
	@Resource(name = "itemService")
	private ItemService itemService;
	
	//카테고리 목록 상품목록 출력 
	@GetMapping("/itemList")
	public String cateManage(Model model) {
		
		model.addAttribute("itemListForUser", itemService.itemListForUser());
		return "content/item/item_list";
	}
	
	//상품 상세 페이지이동
	@GetMapping("/itemDetail")
	public String itemDetail(String itemCode, Model model, HttpServletRequest request) {
		String date = request.getHeader("Referer");
		model.addAttribute("itemDetail", itemService.itemDetail(itemCode));

		return "content/item/item_detail";
	}
	
	
	
	
	
}
