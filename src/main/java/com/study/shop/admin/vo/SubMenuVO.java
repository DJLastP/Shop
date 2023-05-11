package com.study.shop.admin.vo;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class SubMenuVO {
	private String subMenuCode;
	private String subMenuName;
	private String subMenuUrl;
	private String menuCode;
	
	//메뉴정보가 없을경우세팅시키는 메소드
	public void setMenuInfo(String menuCode, String subMenuCode) {
		if(getMenuCode() == null) {
			setMenuCode(menuCode);
		}
		if(getSubMenuCode() == null) {
			setSubMenuCode(subMenuCode);
		}
	}
}
