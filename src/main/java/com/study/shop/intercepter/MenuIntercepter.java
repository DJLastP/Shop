package com.study.shop.intercepter;

import java.util.Map;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.study.shop.admin.service.AdminService;
import com.study.shop.admin.vo.SubMenuVO;

import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class MenuIntercepter implements HandlerInterceptor {

	@Resource(name = "adminService")
	private AdminService adminService;
	
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		
		//컨트롤러에서 html로 전달되는 데이터 받기
		Map<String, Object> data = modelAndView.getModel();
		SubMenuVO subMenuVO = (SubMenuVO)data.get("subMenuVO");
		String menuCode = subMenuVO.getMenuCode();
		
		modelAndView.addObject("cateList", adminService.getAdminMenu());
		modelAndView.addObject("subMenuList", adminService.getSubMenuList(menuCode));
		
	}
	
}
