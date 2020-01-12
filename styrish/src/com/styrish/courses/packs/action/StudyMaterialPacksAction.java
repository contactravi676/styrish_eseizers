package com.styrish.courses.packs.action;

import java.util.List;
import java.util.Map;

import com.opensymphony.xwork2.ActionSupport;

public class StudyMaterialPacksAction extends ActionSupport {

	private static final long serialVersionUID = 1L;
	private String courseId;
	private List<Map<String,String>> coursePackList;

	public String getCourseId() {
		return courseId;
	}
	public void setCourseId(String courseId) {
		this.courseId = courseId;
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
