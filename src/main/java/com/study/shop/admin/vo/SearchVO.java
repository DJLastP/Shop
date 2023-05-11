package com.study.shop.admin.vo;

import com.study.shop.util.PageVO;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class SearchVO {
	
	private String searchValue;
	private String searchBy;
	private String[] status;
	private String orderBy;
	private String fromDate;
	private String toDate;
	private String buyCode;
	private String memId;
	private String memTell;
	private String[] buyCodes;
	private PageVO pageVO;

}
