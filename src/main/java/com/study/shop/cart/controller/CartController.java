package com.study.shop.cart.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.study.shop.cart.service.CartService;
import com.study.shop.cart.vo.BuyVO;
import com.study.shop.cart.vo.CartVO;

import jakarta.annotation.Resource;

@Controller
@RequestMapping("/cart")
public class CartController {
	@Resource(name = "cartService")
	private CartService cartService;
	
	//장바구니 등록
		@PostMapping("/addCartAjax")
		@ResponseBody
		public void addCart(CartVO cartVO,Authentication authentication) {
			
			User user = (User)authentication.getPrincipal();
			cartVO.setMemId(user.getUsername());
			cartService.addCart(cartVO);
		}
		
		//장바구니 페이지 이동
		@GetMapping("/itemCart")
		public String memInfo(Authentication authentication,Model model, 
				@RequestParam(required = false, defaultValue = "1") String personalMenu) {
			User user = (User)authentication.getPrincipal();
			
//			if(user == null) {
//				return "redirect:/item/itemList";
//			}
			
			model.addAttribute("cartList", cartService.cartList(user.getUsername()));
			model.addAttribute("personalMenu", personalMenu);
			
			return "content/cart/item_cart";
		}
		
		//장바구니 아이템 삭제
		@PostMapping("/deleteCartItemAjax")
		@ResponseBody
		public void deleteCartItem(String cartCode) {
			cartService.deleteCartItem(cartCode);
		}
		
		//장바구니 수량 변경
		@PostMapping("/updateCartCntAjax")
		@ResponseBody
		public void updateCartCntAjax(CartVO cartVO) {
			cartService.updateCartCnt(cartVO);
		}
		
		//선택구매 - 구매정보 
		@PostMapping("/buySelectedItemAjax")
		@ResponseBody
		public void buySelectedItemAjax(BuyVO buyVO, Authentication authentication) {

			User user = (User)authentication.getPrincipal();
			buyVO.setMemId(user.getUsername());
			
			buyVO.setBuyCode(cartService.getNextBuyCode());
			
			cartService.buySelectedItemAjax(buyVO);
			String[] cartCodes = buyVO.getCartCodes();
			for(int i = 0; i < cartCodes.length; i++) {
				buyVO.setCartCode(cartCodes[i]);
				cartService.buyItemDetailAjax(buyVO);
			}
			
		}
		
//		
//		//선택구매 - 구매 상세정보
//		@PostMapping("/buyItemDetailAjax")
//		@ResponseBody
//		public void buyItemDetailAjax(BuyVO buyVO, HttpSession session) {
//			MemberVO loginInfo = (MemberVO)session.getAttribute("loginInfo");
//			buyVO.setMemId(loginInfo.getMemId());
//			cartService.buyItemDetailAjax(buyVO);
//		}
}
