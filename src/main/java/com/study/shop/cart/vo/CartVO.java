package com.study.shop.cart.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class CartVO {
	private String cartCode;
	private String itemCode;
	private String itemName;
	private String memId;
	private String regDate;
	private String cartCnt;
	private String itemPrice;
	private String attachedFileName;
}
