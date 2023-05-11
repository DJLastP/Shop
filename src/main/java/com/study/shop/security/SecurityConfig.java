package com.study.shop.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
	
		@Bean
		public SecurityFilterChain filterChain(HttpSecurity security) throws Exception {
			
			
			security.csrf().disable()
								.authorizeHttpRequests()
								.requestMatchers("/","/item/itemList", "/member/checkId", "/member/joinMember", "/item/itemDetail", "/member/findPw")
								.permitAll()
								.requestMatchers("/admin/**").hasAnyRole("ADMIN")
								.anyRequest().authenticated()
							.and()
								.formLogin()
								.loginPage("/member/loginForm")
								.usernameParameter("memId")
								.passwordParameter("memPw")
								.loginProcessingUrl("/member/login")
								.successHandler(getSucessHandler())
								.failureHandler(getFailureHandler())
								.permitAll()
							.and()
								.logout()
								.invalidateHttpSession(true)
								.logoutUrl("/member/logout")
								.logoutSuccessUrl("/")
							.and()
								.exceptionHandling()
								.accessDeniedPage("/accessDeny");
			
			
			return security.build();
		}
		
		@Bean
	    public WebSecurityCustomizer webSecurityCustomizer() {
	        return (web) -> web.ignoring().requestMatchers("/js/**", "/css/**", "/upload/**");
	    }
		
		//암호화 객체 생성
		@Bean
		public PasswordEncoder getPasswordEncoder() {
			return new BCryptPasswordEncoder();
		}
		//로그인 실패시 실행되는 클래스 객체 생성
		@Bean
		public FailureHandler getFailureHandler() {
			return new FailureHandler();
		}
		//로그인 성공시 실행되는 클래스 객체 생성
		@Bean
		public SucessHandler getSucessHandler() {
			return new SucessHandler();
		}
		

}
