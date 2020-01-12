package com.styrish.courses.action;

import java.util.Map;
import com.opensymphony.xwork2.ActionSupport;



public class CourseAction extends ActionSupport {

	private static final long serialVersionUID = -2613425890762568273L;

	private Map<String,String> courseMap;
	private String courseId;
	

   public Map<String, String> getCourseMap() {
		return courseMap;
	}



	public void setCourseMap(Map<String, String> courseMap) {
		this.courseMap = courseMap;
	}



	



	public String getCourseId() {
		return courseId;
	}



	public void setCourseId(String courseId) {
		this.courseId = courseId;
	}



	public String execute() throws Exception {

		return "success";
	}



}