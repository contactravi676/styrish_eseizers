package com.styrish.courses.subjects.databean;

import java.util.*;

import com.styrish.courses.subjects.dao.CourseSubjectsDaoImpl;

public class CourseSubjectDataBean {
	
	private String courseId;
	private List<Map<String,String>> courseSubjectList;
	
	
	public String getCourseId() {
		return courseId;
	}
	public void setCourseId(String courseId) {
		this.courseId = courseId;
	}
	public List<Map<String, String>> getCourseSubjectList() {
		return courseSubjectList;
	}
	public void setCourseSubjectList(List<Map<String, String>> courseSubjectList) {
		CourseSubjectsDaoImpl courseSubjectsDaoImpl = new CourseSubjectsDaoImpl();
		courseSubjectList=courseSubjectsDaoImpl.getCourseSubjectList(courseId);


		this.courseSubjectList = courseSubjectList;
	}
	
	
	

}
