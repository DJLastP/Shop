package com.study.shop.test.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.study.shop.admin.service.AdminService;
import com.study.shop.admin.vo.MenuVO;
import com.study.shop.admin.vo.SubMenuVO;
import com.study.shop.item.service.ItemService;
import com.study.shop.item.vo.CategoryVO;
import com.study.shop.test.vo.ClassVO;
import com.study.shop.test.vo.StudentVO;
import com.study.shop.test.vo.TestVO;

import jakarta.annotation.Resource;

@Controller
@RequestMapping("/test")
public class TestController {
	@Resource(name = "itemService")
	private ItemService itemService;
	@Resource(name = "adminService")
	private AdminService adminService;
	
	@GetMapping("/map1")
	public String map1(Model model) {
		List<CategoryVO> cateList = itemService.cateList();
		List<MenuVO> menuList =  adminService.getAdminMenu();
		//List<SubMenuVO> subMenuList =  adminService.getSubMenuList();
		
		Map<String, List> mapData = new HashMap<>();
		mapData.put("cateList", cateList);
		mapData.put("menuList", menuList);
		//mapData.put("subMenuList", subMenuList);
		
		model.addAttribute("mapData", mapData);
		
		return "test/map1";
	}
	
	@GetMapping("/test1")
	public String json_basic() {
		return "test/json_test";
	}
	@ResponseBody
	@PostMapping("/test1")
	public void text1Ajax(String name, int age, String addr, TestVO testVO) {
		System.out.println("test1Ajax() 메소드 실행");
		System.out.println(name);
		System.out.println(age);
		System.out.println(addr);
		System.out.println(testVO);
	}
	@ResponseBody
	@PostMapping("/test2")
	public void text2Ajax(@RequestBody TestVO testVO) {
		System.out.println("test2Ajax() 메소드 실행");
		//System.out.println(name);
		//System.out.println(age);
		//System.out.println(addr);
		System.out.println(testVO);
	}
	
	@ResponseBody
	@PostMapping("/test3")
	public void text3Ajax(@RequestBody HashMap<String, String> map) {
		System.out.println("test3Ajax() 메소드 실행");
		System.out.println(map.get("name"));
		System.out.println(map.get("age"));
		System.out.println(map.get("addr"));

		
		//map으로 받으 ㄴ데이터를 자바 객체로 변환하는 기본방식
		TestVO testVO = new TestVO();
		testVO.setName(map.get("name"));
		testVO.setAge(Integer.parseInt(map.get("age")));
		testVO.setAddr(map.get("addr"));

		//map으로 받은 데이터를 자바 객체로 변환하는 두번째 방법
		ObjectMapper mapper = new ObjectMapper();
		TestVO result = mapper.convertValue(map, TestVO.class);
		
		System.out.println(result + "@@@@@@");
		
		
	}
	
	
	@ResponseBody
	@PostMapping("/test4")
	public void text4Ajax(@RequestBody List<TestVO> list) {
		System.out.println("test4Ajax() 메소드 실행");
		
		System.out.println(list);
	}
	
	@ResponseBody
	@PostMapping("/test5")
	public void text5Ajax(ArrayList<TestVO> dataArr) {
		System.out.println("test5Ajax() 메소드 실행");
		
		System.out.println(dataArr);
	}

	@ResponseBody
	@PostMapping("/test6")
	public void text6Ajax(@RequestBody List<HashMap<String, String>> map) {
		System.out.println("test6Ajax() 메소드 실행");
		
		System.out.println(map);
	}
	@ResponseBody
	@PostMapping("/test7")
	public void text7Ajax(@RequestBody ClassVO classVO) {
		System.out.println("test7Ajax() 메소드 실행");
		
		System.out.println(classVO);
	}
	
	
	@ResponseBody
	@PostMapping("/test8")
	public void text8Ajax(@RequestBody HashMap<String, Object> map) {
		System.out.println("test8Ajax() 메소드 실행");
		
		System.out.println(map);
		
		ObjectMapper mapper = new ObjectMapper();
		StudentVO[] arr = mapper.convertValue(map.get("stuInfo"), StudentVO[].class);
		List<StudentVO> stuList =  Arrays.asList(arr);
		
		System.out.println();
	}
	
}

