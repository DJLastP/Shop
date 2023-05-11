package com.study.shop.test.vo;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ClassVO {
	@JsonProperty("teacher_name")
	private String teacherName;
	
	
	@JsonProperty("class_name")
	private String className;
	@JsonProperty("stu_info")
	private List<StudentVO> stuInfo;
}
