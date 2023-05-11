package com.study.shop.intercepter;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class IntercepterConfig implements WebMvcConfigurer {

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
	

		registry.addInterceptor(getMenuIntercepter())
					.order(2)
					.addPathPatterns("/admin/cateManage")
					.addPathPatterns("/admin/regItem")
					.addPathPatterns("/admin/memberManage")
					.addPathPatterns("/admin/orderManage")
					.addPathPatterns("/admin/saleStatusPerMonth")
					.addPathPatterns("/admin/saleStatusByCategory")
					.addPathPatterns("/admin/itemManage")
					.excludePathPatterns("/admin/insertItem");
		
		registry.addInterceptor(getUserMenuIntercepter())
					.addPathPatterns("/item/**")
					.addPathPatterns("/cart/**")
					.addPathPatterns("/buy/**")
					.addPathPatterns("/member/loginForm")
					.excludePathPatterns("/**/*Ajax");
	}
	
	@Bean //객체 -> 리턴되는 데이터를 객체로 생성
	public MenuIntercepter getMenuIntercepter() {
		return new MenuIntercepter();
	}

	@Bean
	public UserMenuIntercepter getUserMenuIntercepter() {
		return new UserMenuIntercepter();
	}
	
	@Bean
	public OrderMenuIntercepter getOrderMenuIntercepter() {
		return new OrderMenuIntercepter();
	}
	
	
}
