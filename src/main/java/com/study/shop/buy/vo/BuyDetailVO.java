package com.study.shop.buy.vo;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class BuyDetailVO {
	@JsonProperty("buy_detail_code")
	private String buyDetailCode;
	@JsonProperty("item_code")
	private String itemCode;
	@JsonProperty("buy_cnt")
	private String buyCnt;
	@JsonProperty("buy_detail_price")
	private int buyDetailPrice;
	@JsonProperty("buy_code")
	private String buyCode;
	private String itemName;
	private String attachedFileName;
	private String itemPrice;
}
