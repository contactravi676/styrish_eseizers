package com.styrish.courses.packs.action;

import java.util.List;
import java.util.Map;

import com.opensymphony.xwork2.ActionSupport;

public class EBookPacksDetailAction extends ActionSupport {

	private static final long serialVersionUID = 1L;
	
	private String subcourseId;
	private List<Map<String,String>> coursePackList;

	public String getSubcourseId() {
		return subcourseId;
	}
	public void setSubcourseId(String subcourseId) {
		this.subcourseId = subcourseId;
	}
	public List<Map<String, String>> getCoursePackList() {
		
		return coursePackList;
	}
	public void setCoursePackList(List<Map<String, String>> coursePackList) {
		this.coursePackList = coursePackList;
	}


	public String execute() {



		return SUCCESS;
	}

}


