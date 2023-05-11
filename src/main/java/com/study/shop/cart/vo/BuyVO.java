package com.study.shop.cart.vo;

import java.util.List;

import com.study.shop.buy.vo.BuyDetailVO;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class BuyVO {
	private String buyCode;
	private String memId;
	private int buyPrice;
	private String buyDate;
	private String cartCode;
	private String[] cartCodes;
	List<BuyDetailVO> buyDetailList;
}
