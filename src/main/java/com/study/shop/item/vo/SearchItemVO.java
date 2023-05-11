package com.study.shop.item.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class SearchItemVO {
	private String searchItemName;
	private String[] searchCateCode;
	private int searchItemStatus;
	private String searchFromItemStock;
	private String searchToItemStock;
}
