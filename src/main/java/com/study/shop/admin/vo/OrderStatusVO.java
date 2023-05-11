package com.study.shop.admin.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class OrderStatusVO {

	private int orderNum;
	private int statusCode;
	private String buyCode;
	private String updateDate;
	private String orderBy;
	private String memId;
	
}
