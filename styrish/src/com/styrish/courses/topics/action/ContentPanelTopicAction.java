package com.styrish.courses.topics.action;

import com.opensymphony.xwork2.ActionSupport;


public class ContentPanelTopicAction extends ActionSupport {
	
    private static final long serialVersionUID = 1L;
	private String courseId;
	private String subjectId;
	
	public String getCourseId() {
		return courseId;
	}
	public void setCourseId(String courseId) {
		this.courseId = courseId;
	}
	public String getSubjectId() {
		return subjectId;
	}
	public void setSubjectId(String subjectId) {
		this.subjectId = subjectId;
	}
	
public String execute() {
		
		return SUCCESS;
	}

}
