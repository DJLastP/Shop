package com.study.shop.item.vo;

import java.util.List;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ItemVO {

	private String itemCode;
	private String cateName;
	private String cateCode;
	private String itemName;
	private String itemPrice;
	private String itemStock;
	private String itemIntro;
	private String itemStatus;
	private String memId;
	private List<ImgVO> imgList;
	private CategoryVO categoryVO;
	private SearchItemVO searchItemVO;
}
